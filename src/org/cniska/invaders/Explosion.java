package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.scene.GameScene;

public class Explosion extends Actor {

	protected int lifetime;
	protected long startTime;
	protected boolean explode;

	/**
	 * Creates a new actor.
	 *
	 * @param view The game view.
	 * @param scene The parent scene.
	 */
	public Explosion(GameView view, GameScene scene) {
		super(view, scene);
		name = "explosion";
		lifetime = 1000 * 1000000; // ms -> ns
		visible = false;
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
