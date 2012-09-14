package org.cniska.phaser.collision;

import org.cniska.phaser.event.Event;
import org.cniska.phaser.event.Publisher;
import org.cniska.phaser.node.Entity;

public class CollisionEvent extends Event {

	protected Entity other;

	public CollisionEvent(String action, Publisher source) {
		super(action, source);
	}

	public Entity getOther() {
		return other;
	}

	public void setOther(Entity other) {
		this.other = other;
	}
}
