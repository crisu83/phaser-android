package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.scene.World;

public class Background extends Actor {

	//protected int scrollSpeed;
	protected Player player;

	/**
	 * Creates a new actor.
	 *
	 * @param view  The game view.
	 * @param world The parent world.
	 */
	protected Background(GameView view, World world
	) {
		super(view, world);
		id = 6;
	}

	/*
	@Override
	public void update(Updateable parent) {
		super.update(parent);

		if (player != null && scrollSpeed > 0) {
			if (player.getVx() < 0 && player.getX() >0) {
				vx = scrollSpeed;
			} else if (player.getVx() > 0 && player.x2() < view.getWidth()) {
				vx = -scrollSpeed;
			} else {
				vx = 0;
			}
		}
	}
	*/

	public void setPlayer(Player player) {
		this.player = player;
	}
}
