package org.cniska.phaser.node;

import org.cniska.phaser.core.GameView;

import java.util.Hashtable;

public class SceneManager extends Node {

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
	public SceneManager(GameView view) {
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
			view.getRenderer().flush();
			currentScene = scenes.get(name);
			currentScene.setup();
		}
	}

	/**
	 * Removes a scene from the manager.
	 *
	 * @param name The scene name.
	 * @return The removed scene.
	 */
	public Scene remove(String name) {
		return scenes.remove(name);
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

	// Getters and setters
	// ----------------------------------------

	public Scene getCurrentScene() {
		return currentScene;
	}
}
