package org.cniska.invaders.menu;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.scene.Scene;
import org.cniska.phaser.ui.Button;

public class SpaceMenu extends Scene {

	protected Button newGameButton;

	/**
	 * Creates a new scene.
	 *
	 * @param view The game view.
	 */
	public SpaceMenu(GameView view) {
		super(view);
	}

	@Override
	protected void init() {
		super.init();

		newGameButton = new NewGameButton(view, this);
		addElement(newGameButton);
	}
}
