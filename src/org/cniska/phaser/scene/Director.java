package org.cniska.phaser.scene;

import android.graphics.Canvas;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.debug.Debuggable;
import org.cniska.phaser.node.Node;
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
	 * Creates a new director.
	 *
	 * @param view The game view.
	 */
	public Director(GameView view) {
		super(view);
		name = "director";
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
	 * Sets the current scene.
	 *
	 * @param name The scene name.
	 */
	public void set(String name) {
		if (scenes.containsKey(name)) {
			currentScene = scenes.get(name);
			//currentScene.setup();
		}
	}

	// Overridden methods
	// ----------------------------------------

	@Override
	public void update(Updateable parent) {
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

	@Override
	public void debug(Debuggable parent, Canvas canvas) {
		super.debug(parent, canvas);

		if (currentScene != null) {
			currentScene.debug(this, canvas);
		}
	}
}
