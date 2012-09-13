package org.cniska.phaser.draw;

import android.graphics.Canvas;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.Sprite;
import org.cniska.phaser.node.Node;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class Renderer extends Node {

	// Member variables
	// ----------------------------------------

	protected Vector<Sprite> sprites;
	protected zIndexComparator comparator;
	protected boolean sorted = false;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new renderer.
	 *
	 * @param view The game view.
	 */
	public Renderer(GameView view) {
		super(view);

		sprites = new Vector<Sprite>();
		comparator = new zIndexComparator();
	}

	/**
	 * Adds an sprite to the renderer.
	 *
	 * @param sprite The sprite to add.
	 */
	public void add(Sprite sprite) {
		sprites.add(sprite);
		sorted = false;
	}

	/**
	 * Removes an sprite from the renderer.
	 *
	 * @param sprite The sprite to remove.
	 */
	public void remove(Sprite sprite) {
		sprites.remove(sprite);
	}

	/**
	 * Renders the visible sprites.
	 *
	 * @param canvas The canvas.
	 */
	public void render(Canvas canvas) {
		for (Sprite sprite : sprites) {
			if (sprite.isVisible()) {
				sprite.draw(canvas);
			}
		}
	}

	/**
	 * Empties the renderer.
	 */
	public void flush() {
		sprites.clear();
	}

	/**
	 * Sorts the sprites using the z-index comparator.
	 */
	protected void sort() {
		Collections.sort(sprites, comparator);
	}

	// Overridden methods
	// ----------------------------------------

	@Override
	public void update(Node parent) {
		super.update(parent);

		// Sort sprites if necessary.
		if (!sorted) {
			sort();
			sorted = true;
		}
	}

	// Inner classes
	// ----------------------------------------

	protected class zIndexComparator implements Comparator<Sprite> {

		@Override
		public int compare(Sprite lhs, Sprite rhs) {
			return lhs.getzIndex() - rhs.getzIndex();
		}
	}
}
