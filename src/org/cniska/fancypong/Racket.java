package org.cniska.fancypong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import org.cniska.phaser.node.GameObject;
import org.cniska.phaser.core.GameView;

public class Racket extends GameObject {

	private Paint paint;

	public Racket(GameView view) {
		super(view);

		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.WHITE);
		paint.setStyle(Paint.Style.FILL);

		setSize(20, 150);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawRect(x, y, getX2(),getY2(), paint);
	}
}
