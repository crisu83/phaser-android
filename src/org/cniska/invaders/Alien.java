package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.scene.World;

import java.util.Random;

public class Alien extends Ship {

	protected static final int MIN_MISSILE_COOLDOWN_MS = 500;
	protected static final int MAX_MISSILE_COOLDOWN_MS = 800;

	protected static final int MAX_OFFSET = 100;

	protected int sx;
	protected boolean torpedos = false;
	protected Random random;

	/**
	 * Creates a new actor.
	 *
	 * @param view The game view.
	 * @param world The parent world.
	 */
	public Alien(GameView view, World world) {
		super(view, world);
		id = 2;
		random = new Random();
		randomizeMissileCooldown();
	}

	protected void randomizeMissileCooldown() {
		missileCooldown = (random.nextInt(MAX_MISSILE_COOLDOWN_MS - MIN_MISSILE_COOLDOWN_MS + 1) + MIN_MISSILE_COOLDOWN_MS) * 1000000; // ms -> ns
	}

	@Override
	public void init() {
		super.init();
		loadBitmap(R.drawable.ship_02);
		explosion.loadBitmap(R.drawable.explosion_04);
	}

	@Override
	protected void fire() {
		super.fire();
		Torpedo torpedo = (Torpedo) world.createActor(4);
		torpedo.position(x + (width / 2) - 2, y + 30);
		randomizeMissileCooldown();
	}

	@Override
	public void update(Updateable parent) {
		if (!removed) {
			super.update(parent);

			if (x > (sx + MAX_OFFSET)) {
				vx = Math.abs(vx) * -1;
				y += 30;
			} else if (x < (sx - MAX_OFFSET)) {
				vx = Math.abs(vx);
				y += 30;
			}

			/*
			if (torpedos && reloaded) {
				fire();
			}
			*/
		}
	}

	@Override
	public boolean collides(Actor other) {
		return other instanceof Rocket && intersects(other);
	}

	public void setSx(int sx) {
		this.sx = sx;
	}

	public void setTorpedos(boolean torpedos) {
		this.torpedos = torpedos;
	}
}
