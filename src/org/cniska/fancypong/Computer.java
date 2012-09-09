package org.cniska.fancypong;

import org.cniska.phaser.core.GameView;

public class Computer extends Racket {

	private Ball ball;

	public Computer(GameView view) {
		super(view);

		setPosition(1100, 295);
	}

	@Override
	public void update() {
		super.update();

		if (ball != null)
			y = ball.getY() - 65;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}
}
