package org.cniska.pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.SceneManager;
import org.cniska.phaser.debug.ui.MonitorPanel;

public class Pong extends GameView {

	// Member variables
	// ----------------------------------------

	private MonitorPanel monitor;

	// Methods
	// ----------------------------------------

	public Pong(long period, Context context) {
		super(period, context);
	}

	// Overridden methods
	// ----------------------------------------

	@Override
	public void setup() {
		SceneManager sceneManager = getSceneManager();
		sceneManager.add("gameplay", new GamePlay(this));
		sceneManager.set("gameplay");

		monitor = new MonitorPanel(this);
		getGameRoot().add(monitor);
		getRenderer().add(monitor);
	}
}
