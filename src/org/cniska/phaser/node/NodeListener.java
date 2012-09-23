package org.cniska.phaser.node;

import org.cniska.phaser.event.Event;

public interface NodeListener {

	/**
	 * Called when the node is initialized.
	 *
	 * @param event The event.
	 */
	public void onNodeInit(Event event);

	/**
	 * Called when the node is uninitialzied.
	 *
	 * @param event The event.
	 */
	public void onNodeUninit(Event event);
}
