package org.cniska.invaders.world;

import org.cniska.invaders.Invaders;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.debug.Logger;
import org.cniska.phaser.event.Event;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.node.ActorListener;
import org.cniska.phaser.scene.Level;
import org.cniska.phaser.scene.World;
import org.cniska.phaser.ui.Flash;

public class SpaceWorld extends World implements ActorListener {

	public static final int READY_TIME_MS = 1000;
	public static final int END_TIME_MS = 5000;

	protected Player player;
	protected ScorePanel scorePanel;
	protected Flash readyFlash, endFlash;
	protected long readyTime = 0L;
	protected long endTime = 0L;

	/**
	 * Creates a new world.
	 *
	 * @param view The game view.
	 */
	public SpaceWorld(GameView view) {
		super(view);
	}

	@Override
	protected void init() {
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

		loadLevel(Invaders.LEVEL_1);

		readyTime = System.nanoTime();
		readyFlash = new Flash("GET READY!", view, this);
		addElement(readyFlash);
	}

	@Override
	public void update(Updateable parent) {
		super.update(parent);

		// Remove the "Get ready" text after a while.
		if (readyTime > 0 && (System.nanoTime() - readyTime) > READY_TIME_MS * 1000000L) { // ms -> ns
			readyFlash.remove();
		}

		// Wait a bit before ending the actual game.
		if (endTime > 0 && (System.nanoTime() - endTime) > END_TIME_MS * 1000000L) { // ms -> ns
			endFlash.remove();
			view.endGame();
		}
	}

	@Override
	public Level levelFactory(int id) {
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
	public Actor actorFactory(int id) {
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
		return actor;
	}

	@Override
	protected void afterCreateActor(Actor actor) {
		super.afterCreateActor(actor);
		actor.subscribe(scorePanel);
	}

	@Override
	public void onActorBirth(Event event) {
	}

	@Override
	public void onActorDeath(Event event) {
		if (event.getSource() instanceof Player) {
			endTime = System.nanoTime();
			endFlash = new Flash("GAME OVER!", view, this);
			addElement(endFlash);
		}
	}
}
