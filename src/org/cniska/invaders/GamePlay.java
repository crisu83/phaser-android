package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.scene.GameScene;

public class GamePlay extends GameScene {

	protected Alien alien;
	protected Player player;

	public GamePlay(GameView view) {
		super(view);
	}

	@Override
	public void setup() {
		alien = new Alien(view, this);
		alien.position(600, 100);

		player = new Player(view, this);
		view.getTouchHandler().attach(player); // player wants to listen for touch events
	}
}
