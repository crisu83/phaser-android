package org.cniska.phaser.scene;

import android.graphics.Canvas;
import org.cniska.phaser.collision.Physics;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.debug.Debuggable;
import org.cniska.phaser.debug.EntityPanel;
import org.cniska.phaser.debug.MonitorPanel;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.node.Sprite;

import java.util.ArrayList;

public abstract class World extends Scene {

	// Member variables
	// ----------------------------------------

	protected int currentLevel = -1;
	protected ArrayList<Level> levels;
	protected Physics physics;
	protected MonitorPanel monitorPanel;
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
		levels = new ArrayList<Level>();
		physics = new Physics(view);
		addNode(physics);
		entityPanel = new EntityPanel(view, this);
		addSprite(entityPanel);
	}

	/**
	 * Adds an actor to the world.
	 *
	 * @param actor The actor.
	 */
	public void addActor(Actor actor) {
		addNode(actor);
		physics.addActor(actor);
		renderer.addSprite(actor);
	}

	/**
	 * Adds a sprite to the world.
	 *
	 * @param sprite The sprite.
	 */
	public void addSprite(Sprite sprite) {
		addNode(sprite);
		renderer.addSprite(sprite);
	}

	/**
	 * Loads the next level.
	 */
	public void nextLevel() {
		loadLevel(currentLevel + 1);
	}

	/**
	 * Loads the given level.
	 *
	 * @param index The level index.
	 */
	protected void loadLevel(int index) {
		if (index > (levels.size() - 1)) {
			Level level = createLevel(index + 1);
			if (level != null) {
				levels.add(level);
			}
		}
		currentLevel = index;
	}

	/**
	 * Returns the current level.
	 *
	 * @return The level.
	 */
	protected Level currentLevel() {
		return currentLevel > -1 && currentLevel < levels.size() ? levels.get(currentLevel) : null;
	}

	// Abstract methods
	// ----------------------------------------

	/**
	 * Creates the level with the given id.
	 *
	 * @param id The level identifier.
	 * @return The level.
	 */
	public abstract Level createLevel(int id);

	/**
	 * Creates the actor with the given id.
	 *
	 * @param id The actor identifier.
	 * @return The actor.
	 */
	public abstract Actor createActor(int id);

	// Overridden methods
	// ----------------------------------------

	@Override
	public void init() {
		super.init();

		if (view.isDebug()) {
			monitorPanel = new MonitorPanel(view, this);
			addSprite(monitorPanel);
		}

		nextLevel(); // load the first level
	}

	@Override
	public void update(Updateable parent) {
		super.update(parent);

		Level currentLevel = currentLevel();
		if (currentLevel != null) {
			currentLevel.update(this);
		}
	}

	@Override
	public void debug(Debuggable parent, Canvas canvas) {
		super.debug(parent, canvas);
		physics.debug(this, canvas);
	}

	// Getters and setters
	// ----------------------------------------

	public Physics getPhysics() {
		return physics;
	}
}
