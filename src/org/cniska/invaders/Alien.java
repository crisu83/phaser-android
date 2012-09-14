package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.Entity;
import org.cniska.phaser.scene.GameScene;
import org.cniska.phaser.render.Animation;

public class Alien extends SpaceActor {

	/**
	 * Creates a new actor.
	 *
	 * @param view The game view.
	 * @param scene The parent scene.
	 */
	protected Alien(GameView view, GameScene scene) {
		super(view, scene);

		explosionResource = R.drawable.explosion_04;

		loadBitmap(R.drawable.ship_02);

		Animation animation = new Animation();
		animation.addFrame(0, 0, 100);
		animation.addFrame(20, 0, 100);
		addAnimation("idle", animation);
		playAnimation("idle");

		size(20, 20);
	}

	@Override
	public boolean collides(Entity other) {
		return other instanceof Rocket;
	}
}
