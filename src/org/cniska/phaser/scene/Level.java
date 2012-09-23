package org.cniska.phaser.scene;

import org.cniska.phaser.core.GameData;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.event.Event;
import org.cniska.phaser.event.Subscriber;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.node.Node;

public class Level extends Node {

	// Member variables
	// ----------------------------------------

	protected World world;
	protected boolean ended;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new level.
	 */
	public Level(GameView view, World world) {
		super(view);
		this.world = world;
	}

	/**
	 * Ends the level.
	 */
	public void end() {
		notify(new Event("level:end", this));
		ended = true;
	}

	// Overridden methods
	// ----------------------------------------

	@Override
	protected void init() {
		super.init();
		notify(new Event("level:start", this));
	}

	@Override
	protected void initData() {
		super.initData();

		GameData.LevelData data = view.getData().getLevel(id);

		if (data != null) {
			if (data.actors != null) {
				for (int i = 0, len = data.actors.size(); i < len; i++) {
					GameData.LevelActorData levelActorData = data.actors.get(i);
					Actor actor = world.createActor(levelActorData.id);
					actor.position(levelActorData.x, levelActorData.y);
					world.addActor(actor);
				}
			}
		}
	}

	@Override
	public void notify(Event event) {
		for (int i = 0, len = subscribers.size(); i < len; i++) {
			Subscriber subscriber = subscribers.get(i);

			if (subscriber instanceof LevelListener) {
				if (event.getAction() == "level:start") {
					((LevelListener) subscribers.get(i)).onLevelStart(event);
				} else if (event.getAction() == "level:end") {
					((LevelListener) subscribers.get(i)).onLevelEnd(event);
				}
			}
		}
	}

	// Getters and setters
	// ----------------------------------------

	public boolean isEnded() {
		return ended;
	}
}
