package org.cniska.phaser.scene;

import org.cniska.phaser.core.GameData;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.node.Node;

public class Level extends Node {

	protected World world;

	/**
	 * Creates a new level.
	 */
	public Level(GameView view, World world) {
		super(view);
		this.world = world;
	}

	@Override
	public void init() {
		super.init();

		if (id > 0) {
			GameData.LevelData data = view.getData().getLevel(id);

			if (data != null) {
				if (data.actors != null) {
					for (int i = 0, len = data.actors.size(); i < len; i++) {
						GameData.ActorLevelData actorLevelData = data.actors.get(i);
						Actor actor = world.createActor(actorLevelData.id);
						actor.position(actorLevelData.x, actorLevelData.y);
					}
				}
			}
		}
	}
}
