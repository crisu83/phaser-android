package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.Entity;
import org.cniska.phaser.scene.GameScene;
import org.cniska.phaser.render.Animation;

public class Rocket extends SpaceActor {

	/**
	 * Creates a new actor.
	 *
	 * @param view The game view.
	 * @param scene The parent scene.
	 */
	protected Rocket(GameView view, GameScene scene) {
		super(view, scene);

		explosionResource = R.drawable.explosion_01;

		loadBitmap(R.drawable.missile_01);

		Animation animation = new Animation();
		animation.addFrame(0, 0, 100);
		animation.addFrame(5, 0, 100);
		addAnimation("idle", animation);
		playAnimation("idle");

		size(5, 20);
		velocity(0, -5);
	}

	@Override
	public boolean collides(Entity other) {
		return other instanceof Alien;
	}
}
