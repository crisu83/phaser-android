package org.cniska.phaser.node;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.debug.Debuggable;

import java.util.Vector;

public class Node implements Updateable, Debuggable {

	// Member variables
	// ----------------------------------------

	protected GameView view;
	protected Vector<Node> children;
	protected boolean initialized = false;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new node.
	 */
	public Node(GameView view) {
		this.view = view;
		children = new Vector<Node>();
	}

	/**
	 * Initializes the node.
	 * Override this method to apply initialization logic.
	 */
	public void init() {
	}

	/**
	 * Adds a child node.
	 * @param node The node to add.
	 */
	public void add(Node node) {
		children.add(node);
	}

	/**
	 * Removes a child node.
	 * @param node The node to remove.
	 */
	public void remove(Node node) {
		children.remove(node);
	}

	// Overridden methods
	// ----------------------------------------

	@Override
	public void update(Node parent) {
		// Run initialization logic if necessary.
		if (!initialized) {
			init();
			initialized = true;
		}

		// Update child nodes.
		for (Node child : children) {
			child.update(this);
		}
	}

	/**
	 * Debugs the node.
	 *
	 * @param parent The parent node.
	 * @param canvas The canvas context.
	 */
	public void debug(Node parent, Canvas canvas) {
		for (Node child : children) {
			child.debug(this, canvas);
		}
	}

	// Getters and setters
	// ----------------------------------------

	public GameView getView() {
		return view;
	}
}
