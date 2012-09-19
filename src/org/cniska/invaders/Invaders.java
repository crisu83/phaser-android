package org.cniska.invaders;

import android.content.Context;
import org.cniska.phaser.core.GameData;
import org.cniska.phaser.core.GameView;

public class Invaders extends GameView {

	// Actor identifiers
	public static final int ACTOR_PLAYER = 1;
	public static final int ACTOR_ALIEN = 2;
	public static final int ACTOR_ROCKET = 3;
	public static final int ACTOR_TORPEDO = 4;
	public static final int ACTOR_EXPLOSION = 5;

	// Level identifiers
	public static final int LEVEL_1 = 1;

	// Scene identifiers
	public static final int SCENE_GAME = 2;

	protected SpaceData data;

	/**
	 * Creates a new game view.
	 *
	 * @param period  Time between draws (in nanoseconds).
	 * @param context The parent activity.
	 */
	public Invaders(long period, Context context) {
		super(period, context);
	}

	@Override
	public void setup() {
		data = new SpaceData(this);
		data.load();

		director.addScene(SCENE_GAME, new SpaceWorld(this));
		director.setScene(SCENE_GAME);
    }

	@Override
	public GameData getData() {
		return data;
	}
}
