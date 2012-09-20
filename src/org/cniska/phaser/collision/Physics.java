package org.cniska.phaser.collision;

import android.graphics.Canvas;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.debug.Debuggable;
import org.cniska.phaser.event.Event;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.node.EntityListener;
import org.cniska.phaser.node.Node;
import org.cniska.phaser.util.List;
import org.cniska.phaser.util.QuadTree;
import org.cniska.phaser.util.QuadTreeable;

import java.util.ArrayList;

public class Physics extends Node implements EntityListener {

	protected List<Actor> actors;
	protected QuadTree quadTree;

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
	public void init() {
		super.init();
		quadTree = new QuadTree(0, 0, view.getWidth(), view.getHeight(), 2, 4);
	}

	@Override
	public void update(Updateable parent) {
		super.update(parent);

		int i, len;

		actors.update(this);

		quadTree.flush();
		for (i = 0, len = actors.size(); i < len; i++) {
			quadTree.add(actors.get(i));
		}

		for (i = 0, len = actors.size(); i < len; i++) {
			Actor actor = actors.get(i);

			if (!actor.isRemoved()) {
				ArrayList<QuadTreeable> others = quadTree.getNeighbours(actor);

				for (int j = 0, len2 = others.size(); j < len2; j++) {
					Actor other = (Actor) others.get(j);
					if (!other.isRemoved() && actor.collides(other)) {
						actor.collide(other);
						other.collide(actor);
					}
				}
			}
		}
	}

	@Override
	public void debug(Debuggable parent, Canvas canvas) {
		super.debug(parent, canvas);

		if (quadTree != null) {
			quadTree.debug(this, canvas);
		}
	}

	@Override
	public void onEntityRemove(Event event) {
		removeActor((Actor) event.getSource());
	}
}
