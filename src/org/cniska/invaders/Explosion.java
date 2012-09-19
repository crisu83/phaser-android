package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.scene.World;

public class Explosion extends Actor {

	protected int lifetime;
	protected long startTime;

	/**
	 * Creates a new actor.
	 *
	 * @param view The game view.
	 * @param world The parent world.
	 */
	public Explosion(GameView view, World world) {
		super(view, world);
		id = Invaders.ACTOR_EXPLOSION;
		lifetime = 400 * 1000000; // ms -> ns
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
