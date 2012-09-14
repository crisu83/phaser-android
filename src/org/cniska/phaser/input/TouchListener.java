package org.cniska.phaser.input;

import org.cniska.phaser.event.Subscriber;

public interface TouchListener extends Subscriber {

	/**
	 * Called when a touch event is received.
	 *
	 * @param event The event.
	 */
	public void onTouch(TouchEvent event);
}
