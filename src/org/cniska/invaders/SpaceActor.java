package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.event.Event;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.scene.GameScene;

public abstract class SpaceActor extends Actor {

	protected Explosion explosion;

	/**
	 * Creates a new actor.
	 *
	 * @param view The game view.
	 * @param scene The parent scene.
	 */
	protected SpaceActor(GameView view, GameScene scene) {
		super(view, scene);
		explosion = new Explosion(view, scene);
	}

	@Override
	public void onCollision(Event event) {
		visible = false;
		remove();

		explosion.position(x, y);
		explosion.explode();
	}
}
