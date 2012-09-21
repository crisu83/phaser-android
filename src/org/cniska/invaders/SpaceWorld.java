package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.debug.EntityPanel;
import org.cniska.phaser.debug.Logger;
import org.cniska.phaser.event.Event;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.node.ActorListener;
import org.cniska.phaser.scene.Level;
import org.cniska.phaser.scene.World;

public class SpaceWorld extends World implements ActorListener {

	protected Player player;
	protected ScorePanel scorePanel;

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

		scorePanel = new ScorePanel(view, this);
		addSprite(scorePanel);

		Player player = (Player) createActor(Invaders.ACTOR_PLAYER);
		addActor(player);
	}

	@Override
	public Level createLevel(int id) {
		Level level = null;
		switch (id) {
			case Invaders.LEVEL_1:
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
			case Invaders.ACTOR_PLAYER:
				if (player == null) {
					player = new Player(view, this);
				}
				actor = player;
				break;
			case Invaders.ACTOR_ALIEN:
				actor = new Alien(view, this);
				break;
			case Invaders.ACTOR_ROCKET:
				actor = new Rocket(view, this);
				break;
			case Invaders.ACTOR_TORPEDO:
				actor = new Torpedo(view, this);
				break;
			case Invaders.ACTOR_EXPLOSION:
				actor = new Explosion(view, this);
				break;
			default:
				Logger.error(getClass().getCanonicalName(), "Invalid actor id.");
		}

		if (view.isDebug()) {
			actor.attach(entityPanel);
		}

		actor.attach(scorePanel);

		return actor;
	}

	@Override
	public void onActorDeath(Event event) {
		if (event.getSource() instanceof Player) {
			view.endGame();
		}
	}
}
