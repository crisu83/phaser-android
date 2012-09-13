package org.cniska.pong;

import android.content.Context;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.Director;

public class Pong extends GameView {

	// Member variables
	// ----------------------------------------

	// Methods
	// ----------------------------------------

	public Pong(long period, Context context) {
		super(period, context);
	}

	// Overridden methods
	// ----------------------------------------

	@Override
	public void setup() {
		Director director = getDirector();
		director.add("gameplay", new GamePlay(this));
		director.set("gameplay");
	}
}
