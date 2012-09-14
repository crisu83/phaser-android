package org.cniska.phaser.collision;

import org.cniska.phaser.event.Event;
import org.cniska.phaser.event.Subscriber;

public interface Collidable extends Subscriber {

	/**
	 * Called when the object collides.
	 *
	 * @param event The event.
	 */
	public void onCollision(Event event);
}
