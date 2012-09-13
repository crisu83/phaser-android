package org.cniska.phaser.node;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.debug.Logger;
import org.cniska.phaser.render.Animation;
import org.cniska.phaser.render.Drawable;

import java.util.Hashtable;

public abstract class Sprite extends Entity implements Drawable {

	// Member variables
	// ----------------------------------------

	protected Bitmap bitmap;
	protected Animation currentAnimation;
	protected Hashtable<String, Animation> animations;
	protected int zIndex = 0;
	protected boolean visible = true;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new sprite.
	 *
	 * @param view The game view.
	 */
	protected Sprite(GameView view) {
		super(view);
		animations = new Hashtable<String, Animation>();
	}

	/**
	 * Loads the bitmap with the given resource id.
	 *
	 * @param resourceId The resource id.
	 */
	public void loadBitmap(int resourceId) {
		bitmap = view.getImageLoader().load(resourceId);
	}

	/**
	 * Adds an animation to the sprite.
	 *
	 * @param name The animation name.
	 * @param animation The animation itself.
	 */
	public void addAnimation(String name, Animation animation) {
		animations.put(name, animation);
	}

	/**
	 * Plays the given animation.
	 *
	 * @param name The animation name.
	 */
	public void playAnimation(String name) {
		if (animations.containsKey(name)) {
			currentAnimation = animations.get(name);
			currentAnimation.play();
		}
	}

	/**
	 * Returns the bitmap offset on the x-axis.
	 *
	 * @return The offset in pixels.
	 */
	protected int ox() {
		return currentAnimation != null ? currentAnimation.ox() : 0;
	}

	/**
	 * Returns the bitmap offset on the y-axis.
	 *
	 * @return The offset in pixels.
	 */
	protected int oy() {
		return currentAnimation != null ? currentAnimation.oy() : 0;
	}

	// Overridden methods
	// ----------------------------------------

	@Override
	public void update(Node parent) {
		super.update(parent);

		if (currentAnimation != null) {
			currentAnimation.update(this);
		}
	}

	@Override
	public void draw(Canvas canvas) {
		if (bitmap != null) {
			Logger.debug(getClass().getCanonicalName(), "ox: " + ox() + ", oy: " + oy());
			Rect src = new Rect(ox(), oy(), ox() + width, oy() + height);
			Rect dst = new Rect(x, y, x2(), y2());
			canvas.drawBitmap(bitmap, src, dst, null);
		}
	}

	// Getters and setters
	// ----------------------------------------

	public int getzIndex() {
		return zIndex;
	}

	public void setzIndex(int zIndex) {
		this.zIndex = zIndex;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
