package org.cniska.phaser.collision;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.event.Event;
import org.cniska.phaser.node.Entity;
import org.cniska.phaser.node.EntityListener;
import org.cniska.phaser.node.Node;
import org.cniska.phaser.util.List;

public class CollisionHandler extends Node implements EntityListener {

	protected List<Entity> entities;

	public CollisionHandler(GameView view) {
		super(view);
		entities = new List<Entity>();
	}

	public void addEntity(Entity entity) {
		entity.attach(this);
		entities.add(entity);
	}

	public void removeEntity(Entity entity) {
		entity.detach(this);
		entities.remove(entity);
	}

	@Override
	public void onRemove(Event event) {
		removeEntity((Entity) event.getSource());
	}

	@Override
	public void update(Updateable parent) {
		super.update(parent);

		entities.update(this);

		for (int i = 0, len = entities.size(); i < len; i++) {
			for (int j = 0; j < len; j++) {
				Entity entity = entities.get(i);
				Entity other = entities.get(j);
				if (!entity.isRemoved() && entity != other && entity.intersects(other) && entity.collides(other)) {
					CollisionEvent event = new CollisionEvent("collision", this);
					event.setOther(other);
					entity.onCollision(event);
				}
			}
		}
	}
}
