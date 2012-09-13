package org.cniska.phaser.draw;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import org.cniska.phaser.core.GameView;

public class ImageLoader {

	// Member variables
	// ----------------------------------------

	protected GameView view;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new image loader.
	 *
	 * @param view The game view.
	 */
	public ImageLoader(GameView view) {
		this.view = view;
	}

	/**
	 * Loads the bitmap with the given resource id.
	 * @param resourceId The resource id.
	 * @return The bitmap.
	 */
	public Bitmap load(int resourceId) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inScaled = false; // prevent scaling of images
		return BitmapFactory.decodeResource(view.getResources(), resourceId, options);
	}
}
