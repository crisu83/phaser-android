package org.cniska.invaders;

import org.cniska.phaser.collision.CollisionEvent;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.scene.GameScene;

public abstract class SpaceActor extends Actor {

	protected int explosionResource;

	/**
	 * Creates a new actor.
	 *
	 * @param view The game view.
	 * @param scene The parent scene.
	 */
	protected SpaceActor(GameView view, GameScene scene) {
		super(view, scene);
	}

	@Override
	public void onCollision(CollisionEvent event) {
		visible = false;
		remove();

		Explosion explosion = new Explosion(explosionResource, view, scene);
		explosion.position(x, y);
		explosion.setVisible(true);
		explosion.playAnimation("explode");
	}
}
