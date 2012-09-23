package org.cniska.phaser.node;

import android.graphics.Canvas;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.debug.Debuggable;
import org.cniska.phaser.event.Event;
import org.cniska.phaser.event.Publisher;
import org.cniska.phaser.event.Subscriber;
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
	protected void init() {
		if (id > 0) {
			initData();
		}
		notify(new Event("node:init", this));
	}

	/**
	 * Uninitialized the node.
	 * Override this method to apply uninitialization logic.
	 */
	public void uninit() {
		notify(new Event("node:uninit", this));
	}

	/**
	 * Adds a child node.
	 *
	 * @param node The node to add.
	 */
	public void addNode(Node node) {
		node.subscribe(this);
		children.add(node);
	}

	/**
	 * Removes a child node.
	 *
	 * @param node The node to remove.
	 */
	public void removeNode(Node node) {
		node.unsubscribe(this);
		children.remove(node);
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
	 * Initializes the entity data.
	 * Override this method to add data initialization logic.
	 */
	protected void initData() {
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

		// Update child nodes.
		children.update(this);
		for (int i = 0, len = children.size(); i < len; i++) {
			children.get(i).update(this);
		}
	}

	@Override
	public void notify(Event event) {
		super.notify(event);

		for (int i = 0, len = subscribers.size(); i < len; i++) {
			Subscriber subscriber = subscribers.get(i);

			if (subscriber instanceof NodeListener) {
				if (event.getAction() == "node:init") {
					((NodeListener) subscribers.get(i)).onNodeInit(event);
				} else if (event.getAction() == "node:uninit") {
					((NodeListener) subscribers.get(i)).onNodeUninit(event);
				}
			}
		}
	}

	// Interface methods
	// ----------------------------------------

	@Override
	public void debug(Debuggable parent, Canvas canvas) {
		for (int i = 0, len = children.size(); i < len; i++) {
			children.get(i).debug(this, canvas);
		}
	}

	// Event handlers
	// ----------------------------------------

	@Override
	public void onEntityCreate(Event event) {
	}

	@Override
	public void onEntityRemove(Event event) {
		removeNode((Node) event.getSource());
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
