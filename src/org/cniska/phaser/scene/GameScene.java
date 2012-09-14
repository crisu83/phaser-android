package org.cniska.phaser.scene;

import org.cniska.phaser.collision.CollisionHandler;
import org.cniska.phaser.core.GameView;

public abstract class GameScene extends Scene {

	// Member variables
	// ----------------------------------------

	protected CollisionHandler world;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new scene.
	 *
	 * @param view The game view.
	 */
	public GameScene(GameView view) {
		super(view);
		world = new CollisionHandler(view);
		addNode(world);
	}

	// Getters and setters
	// ----------------------------------------

	public CollisionHandler getWorld() {
		return world;
	}
}
