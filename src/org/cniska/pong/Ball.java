package org.cniska.pong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.node.Node;

public class Ball extends Actor {

	private Player player;
	private Computer computer;
	private Paint paint;

	public Ball(GameView view) {
		super(view);

		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.WHITE);
		paint.setStyle(Paint.Style.FILL);

		position(40, 40);
		setSize(20);
		velocity(5, 5);
		acceleration(0.1f, 0.1f);
	}

	public void setSize(int radius) {
		width = radius * 2;
		height = radius * 2;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawCircle(x, y, width / 2, paint);
	}

	@Override
	public void update(Node parent) {
		super.update(parent);

		if (x <= 0)
			vx = Math.abs(vx);

		if (y <= 0)
			vy = Math.abs(vy);

		if (x2() >= view.getWidth())
			vx = Math.abs(vx) * -1;

		if (y2() >= view.getHeight())
			vy = Math.abs(vy) * -1;

		if (player != null
				&& (x <= (player.x2() + 10) && y >= (player.getY() - 10) && y <= (player.y2() + 10)))
			vx = Math.abs(vx);

		if (computer != null
				&& (x2() >= (computer.x2() - 10) && y >= (computer.getY() - 10) && y <= (computer.y2() + 10)))
			vx = Math.abs(vx) * -1;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}
}
