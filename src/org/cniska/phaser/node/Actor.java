package org.cniska.phaser.node;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.scene.GameScene;

public abstract class Actor extends Sprite {

	// Member variables
	// ----------------------------------------

	protected GameScene scene;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new actor.
	 *
	 * @param view The game view.
	 * @param scene The parent scene.
	 */
	protected Actor(GameView view, GameScene scene) {
		super(view);
		this.scene = scene;

		scene.addNode(this);
		scene.getRenderer().addSprite(this);
	}
}
