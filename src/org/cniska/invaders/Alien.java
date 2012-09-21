package org.cniska.invaders;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.debug.Debuggable;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.scene.World;

import java.util.Random;

public class Alien extends Ship {

	protected static final int MAX_OFFSET = 200;

	protected int sx;
	protected int index;
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
		id = Invaders.ACTOR_ALIEN;
		random = new Random(System.nanoTime());
		missileCooldown = randomizeCooldown();
	}

	protected void fire() {
		super.fire();
		Torpedo torpedo = (Torpedo) world.createActor(Invaders.ACTOR_TORPEDO);
		torpedo.position(x + (width / 2) - 2, y + 30);
		world.addActor(torpedo);
	}

	protected long randomizeCooldown() {
		long rand = random.nextInt(10000 - 2001) + 2000;
		return rand * 1000000;
	}

	@Override
	public void init() {
		super.init();
		loadBitmap(R.drawable.ship_02);
		explosion.loadBitmap(R.drawable.explosion_04);
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

			if (torpedos && !reloading) {
				fire();
				missileCooldown = randomizeCooldown();
				reloading = true;
			}
		}
	}

	@Override
	public void debug(Debuggable parent, Canvas canvas) {
		super.debug(parent, canvas);

		if (torpedos) {
			Paint paint = new Paint();
			paint.setColor(Color.YELLOW);
			paint.setStyle(Paint.Style.STROKE);
			canvas.drawRect(x, y, x2(), y2(), paint);
		}
	}

	@Override
	public boolean collides(Actor other) {
		return other instanceof Rocket && intersects(other);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setSx(int sx) {
		this.sx = sx;
	}

	public void setTorpedos(boolean torpedos) {
		this.torpedos = torpedos;
	}
}
