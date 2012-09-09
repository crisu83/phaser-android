package org.cniska.phaser.node;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.input.TouchListener;

abstract public class GameScene extends GameNode {

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new scene.
	 *
	 * @param view The parent view.
	 */
	public GameScene(GameView view) {
		super(view);
	}
}
