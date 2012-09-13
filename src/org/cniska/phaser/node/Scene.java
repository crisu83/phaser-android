package org.cniska.phaser.node;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.render.SpriteRenderer;

abstract public class Scene extends Node {

	// Member variables
	// ----------------------------------------

	protected SpriteRenderer renderer;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new scene.
	 *
	 * @param view The game view.
	 */
	public Scene(GameView view) {
		super(view);
		renderer = new SpriteRenderer(view);
		add(renderer);
	}

	// Abstract methods
	// ----------------------------------------

	/**
	 * Setups the scene.
	 */
	public abstract void setup();

	// Getters and setters
	// ----------------------------------------

	public SpriteRenderer getRenderer() {
		return renderer;
	}
}
