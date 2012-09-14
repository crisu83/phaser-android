package org.cniska.phaser.node;

import android.graphics.Canvas;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.debug.Debuggable;
import org.cniska.phaser.event.Event;
import org.cniska.phaser.event.Publisher;
import org.cniska.phaser.util.List;

public class Node extends Publisher implements Debuggable, EntityListener {

	// Member variables
	// ----------------------------------------

	protected GameView view;
	protected List<Node> children;
	protected boolean initialized = false;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new node.
	 */
	public Node(GameView view) {
		this.view = view;
		children = new List<Node>();
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
	public void addNode(Node node) {
		node.attach(this);
		children.add(node);
	}

	/**
	 * Removes a child node.
	 * @param node The node to remove.
	 */
	public void removeNode(Node node) {
		node.detach(this);
		children.remove(node);
	}

	// Overridden methods
	// ----------------------------------------

	@Override
	public void update(Updateable parent) {
		super.update(parent);

		// Run initialization logic if necessary.
		if (!initialized) {
			init();
			initialized = true;
		}

		children.update(this);

		// Update child nodes.
		for (int i = 0, len = children.size(); i < len; i++) {
			children.get(i).update(this);
		}
	}

	@Override
	public void onEntityRemove(Event event) {
		removeNode((Node) event.getSource());
	}

	@Override
	public void debug(Node parent, Canvas canvas) {
		for (int i = 0, len = children.size(); i < len; i++) {
			children.get(i).debug(this, canvas);
		}
	}

	// Getters and setters
	// ----------------------------------------

	public GameView getView() {
		return view;
	}
}
