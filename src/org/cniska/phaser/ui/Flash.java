package org.cniska.phaser.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.scene.Scene;

public class Flash extends Element {

	public static final int DEFAULT_FLASH_TEXT_SIZE = 48;

	/**
	 * Creates a new flash.
	 *
	 * @param view  The game view.
	 * @param scene The parent scene.
	 */
	public Flash(String message, GameView view, Scene scene) {
		super(view, scene);
		body = message;
	}

	@Override
	protected void init() {
		super.init();
		size(400, 100);
		center();
		text.setTextAlign(Paint.Align.CENTER);
		text.setTextSize(DEFAULT_FLASH_TEXT_SIZE);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawRect(x, y, x2(), y2(), background);

		if (body != null) {
			canvas.drawText(body, x + width / 2, lineY(1), text);
		}
	}
}
