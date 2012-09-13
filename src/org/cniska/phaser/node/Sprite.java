package org.cniska.phaser.node;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.draw.Animation;
import org.cniska.phaser.draw.Drawable;

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

	public void addAnimation(String name, Animation animation) {
		animations.put(name, animation);
	}

	public void playAnimation(String name) {
		if (animations.containsKey(name)) {
			currentAnimation = animations.get(name);
			currentAnimation.play();
		}
	}

	/**
	 * Loads the bitmap with the given resource id.
	 *
	 * @param resourceId The resource id.
	 */
	public void loadBitmap(int resourceId) {
		bitmap = view.getImageLoader().load(resourceId);
	}

	public int bitmapX() {
		return currentAnimation != null ? currentAnimation.bitmapX() : 0;
	}

	public int bitmapY() {
		return currentAnimation != null ? currentAnimation.bitmapY() : 0;
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
			Rect src = new Rect(bitmapX(), bitmapY(), bitmapY() + width, bitmapY() + height);
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
