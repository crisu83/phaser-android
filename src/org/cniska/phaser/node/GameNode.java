package org.cniska.phaser.node;

import org.cniska.phaser.core.GameView;

abstract public class GameNode {

	public interface NodeType {
	};

	// Member variables
	// ----------------------------------------

	private final GameView view;
	private boolean initialized = false;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new node.
	 *
	 * @param view The parent view.
	 */
	public GameNode(GameView view) {
		this.view = view;
	}

	/**
	 * Initializes the node.
	 * Override this method to apply initialization logic.
	 */
	public void init() {
	}

	/**
	 * Updates the node.
	 */
	public void update() {
		if (!initialized) {
			init();
			initialized = true;
		}
	}

	// Getters and setters
	// ----------------------------------------

	public GameView getView() {
		return view;
	}
}
