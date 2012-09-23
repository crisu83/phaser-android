package org.cniska.phaser.scene;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.debug.MonitorPanel;
import org.cniska.phaser.node.Node;
import org.cniska.phaser.node.Sprite;
import org.cniska.phaser.render.SpriteRenderer;
import org.cniska.phaser.ui.Element;

abstract public class Scene extends Node {

	// Member variables
	// ----------------------------------------

	protected MonitorPanel monitorPanel;
	protected SpriteRenderer renderer;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new scene.
	 *
	 * @param view The game view.
	 */
	public Scene(GameView view) {
		super(view);
		renderer = new SpriteRenderer(view);
		addNode(renderer);
	}

	/**
	 * Adds a sprite to the scene.
	 *
	 * @param sprite The sprite to add.
	 */
	public void addSprite(Sprite sprite) {
		addNode(sprite);
		renderer.addSprite(sprite);
	}

	/**
	 * Adds an ui element to the scene.
	 *
	 * @param element The element to add.
	 */
	public void addElement(Element element) {
		addSprite(element);
	}

	// Overridden methods
	// ----------------------------------------

	@Override
	protected void init() {
		super.init();

		monitorPanel = new MonitorPanel(view, this);
		addSprite(monitorPanel);
	}

	// Getters and setters
	// ----------------------------------------

	public SpriteRenderer getRenderer() {
		return renderer;
	}
}
