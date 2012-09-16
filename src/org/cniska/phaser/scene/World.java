package org.cniska.phaser.scene;

import android.graphics.Canvas;
import org.cniska.phaser.collision.Physics;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.debug.Debuggable;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.util.List;

public abstract class World extends Scene {

	// Member variables
	// ----------------------------------------

	protected int currentLevel = -1;
	protected List<Level> levels;
	protected Physics physics;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new world.
	 *
	 * @param view The game view.
	 */
	public World(GameView view) {
		super(view);
		levels = new List<Level>();
		physics = new Physics(view);
		addNode(physics);
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
		if (index > (levels.size()) - 1) {
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
	public void setup() {
		nextLevel(); // load the first level
	}

	@Override
	public void update(Updateable parent) {
		super.update(parent);

		levels.update(this);

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
