package org.cniska.fancypong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import org.cniska.phaser.core.GameObject;
import org.cniska.phaser.core.GameView;

public class Ball extends GameObject {

	private Paint paint;

	public Ball(GameView view) {
		super(view);

		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.WHITE);
		paint.setStyle(Paint.Style.FILL);

		setPosition(40, 40);
		setSize(40, 40);
		setVelocity(10, 10);
	}

	@Override
	public void update() {
		super.update();

		Racket player = ((FancyPong) view).getPlayer();
		Racket ai = ((FancyPong) view).getComputer();

		if (x <= 0)
			vx = Math.abs(vx);

		if (y <= 0)
			vy = Math.abs(vy);

		if (getX2() >= view.getWidth())
			vx = Math.abs(vx) * -1;

		if (getY2() >= view.getHeight())
			vy = Math.abs(vy) * -1;

		if (x <= (player.getX2() + 10) && y >= (player.getY() - 10) && y <= (player.getY2() + 10)) {
			vx = Math.abs(vx);
		}

		if (getX2() >= (ai.getX2() - 10) && y >= (ai.getY() - 10) && y <= (ai.getY2() + 10)) {
			vx = Math.abs(vx) * -1;
		}
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawCircle(x, y, width / 2, paint);
	}
}
