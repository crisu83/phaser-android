package org.cniska.pong;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.Node;
import org.cniska.phaser.node.Scene;

public class GamePlay extends Scene {

	private Ball ball;
	private Player player;
	private Computer computer;

	public GamePlay(GameView view) {
		super(view);
	}

	@Override
	public void setup() {
		Node gameRoot = view.getGameRoot();

		player = new Player(view);
		gameRoot.add(player);
		renderer.add(player);

		computer = new Computer(view);
		gameRoot.add(computer);
		renderer.add(computer);

		ball = new Ball(view);
		ball.setPlayer(player);
		ball.setComputer(computer);
		gameRoot.add(ball);
		renderer.add(ball);

		computer.setBall(ball);

		// Player wants to listen for touch events.
		view.getTouchHandler().subscribe(player);
	}
}
