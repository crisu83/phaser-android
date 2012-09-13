package org.cniska.phaser.event;

import java.util.Vector;

public abstract class Publisher {

	// Member variables
	// ----------------------------------------

	protected Vector<Subscriber> subscribers;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new publisher.
	 */
	protected Publisher() {
		subscribers = new Vector<Subscriber>();
	}

	/**
	 * Adds a subscriber to the publisher.
	 *
	 * @param subscriber The subscriber to add.
	 */
	public void subscribe(Subscriber subscriber) {
		subscribers.add(subscriber);
	}

	/**
	 * Removes a subscriber from the publisher.
	 *
	 * @param subscriber The subscriber to remove.
	 */
	public void unsubscribe(Subscriber subscriber) {
		subscribers.remove(subscriber);
	}
}
