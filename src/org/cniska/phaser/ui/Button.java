package org.cniska.phaser.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.input.TouchListener;
import org.cniska.phaser.scene.Scene;

public abstract class Button extends Element implements TouchListener {

	public static final int DEFAULT_BUTTON_TEXT_SIZE = 32;

	protected MotionEvent touch;

	/**
	 * Creates a new button.
	 *
	 * @param view  The game view.
	 * @param scene The parent scene.
	 */
	protected Button(GameView view, Scene scene) {
		super(view, scene);
	}

	@Override
	protected void init() {
		super.init();
		size(300, 80);
		setPadding(20);
		text.setTextAlign(Paint.Align.CENTER);
		text.setTextSize(DEFAULT_BUTTON_TEXT_SIZE);
		view.getTouchHandler().subscribe(this);
	}

	@Override
	public void update(Updateable parent) {
		super.update(parent);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawRect(x, y, x2(), y2(), background);

		if (body != null) {
			canvas.drawText(body, x + width / 2, lineY(1), text);
		}
	}

	@Override
	public void onTouch(MotionEvent event) {
		if (contains((int) event.getX(), (int) event.getY())) {
			switch (event.getAction()) {
				// Buttons are triggered when the finger is lifted.
				case MotionEvent.ACTION_UP:
					touch = event;
					break;
				default:
					// We do not care about other events.
			}
		}
	}
}
