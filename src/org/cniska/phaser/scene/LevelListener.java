package org.cniska.phaser.scene;

import org.cniska.phaser.event.Event;
import org.cniska.phaser.event.Subscriber;

public interface LevelListener extends Subscriber {

	/**
	 * Called when a level ends.
	 *
	 * @param event The event.
	 */
	public void onLevelEnd(Event event);
}
