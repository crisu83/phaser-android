package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.render.Animation;
import org.cniska.phaser.scene.GameScene;

public class Explosion extends Actor {

	protected int lifetime;
	protected long startTime;

	/**
	 * Creates a new actor.
	 *
	 * @param view The game view.
	 * @param scene The parent scene.
	 */
	public Explosion(GameView view, GameScene scene) {
		super(view, scene);
		lifetime = 1000 * 1000000; // ms -> ns
		visible = false;
	}

	@Override
	public void init() {
		size(20, 20);

		Animation animation = new Animation();
		animation.addFrame(0, 0, 100);
		animation.addFrame(20, 0, 100);
		animation.addFrame(40, 0, 100);
		animation.addFrame(60, 0, 100);
		animation.addFrame(80, 0, 100);
		animation.addFrame(100, 0, 100); // empty frame
		animation.setLoop(false);
		addAnimation("explode", animation);

		lifetime = 1000 * 1000000; // ns -> ms
	}

	public void explode() {
		startTime = System.nanoTime();
		visible = true;
		playAnimation("explode");
	}

	@Override
	public void update(Updateable parent) {
		super.update(parent);

		if (startTime > 0 && ((System.nanoTime() - startTime) > lifetime)) {
			remove();
		}
	}
}
