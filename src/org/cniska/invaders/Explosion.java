package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.render.Animation;
import org.cniska.phaser.node.Actor;

public class Explosion extends Actor {

	/**
	 * Creates a new actor.
	 *
	 * @param view The game view.
	 */
	protected Explosion(GameView view) {
		super(view);

		position(600, 200);
		size(20, 20);

		loadBitmap(R.drawable.explosion_03);

		Animation animation = new Animation();
		animation.addFrame(0, 0, 100);
		animation.addFrame(20, 0, 100);
		animation.addFrame(40, 0, 100);
		animation.addFrame(60, 0, 100);
		animation.addFrame(80, 0, 100);
		addAnimation("idle", animation);
		playAnimation("idle");
	}
}
