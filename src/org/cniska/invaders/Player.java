package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.input.TouchEvent;
import org.cniska.phaser.input.TouchListener;
import org.cniska.phaser.render.Animation;
import org.cniska.phaser.scene.GameScene;

public class Player extends SpaceActor implements TouchListener {

	protected TouchEvent touch;
	protected int missileCooldown;
	protected long fireTime;
	protected boolean missiles = true;

	/**
	 * Creates a new game object.
	 *
	 * @param view The game view.
	 * @param scene The parent scene.
	 */
	public Player(GameView view, GameScene scene) {
		super(view, scene);

		explosionResource = R.drawable.explosion_02;
		missileCooldown = 500 * 1000000; // ms -> ns

		loadBitmap(R.drawable.ship_01);

		Animation animation = new Animation();
		animation.addFrame(0, 0, 100);
		animation.addFrame(20, 0, 100);
		addAnimation("idle", animation);
		playAnimation("idle");

		position(600, 600);
		size(20, 20);
	}

	protected void fire() {
		Rocket rocket = new Rocket(view, scene);
		rocket.position(x + (width / 2) - (rocket.getWidth() / 2), y - 15);
		scene.getWorld().addEntity(rocket);
		fireTime = System.nanoTime();
		missiles = false;
	}

	@Override
	public void input() {
		if (missiles && touch != null) {
			fire();
			touch = null;
		}
	}


	@Override
	public void update(Updateable parent) {
		super.update(parent);

		if ((System.nanoTime() - fireTime) > missileCooldown) {
			missiles = true;
		}
	}

	@Override
	public void onTouch(TouchEvent event) {
		this.touch = event;
	}
}
