package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.draw.Renderer;
import org.cniska.phaser.node.Node;
import org.cniska.phaser.node.Scene;

public class GamePlay extends Scene {

	protected Player player;
	protected Explosion explosion;

	public GamePlay(GameView view) {
		super(view);
	}

	@Override
	public void setup() {
		Node gameRoot = view.getGameRoot();
		Renderer renderer = view.getRenderer();

		player = new Player(view);
		gameRoot.add(player);
		renderer.add(player);

		explosion = new Explosion(view);
		gameRoot.add(explosion);
		renderer.add(explosion);

		// Player wants to listen for touch events.
		view.getTouchHandler().subscribe(player);
	}
}
