package org.cniska.phaser.node;

import org.cniska.phaser.collision.Collidable;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.event.Event;
import org.cniska.phaser.scene.GameScene;

public abstract class Actor extends Sprite implements Collidable {

	// Member variables
	// ----------------------------------------

	protected GameScene scene;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new actor.
	 *
	 * @param view The game view.
	 * @param scene The parent scene.
	 */
	protected Actor(GameView view, GameScene scene) {
		super(view);
		this.scene = scene;

		scene.addNode(this);
		scene.getWorld().addActor(this);
		scene.getRenderer().addSprite(this);
	}

	/**
	 * Returns whether the actor collides with the given actor.
	 *
	 * @param other The other actor.
	 * @return The result.
	 */
	public boolean collides(Actor other) {
		return intersects(other);
	}

	/**
	 * Fires the collision event to the given actor.
	 *
	 * @param other The other actor.
	 */
	public void collide(Actor other) {
		Event event = new Event("actor:collide", this);
		other.onCollision(event);
	}

	@Override
	public void onCollision(Event event) {
	}
}
