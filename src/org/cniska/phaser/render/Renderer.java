package org.cniska.phaser.render;

import android.graphics.Canvas;

public interface Renderer {

	/**
	 * Renders the content onto the canvas.
	 *
	 * @param canvas The canvas context.
	 */
	public void render(Canvas canvas);
}
