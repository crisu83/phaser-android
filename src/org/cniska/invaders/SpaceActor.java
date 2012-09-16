package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.event.Event;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.scene.World;

public abstract class SpaceActor extends Actor {

	protected Explosion explosion;

	/**
	 * Creates a new actor.
	 *
	 * @param view The game view.
	 * @param world The parent world.
	 */
	protected SpaceActor(GameView view, World world) {
		super(view, world);
		explosion = new Explosion(view, world);
	}

	@Override
	public void onCollision(Event event) {
		visible = false;
		remove();

		explosion.position(x, y);
		explosion.explode();
	}
}
