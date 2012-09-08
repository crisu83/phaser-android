package org.cniska.phaser.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import org.cniska.phaser.core.GameObject;
import org.cniska.phaser.core.GameView;

public class GamePanel extends GameObject {

	private Paint background;

	public GamePanel(GameView view) {
		super(view);

		background = new Paint();
		background.setColor(Color.TRANSPARENT);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawRect(x, y, getX2(), getY2(), background);
	}

	public void setBackground(Paint background) {
		this.background = background;
	}
}
