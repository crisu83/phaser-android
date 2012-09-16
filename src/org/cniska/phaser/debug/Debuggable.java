package org.cniska.phaser.debug;

import android.graphics.Canvas;

public interface Debuggable {

	/**
	 * Debugs the object.
	 *
	 * @param parent The parent.
	 * @param canvas The canvas context.
	 */
	public void debug(Debuggable parent, Canvas canvas);
}
