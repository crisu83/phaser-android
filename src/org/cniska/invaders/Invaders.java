package org.cniska.invaders;

import android.content.Context;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.scene.World;

public class Invaders extends GameView {

	/**
	 * Creates a new game view.
	 *
	 * @param period  Time between draws (in nanoseconds).
	 * @param context The parent activity.
	 */
	public Invaders(long period, Context context) {
		super(period, context);
	}

	@Override
	public void setup() {
		World world = new SpaceWorld(this);
		director.add("space-world", world);
		director.set("space-world");
	}
}
