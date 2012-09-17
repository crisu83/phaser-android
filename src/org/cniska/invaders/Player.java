package org.cniska.invaders;

import android.hardware.SensorManager;
import android.view.MotionEvent;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.input.TouchListener;
import org.cniska.phaser.scene.World;

public class Player extends SpaceActor implements TouchListener {

	protected MotionEvent touch;
	protected int missileCooldown;
	protected long reloadTime;
	protected boolean missiles = true;

	/**
	 * Creates a new game object.
	 *
	 * @param view The game view.
	 * @param world The parent world.
	 */
	public Player(GameView view, World world) {
		super(view, world);
		id = 1;
		name = "player";
		missileCooldown = 1000 * 1000000; // ms -> ns
	}

	protected void fire() {
		Rocket rocket = (Rocket) world.createActor(3);
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
		if (touch != null) {
            x = (int) touch.getX() - 10;

            if (missiles) {
                fire();
            }

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
