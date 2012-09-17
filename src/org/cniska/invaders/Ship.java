package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.scene.World;

public abstract class Ship extends SpaceActor {

	protected int missileCooldown;
	protected long reloadTime;
	protected boolean reloaded = true;

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
		reloadTime = System.nanoTime();
		reloaded = false;
	}

	@Override
	public void update(Updateable parent) {
		if (!removed) {
			super.update(parent);

			if ((System.nanoTime() - reloadTime) > missileCooldown) {
				reloaded = true;
			}
		}
	}
}
