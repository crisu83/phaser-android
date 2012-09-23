package org.cniska.phaser.node;

import org.cniska.phaser.event.Event;
import org.cniska.phaser.event.Subscriber;

public interface ActorListener extends Subscriber {

	/**
	 * Called when the actor is born.
	 *
	 * @param event The event.
	 */
	public void onActorBirth(Event event);

	/**
	 * Called when the actor dies.
	 *
	 * @param event The event.
	 */
	public void onActorDeath(Event event);
}
