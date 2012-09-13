package org.cniska.phaser.node;

import android.graphics.Canvas;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.render.Renderer;
import org.cniska.phaser.render.SpriteRenderer;

import java.util.Hashtable;

public class Director extends Node implements Renderer {

	// Member variables
	// ----------------------------------------

	protected Hashtable<String, Scene> scenes;
	protected Scene currentScene;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new scene manager.
	 *
	 * @param view The game view.
	 */
	public Director(GameView view) {
		super(view);
		scenes = new Hashtable<String, Scene>();
	}

	/**
	 * Adds a scene to the manager.
	 *
	 * @param name The scene name.
	 * @param scene The scene to add.
	 */
	public void add(String name, Scene scene) {
		scenes.put(name, scene);
	}

	/**
	 * Sets the currentScene scene.
	 *
	 * @param name The scene name.
	 */
	public void set(String name) {
		if (scenes.containsKey(name)) {
			currentScene = scenes.get(name);
			currentScene.setup();
		}
	}

	// Overridden methods
	// ----------------------------------------

	@Override
	public void update(Node parent) {
		super.update(parent);

		if (currentScene != null) {
			currentScene.update(this);
		}
	}

	@Override
	public void render(Canvas canvas) {
		if (currentScene != null) {
			SpriteRenderer renderer = currentScene.getRenderer();
			renderer.render(canvas);
		}
	}
}
