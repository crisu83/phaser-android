package org.cniska.phaser.node;

import org.cniska.phaser.event.Event;
import org.cniska.phaser.event.Subscriber;

public interface EntityListener extends Subscriber {
	public void onRemove(Event event);
}
