package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.Node;
import org.cniska.phaser.node.Scene;

public class GamePlay extends Scene {

	protected Player player;

	public GamePlay(GameView view) {
		super(view);
	}

	@Override
	public void setup() {
		Node gameRoot = view.getGameRoot();

		player = new Player(view);
		gameRoot.add(player);
		renderer.add(player);

		// Player wants to listen for touch events.
		view.getTouchHandler().subscribe(player);
	}
}
