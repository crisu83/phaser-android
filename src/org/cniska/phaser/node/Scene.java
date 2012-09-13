package org.cniska.phaser.node;

import android.graphics.Canvas;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.draw.Drawable;
import org.cniska.phaser.draw.Renderer;
import org.cniska.phaser.input.TouchListener;

abstract public class Scene extends Node {

	// Member variables
	// ----------------------------------------

	protected Renderer renderer;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new scene.
	 *
	 * @param view The game view.
	 */
	public Scene(GameView view) {
		super(view);
		renderer = new Renderer(view);
		add(renderer);
	}

	// Abstract methods
	// ----------------------------------------

	/**
	 * Setups the scene.
	 */
	public abstract void setup();
}
