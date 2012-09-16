package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.scene.World;

public class Alien extends SpaceActor {

	/**
	 * Creates a new actor.
	 *
	 * @param view The game view.
	 * @param world The parent world.
	 */
	public Alien(GameView view, World world) {
		super(view, world);
		id = 2;
		name = "alien";
	}

	@Override
	public void init() {
		super.init();
		loadBitmap(R.drawable.ship_02);
		explosion.loadBitmap(R.drawable.explosion_04);
	}

	@Override
	public boolean collides(Actor other) {
		return other instanceof Rocket && intersects(other);
	}
}
