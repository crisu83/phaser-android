package org.cniska.invaders.menu;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.scene.Scene;
import org.cniska.phaser.ui.Button;

public class NewGameButton extends Button {

	/**
	 * Creates a new element.
	 *
	 * @param view  The game view.
	 * @param scene The parent scene.
	 */
	public NewGameButton(GameView view, Scene scene) {
		super(view, scene);
	}

	@Override
	public void input() {
		super.input();

		if (touch != null) {
			view.newGame();
			touch = null;
		}
	}

	@Override
	protected void init() {
		super.init();
		center();
		body = "NEW GAME";
	}
}
