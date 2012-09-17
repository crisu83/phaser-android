package org.cniska.phaser.render;

import android.graphics.Canvas;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.event.Event;
import org.cniska.phaser.node.EntityListener;
import org.cniska.phaser.node.Node;
import org.cniska.phaser.node.Sprite;
import org.cniska.phaser.util.SortedList;

import java.util.Comparator;

public class SpriteRenderer extends Node implements Renderer, EntityListener {

	// Member variables
	// ----------------------------------------

	protected SortedList<Sprite> sprites;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new renderer.
	 *
	 * @param view The game view.
	 */
	public SpriteRenderer(GameView view) {
		super(view);
		name = "sprite-renderer";
		sprites = new SortedList<Sprite>(new zIndexComparator());
	}

	/**
	 * Adds an sprite to the renderer.
	 *
	 * @param sprite The sprite to add.
	 */
	public void addSprite(Sprite sprite) {
		sprite.attach(this);
		sprites.add(sprite);
	}

	/**
	 * Removes an sprite from the renderer.
	 *
	 * @param sprite The sprite to remove.
	 */
	public void removeSprite(Sprite sprite) {
		sprite.detach(this);
		sprites.remove(sprite);
	}

	/**
	 * Renders the visible sprites.
	 *
	 * @param canvas The canvas.
	 */
	public void render(Canvas canvas) {
		for (int i = 0, len = sprites.size(); i < len; i++) {
			Sprite sprite = sprites.get(i);
			if (sprite.isVisible()) {
				sprite.draw(canvas);
			}
		}
	}

	// Overridden methods
	// ----------------------------------------

	@Override
	public void update(Updateable parent) {
		super.update(parent);

		sprites.update(this);
	}

	@Override
	public void onEntityRemove(Event event) {
		removeSprite((Sprite) event.getSource());
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
