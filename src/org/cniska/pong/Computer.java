package org.cniska.pong;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.Node;

public class Computer extends Racket {

	private Ball ball;

	public Computer(GameView view) {
		super(view);

		position(1100, 295);
	}

	@Override
	public void update(Node parent) {
		super.update(parent);

		if (ball != null)
			y = ball.getY() - 65;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}
}
