package org.cniska.fancypong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.debug.ui.MonitorPanel;

public class FancyPong extends GameView {

	private Ball ball;
	private Racket player;
	private Racket computer;
	private MonitorPanel monitorPanel;

	private Paint background;

	/**
	 * Creates the game view.
	 *
	 * @param period Time between draws (in nanoseconds).
	 * @param context The parent activity.
	 */
	public FancyPong(long period, Context context) {
		super(period, context);

		background = new Paint();
		background.setColor(Color.BLACK);
	}

	@Override
	public void init() {
		ball = new Ball(this);

		player = new Racket(this);
		player.setPosition(20, 295);

		computer = new Computer(this);
		computer.setPosition(1100, 295);

		monitorPanel = new MonitorPanel(this);
	}

	@Override
	public void update(long timeDelta) {
		ball.update(timeDelta);
		player.update(timeDelta);
		computer.update(timeDelta);
		monitorPanel.update(timeDelta);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawRect(0, 0, getWidth(), getHeight(), background);

		ball.draw(canvas);
		player.draw(canvas);
		computer.draw(canvas);
		monitorPanel.draw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		player.setY((int) (event.getY() - 75));
		return true;
	}

	// Getters and setters
	// ----------------------------------------

	public Ball getBall() {
		return ball;
	}

	public Racket getPlayer() {
		return player;
	}

	public Racket getComputer() {
		return computer;
	}
}
