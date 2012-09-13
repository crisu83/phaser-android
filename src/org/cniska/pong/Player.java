package org.cniska.pong;

import android.view.MotionEvent;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.input.TouchListener;

public class Player extends Racket implements TouchListener {

	private MotionEvent touch;

	public Player(GameView view) {
		super(view);

		position(20, 295);
	}

	@Override
	public void input() {
		if (touch != null) {
			y = (int) (touch.getY() - 75);
			touch = null;
		}
	}

	@Override
	public void onTouch(MotionEvent event) {
		this.touch = event;
	}
}
