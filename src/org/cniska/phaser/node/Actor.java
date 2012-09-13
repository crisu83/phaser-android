package org.cniska.phaser.node;

import org.cniska.phaser.core.GameView;

public abstract class Actor extends Sprite {

	// Member variables
	// ----------------------------------------

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new actor.
	 *
	 * @param view The game view.
	 */
	protected Actor(GameView view) {
		super(view);
	}
}
