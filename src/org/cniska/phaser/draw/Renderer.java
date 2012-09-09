package org.cniska.phaser.draw;

import android.graphics.Canvas;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.GameNode;
import org.cniska.phaser.node.GameObject;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class Renderer extends GameNode {

	// Member variables
	// ----------------------------------------

	private Vector<GameObject> objects;
	private zIndexComparator comparator;
	private boolean sorted = false;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new renderer.
	 *
	 * @param view The parent view.
	 */
	public Renderer(GameView view) {
		super(view);

		objects = new Vector<GameObject>();
		comparator = new zIndexComparator();
	}

	/**
	 * Adds an object to the renderer.
	 *
	 * @param object The object to add.
	 */
	public void add(GameObject object) {
		objects.add(object);
		sorted = false;
	}

	/**
	 * Removes an object from the renderer.
	 *
	 * @param object The object to remove.
	 */
	public void remove(GameObject object) {
		objects.remove(object);
	}

	/**
	 * Renders the visible objects.
	 *
	 * @param canvas The canvas.
	 */
	public void render(Canvas canvas) {
		for (GameObject obj : objects) {
			if (obj.isVisible())
				obj.draw(canvas);
		}
	}

	/**
	 * Sorts the objects using the z-index comparator.
	 */
	private void sort() {
		Collections.sort(objects, comparator);
	}

	// Overridden methods
	// ----------------------------------------

	@Override
	public void update() {
		super.update();

		// Sort objects if necessary.
		if (!sorted) {
			sort();
			sorted = true;
		}
	}

	// Inner classes
	// ----------------------------------------

	private class zIndexComparator implements Comparator<GameObject> {

		@Override
		public int compare(GameObject lhs, GameObject rhs) {
			return lhs.getzIndex() - rhs.getzIndex();
		}
	}
}
