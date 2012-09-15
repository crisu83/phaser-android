package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.scene.GameScene;

public class Rocket extends SpaceActor {

	/**
	 * Creates a new actor.
	 *
	 * @param view The game view.
	 * @param scene The parent scene.
	 */
	public Rocket(GameView view, GameScene scene) {
		super(view, scene);
		name = "rocket";
	}

	@Override
	public void init() {
		super.init();
		loadBitmap(R.drawable.missile_01);
		explosion.loadBitmap(R.drawable.explosion_01);
	}

	@Override
	public boolean collides(Actor other) {
		return other instanceof Alien && intersects(other);
	}
}
