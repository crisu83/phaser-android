package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.scene.GameScene;

public class Alien extends SpaceActor {

	/**
	 * Creates a new actor.
	 *
	 * @param view The game view.
	 * @param scene The parent scene.
	 */
	public Alien(GameView view, GameScene scene) {
		super(view, scene);
		name = "alien";
	}

	@Override
	public void init() {
		super.init();
		loadBitmap(R.drawable.ship_02);
		explosion.loadBitmap(R.drawable.explosion_04);
	}

	@Override
	public boolean collides(Actor other) {
		return other instanceof Rocket && intersects(other);
	}
}
