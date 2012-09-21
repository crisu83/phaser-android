package org.cniska.invaders;

import android.view.MotionEvent;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.input.TouchListener;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.scene.World;

import java.util.Random;

public class Player extends Ship implements TouchListener {

	public static final int DEFAULT_VELOCITY = 5;

	protected MotionEvent touch;

	/**
	 * Creates a new game object.
	 *
	 * @param view The game view.
	 * @param world The parent world.
	 */
	public Player(GameView view, World world) {
		super(view, world);
		id = Invaders.ACTOR_PLAYER;
		missileCooldown = 1000 * 1000000; // ms -> ns
	}

	protected void fire() {
		super.fire();
		Rocket rocket = (Rocket) world.createActor(Invaders.ACTOR_ROCKET);
		rocket.position(x + (width / 2) - 2, y - 30);
		world.addActor(rocket);
	}

	@Override
	public void init() {
		super.init();
		position((view.getWidth() / 2) - 10, view.getHeight() - 100);
		loadBitmap(R.drawable.ship_01);
		explosion.loadBitmap(R.drawable.explosion_02);
		view.getTouchHandler().attach(this);
	}

	@Override
	public void input() {
		if (touch != null) {
			switch (touch.getAction()) {
				case MotionEvent.ACTION_DOWN:
					int tx = (int) touch.getX();
					if (tx < x) { // touch on the left side of the player
						vx = -DEFAULT_VELOCITY;
						playAnimation("tiltLeft");
					} else if (tx > x) { // touch on the right side of the player
						vx = DEFAULT_VELOCITY;
						playAnimation("tiltRight");
					}
					break;

				case MotionEvent.ACTION_UP:
					playAnimation("idle");
					vx = 0;
					break;

				default:
					// We do not care about other events for now...
			}

            touch = null; // event handled
		}
	}

	@Override
	public void update(Updateable parent) {
		if (!removed) {
			super.update(parent);

			// Prevent the player from leaving the screen.
			if (x < 0) {
				x = 1;
			} else if (x > (view.getWidth() - width)) {
				x = view.getWidth() - width - 1;
			}

			if (!reloading) {
				fire();
				reloading = true;
			}
		}
	}

	@Override
	public boolean collides(Actor other) {
		return other instanceof Torpedo && intersects(other);
	}

	@Override
	public void onTouch(MotionEvent event) {
	    this.touch = event;
	}
}
