package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.render.Animation;
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
	}

	@Override
	public void init() {
		size(5, 20);
		velocity(0, -5);
		loadBitmap(R.drawable.missile_01);

		Animation animation = new Animation();
		animation.addFrame(0, 0, 100);
		animation.addFrame(5, 0, 100);
		addAnimation("idle", animation);
		playAnimation("idle");

		explosion.loadBitmap(R.drawable.explosion_01);
	}

	@Override
	public boolean collides(Actor other) {
		return other instanceof Alien && intersects(other);
	}
}
