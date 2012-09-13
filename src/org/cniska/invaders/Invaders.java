package org.cniska.invaders;

import android.content.Context;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.debug.ui.MonitorPanel;
import org.cniska.phaser.node.SceneManager;

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
		SceneManager sceneManager = getSceneManager();
		sceneManager.add("gameplay", new GamePlay(this));
		sceneManager.set("gameplay");
	}
}
