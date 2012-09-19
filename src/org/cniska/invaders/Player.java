package org.cniska.invaders;

import android.view.MotionEvent;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.input.TouchListener;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.scene.World;

public class Player extends Ship implements TouchListener {

	public static final int DEFAULT_VELOCITY = 5;

	protected MotionEvent touch;
	protected int tx = -1;
	protected boolean shoot = false;
	protected boolean left = false;
	protected boolean right = false;

	/**
	 * Creates a new game object.
	 *
	 * @param view The game view.
	 * @param world The parent world.
	 */
	public Player(GameView view, World world) {
		super(view, world);
		id = Invaders.ACTOR_PLAYER;
		missileCooldown = 800 * 1000000; // ms -> ns
	}

	protected void fire() {
		super.fire();
		Rocket rocket = (Rocket) world.createActor(3);
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
			if (tx == -1) {
				tx = (int) touch.getX() - 10;

				if (tx < x) {
					vx = -DEFAULT_VELOCITY;
					playAnimation("tiltLeft");
					left = true;
				} else {
					vx = DEFAULT_VELOCITY;
					playAnimation("tiltRight");
					right = true;
				}
			}

            if (reloaded) {
				shoot = true;
            }

            touch = null;
		}
	}

	@Override
	public void update(Updateable parent) {
		if (!removed) {
			super.update(parent);

			if (left && x < tx || right && x > tx) {
				tx = -1;
				vx = 0;
				playAnimation("idle");
				left = right = false;
			}

			if (reloaded && shoot) {
				fire();
				shoot = false;
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
