package org.cniska.phaser.scene;

import android.graphics.Canvas;
import org.cniska.phaser.collision.Physics;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.debug.Debuggable;
import org.cniska.phaser.debug.EntityPanel;
import org.cniska.phaser.node.Actor;

import java.util.HashMap;

public abstract class World extends Scene {

	// Member variables
	// ----------------------------------------

	protected Level currentLevel;
	protected HashMap<Integer, Level> levels;
	protected Physics physics;
	protected EntityPanel entityPanel;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new world.
	 *
	 * @param view The game view.
	 */
	public World(GameView view) {
		super(view);
		levels = new HashMap<Integer, Level>();
	}

	/**
	 * Adds an actor to the world.
	 *
	 * @param actor The actor.
	 */
	public void addActor(Actor actor) {
		addSprite(actor);
		physics.addActor(actor);
	}

	/**
	 * Loads the given level.
	 *
	 * @param id The level identifier.
	 */
	public void loadLevel(int id) {
		Level level = levels.get(id);
		if (level == null) {
			level = createLevel(id);
			if (level != null) {
				levels.put(id, level);
			}
		}
		currentLevel = level;
	}

	/**
	 * Creates the level with the given id.
	 *
	 * @param id The level identifier.
	 * @return The actor.
	 */
	public Level createLevel(int id) {
		Level level = levelFactory(id);
		afterCreateLevel(level);
		return level;
	}

	/**
	 * Creates the actor with the given id.
	 *
	 * @param id The actor identifier.
	 * @return The actor.
	 */
	public Actor createActor(int id) {
		Actor actor = actorFactory(id);
		afterCreateActor(actor);
		return actor;
	}

	/**
	 * Called after the level has been created.
	 *
	 * @param level The level.
	 */
	protected void afterCreateLevel(Level level) {
	}

	/**
	 * Called after the actor has been created.
	 *
	 * @param actor The actor.
	 */
	protected void afterCreateActor(Actor actor) {
		if (view.isDebug()) {
			actor.subscribe(entityPanel);
		}
	}

	// Abstract methods
	// ----------------------------------------

	/**
	 * Factory method that creates the levels.
	 *
	 * @param id The level identifier.
	 * @return The level.
	 */
	public abstract Level levelFactory(int id);

	/**
	 * Factory method that creates actors.
	 *
	 * @param id The actor identifier.
	 * @return The actor.
	 */
	public abstract Actor actorFactory(int id);

	// Overridden methods
	// ----------------------------------------

	@Override
	protected void init() {
		super.init();

		physics = new Physics(view);
		addNode(physics);

		if (view.isDebug()) {
			entityPanel = new EntityPanel(view, this);
			addSprite(entityPanel);
		}
	}

	@Override
	public void update(Updateable parent) {
		super.update(parent);

		if (currentLevel != null) {
			currentLevel.update(this);
		}
	}

	@Override
	public void debug(Debuggable parent, Canvas canvas) {
		super.debug(parent, canvas);

		if (physics != null) {
			physics.debug(this, canvas);
		}
	}

	// Getters and setters
	// ----------------------------------------

	public Physics getPhysics() {
		return physics;
	}
}
