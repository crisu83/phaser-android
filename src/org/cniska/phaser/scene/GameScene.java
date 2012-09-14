package org.cniska.phaser.scene;

import org.cniska.phaser.collision.World;
import org.cniska.phaser.core.GameView;

public abstract class GameScene extends Scene {

	// Member variables
	// ----------------------------------------

	protected World world;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new scene.
	 *
	 * @param view The game view.
	 */
	public GameScene(GameView view) {
		super(view);
		world = new World(view);
		addNode(world);
	}

	// Getters and setters
	// ----------------------------------------

	public World getWorld() {
		return world;
	}
}
