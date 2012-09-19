package org.cniska.phaser.scene;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.Node;
import org.cniska.phaser.render.SpriteRenderer;

abstract public class Scene extends Node {

	// Member variables
	// ----------------------------------------

	protected SpriteRenderer renderer;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new world.
	 *
	 * @param view The game view.
	 */
	public Scene(GameView view) {
		super(view);
		renderer = new SpriteRenderer(view);
		addNode(renderer);
	}

	// Getters and setters
	// ----------------------------------------

	public SpriteRenderer getRenderer() {
		return renderer;
	}
}
