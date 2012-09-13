package org.cniska.pong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.Actor;

public class Racket extends Actor {

	private Paint paint;

	public Racket(GameView view) {
		super(view);

		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.WHITE);
		paint.setStyle(Paint.Style.FILL);

		size(20, 150);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawRect(x, y, x2(), y2(), paint);
	}
}
