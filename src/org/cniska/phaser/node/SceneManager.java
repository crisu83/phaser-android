package org.cniska.phaser.node;

import android.graphics.Canvas;
import android.view.MotionEvent;
import org.cniska.phaser.core.GameView;

import java.util.Hashtable;

public class SceneManager extends GameNode {

	// Member variables
	// ----------------------------------------

	private Hashtable<NodeType, GameScene> scenes;
	private GameScene current;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new scene manager.
	 *
	 * @param view The parent view.
	 */
	public SceneManager(GameView view) {
		super(view);

		scenes = new Hashtable<NodeType, GameScene>();
	}

	/**
	 * Adds a scene to the manager.
	 *
	 * @param type Type of scene.
	 * @param scene The scene to add.
	 * @return The added scene.
	 */
	public GameScene add(NodeType type, GameScene scene) {
		return scenes.put(type, scene);
	}

	/**
	 * Sets the current scene.
	 *
	 * @param type Type of scene.
	 */
	public void set(NodeType type) {
		if (scenes.containsKey(type))
			current = scenes.get(type);
	}

	/**
	 * Removes a scene from the manager.
	 *
	 * @param type Type of scene.
	 * @return The removed scene.
	 */
	public GameScene remove(NodeType type) {
		return scenes.remove(type);
	}

	// Overridden methods
	// ----------------------------------------

	@Override
	public void update() {
		if (current != null)
			current.update();
	}
}
