package org.cniska.fancypong;

import org.cniska.phaser.core.GameView;

public class Computer extends Racket {

	public Computer(GameView view) {
		super(view);
	}

	@Override
	public void update() {
		super.update();

		Ball ball = ((FancyPong) view).getBall();
		y = ball.getY() - 65;
	}
}
