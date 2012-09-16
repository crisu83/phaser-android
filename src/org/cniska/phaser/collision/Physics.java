package org.cniska.phaser.collision;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.event.Event;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.node.EntityListener;
import org.cniska.phaser.node.Node;
import org.cniska.phaser.util.List;

public class Physics extends Node implements EntityListener {

	protected List<Actor> actors;

	public Physics(GameView view) {
		super(view);
		actors = new List<Actor>();
	}

	public void addActor(Actor entity) {
		entity.attach(this);
		actors.add(entity);
	}

	public void removeActor(Actor entity) {
		entity.detach(this);
		actors.remove(entity);
	}

	@Override
	public void update(Updateable parent) {
		super.update(parent);

		actors.update(this);

		for (int i = 0, len = actors.size(); i < len; i++) {
			for (int j = 0; j < len; j++) {
				Actor actor = actors.get(i);
				Actor other = actors.get(j);
				if (!actor.isRemoved() && actor != other && actor.collides(other)) {
					actor.collide(other);
					other.collide(actor);
				}
			}
		}
	}

	@Override
	public void onEntityRemove(Event event) {
		removeActor((Actor) event.getSource());
	}
}
