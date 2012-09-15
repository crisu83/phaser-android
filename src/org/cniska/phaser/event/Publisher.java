package org.cniska.phaser.event;

import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.util.List;

public abstract class Publisher implements Updateable {

	// Member variables
	// ----------------------------------------

	protected List<Subscriber> subscribers;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new publisher.
	 */
	protected Publisher() {
		subscribers = new List<Subscriber>();
	}

	/**
	 * Adds a subscriber to the publisher.
	 *
	 * @param subscriber The subscriber to add.
	 */
	public void attach(Subscriber subscriber) {
		subscribers.add(subscriber);
	}

	/**
	 * Removes a subscriber from the publisher.
	 *
	 * @param subscriber The subscriber to remove.
	 */
	public void detach(Subscriber subscriber) {
		subscribers.remove(subscriber);
	}

	// Overridden methods
	// ----------------------------------------

	@Override
	public void update(Updateable parent) {
		subscribers.update(this);
	}

	/**
	 * Notifies the listeners that the given event occurred.
	 * Override this method to implement event notification.
	 *
	 * @param event The event.
	 */
	public void notify(Event event) {
	}
}
