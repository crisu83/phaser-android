package org.cniska.invaders;

import android.view.MotionEvent;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.input.TouchListener;
import org.cniska.phaser.scene.GameScene;

public class Player extends SpaceActor implements TouchListener {

	protected MotionEvent touch;
	protected int missileCooldown;
	protected long reloadTime;
	protected boolean missiles = true;

	/**
	 * Creates a new game object.
	 *
	 * @param view The game view.
	 * @param scene The parent scene.
	 */
	public Player(GameView view, GameScene scene) {
		super(view, scene);
		name = "player";
		missileCooldown = 500 * 1000000; // ms -> ns
	}

	protected void fire() {
		Rocket rocket = new Rocket(view, scene);
		rocket.position(x + (width / 2) - 2, y - 25);
		reloadTime = System.nanoTime();
		missiles = false;
	}

	@Override
	public void init() {
		super.init();
		loadBitmap(R.drawable.ship_01);
		explosion.loadBitmap(R.drawable.explosion_02);
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

		if ((System.nanoTime() - reloadTime) > missileCooldown) {
			missiles = true;
		}
	}

	@Override
	public void onTouch(MotionEvent event) {
		this.touch = event;
	}
}
