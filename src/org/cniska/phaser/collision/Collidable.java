package org.cniska.phaser.collision;

import org.cniska.phaser.event.Subscriber;
import org.cniska.phaser.node.Entity;

public interface Collidable extends Subscriber {

	public boolean collides(Entity other);

	public void onCollision(CollisionEvent event);
}
