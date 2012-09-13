package org.cniska.phaser.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.Sprite;

public class Panel extends Sprite {

	// Member variables
	// ----------------------------------------

	private Paint background;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new panel.
	 *
	 * @param view The game view.
	 */
	public Panel(GameView view) {
		super(view);

		background = new Paint();
		background.setColor(Color.TRANSPARENT);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawRect(x, y, x2(), y2(), background);
	}

	// Getters and setters
	// ----------------------------------------

	public void setBackground(Paint background) {
		this.background = background;
	}
}
