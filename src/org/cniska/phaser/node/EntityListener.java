package org.cniska.phaser.node;

import org.cniska.phaser.event.Event;
import org.cniska.phaser.event.Subscriber;

public interface EntityListener extends Subscriber {

	/**
	 * Called when the entity is removed.
	 *
	 * @param event The event.
	 */
	public void onEntityRemove(Event event);
}
