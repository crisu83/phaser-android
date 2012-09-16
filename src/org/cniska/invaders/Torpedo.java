package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.scene.World;

public class Torpedo extends SpaceActor {

	/**
	 * Creates a new actor.
	 *
	 * @param view The game view.
	 * @param world The parent world.
	 */
	public Torpedo(GameView view, World world) {
		super(view, world);
		id = 4;
		name = "torpedo";
	}

	@Override
	public void init() {
		super.init();
		loadBitmap(R.drawable.missile_02);
		explosion.loadBitmap(R.drawable.explosion_03);
	}

	@Override
	public boolean collides(Actor other) {
		return other instanceof Player && intersects(other);
	}
}
