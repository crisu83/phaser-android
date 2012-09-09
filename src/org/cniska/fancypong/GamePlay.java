package org.cniska.fancypong;

import org.cniska.phaser.draw.Renderer;
import org.cniska.phaser.node.GameScene;
import org.cniska.phaser.core.GameView;

public class GamePlay extends GameScene {

	private Ball ball;
	private Player player;
	private Computer computer;

	public GamePlay(GameView view) {
		super(view);
	}

	@Override
	public void init() {
		FancyPong view = (FancyPong) getView();
		Renderer renderer = view.getRenderer();

		player = new Player(view);
		computer = new Computer(view);
		ball = new Ball(view);
		ball.setPlayer(player);
		ball.setComputer(computer);
		computer.setBall(ball);

		// Player wants to listen for touch events.
		view.getTouchHandler().addListener(player);

		renderer.add(player);
		renderer.add(computer);
		renderer.add(ball);
	}

	@Override
	public void update() {
		super.update();

		ball.update();
		player.update();
		computer.update();
	}
}
