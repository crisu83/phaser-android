package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.debug.Logger;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.scene.Level;
import org.cniska.phaser.scene.World;

public class SpaceWorld extends World {

	// Actor identifiers
	public static final int ACTOR_PLAYER = 1;
	public static final int ACTOR_ALIEN = 2;
	public static final int ACTOR_ROCKET = 3;
	public static final int ACTOR_TORPEDO = 4;
	public static final int ACTOR_EXPLOSION = 5;

	// Level identifiers
	public static final int LEVEL_1 = 1;

	protected Player player;

	/**
	 * Creates a new world.
	 *
	 * @param view The game view.
	 */
	public SpaceWorld(GameView view) {
		super(view);
	}

	@Override
	public void init() {
		super.init();

        // todo: figure out how to use large backgrounds in android without affecting the performance too much.
        /*
		Background bg1 = new Background(view, this);
		bg1.loadBitmap(R.drawable.stars_01);

		Background bg2 = new Background(view, this);
		bg2.loadBitmap(R.drawable.stars_02);

		Background bg3 = new Background(view, this);
		bg3.loadBitmap(R.drawable.stars_03);
		*/

		Player player = (Player) createActor(ACTOR_PLAYER);
		view.getTouchHandler().attach(player); // player wants to listen for touch events
	}

	@Override
	public Level createLevel(int id) {
		Level level = null;
		switch (id) {
			case LEVEL_1:
				level = new Level1(view, this);
				break;
			default:
				Logger.error(getClass().getCanonicalName(), "Invalid level id.");
		}
		return level;
	}

	@Override
	public Actor createActor(int id) {
		Actor actor = null;
		switch (id) {
			case ACTOR_PLAYER:
				if (player == null) {
					player = new Player(view, this);
				}

				actor = player;
				break;
			case ACTOR_ALIEN:
				actor = new Alien(view, this);
				break;
			case ACTOR_ROCKET:
				actor = new Rocket(view, this);
				break;
			case ACTOR_TORPEDO:
				actor = new Torpedo(view, this);
				break;
			case ACTOR_EXPLOSION:
				actor = new Explosion(view, this);
				break;
			default:
				Logger.error(getClass().getCanonicalName(), "Invalid actor id.");
		}
		return actor;
	}
}
