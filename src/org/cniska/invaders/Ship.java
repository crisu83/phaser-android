package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.scene.World;

public abstract class Ship extends SpaceActor {

	protected long missileCooldown;
	protected long missileTime = 0;
	protected boolean reloading = true;

	/**
	 * Creates a new actor.
	 *
	 * @param view  The game view.
	 * @param world The parent world.
	 */
	protected Ship(GameView view, World world) {
		super(view, world);
	}

	protected void fire() {
		missileTime = System.nanoTime();
	}

	@Override
	public void init() {
		super.init();
		missileTime = System.nanoTime();
	}

	@Override
	public void update(Updateable parent) {
		if (!removed) {
			super.update(parent);

			long timeDelta = System.nanoTime() - missileTime;
			if (timeDelta > missileCooldown) {
				reloading = false;
			}
		}
	}
}
