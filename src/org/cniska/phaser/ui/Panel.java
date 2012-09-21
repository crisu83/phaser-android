package org.cniska.phaser.ui;

import android.graphics.Color;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.scene.Scene;

public class Panel extends Element {

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new panel.
	 *
	 * @param view The game view.
	 * @param scene The parent scene.
	 */
	public Panel(GameView view, Scene scene) {
		super(view, scene);
	}

	@Override
	public void init() {
		super.init();

		text.setColor(Color.WHITE);
		text.setTextSize(12);
	}
}
