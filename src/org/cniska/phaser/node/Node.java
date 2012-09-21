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

	protected int id;
	protected String name;
	protected List<Node> children;
	protected GameView view;
	protected boolean initialized = false;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new node.
	 */
	public Node(GameView view) {
		this.view = view;
		name = "";
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
	 *
	 * @param node The node to add.
	 */
	public void addNode(Node node) {
		node.attach(this);
		children.add(node);
	}

	/**
	 * Returns a child node with the given name.
	 *
	 * @param name The node name.
	 * @return The node.
	 */
	public Node getNode(String name) {
		for (int i = 0, len = children.size(); i < len; i++) {
			Node node = children.get(i);
			if (node.getName() == name) {
				return node;
			}
		}
		return null;
	}

	/**
	 * Looks up a child node recursively with the given name.
	 *
	 * @param name The node name.
	 * @return The node.
	 */
	public Node lookup(String name) {
		Node node = getNode(name);
		if (node == null) {
			for (int i = 0, len = children.size(); i < len; i++) {
				node = children.get(i).lookup(name);
				if (node != null) {
					return node;
				}
			}
		}
		return null;
	}

	/**
	 * Removes a child node.
	 *
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
	public void onEntityInit(Event event) {
	}

	@Override
	public void onEntityRemove(Event event) {
		removeNode((Node) event.getSource());
	}

	@Override
	public void debug(Debuggable parent, Canvas canvas) {
		for (int i = 0, len = children.size(); i < len; i++) {
			children.get(i).debug(this, canvas);
		}
	}

	// Getters and setters
	// ----------------------------------------

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Node> getChildren() {
		return children;
	}

	public GameView getView() {
		return view;
	}
}
