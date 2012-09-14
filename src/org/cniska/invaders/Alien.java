package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.render.Animation;
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
	}

	@Override
	public void init() {
		super.init();

		size(20, 20);
		loadBitmap(R.drawable.ship_02);

		Animation animation = new Animation();
		animation.addFrame(0, 0, 100);
		animation.addFrame(20, 0, 100);
		addAnimation("idle", animation);
		playAnimation("idle");

		explosion.loadBitmap(R.drawable.explosion_04);
	}

	@Override
	public boolean collides(Actor other) {
		return other instanceof Rocket && intersects(other);
	}
}
