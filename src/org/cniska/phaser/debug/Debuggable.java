package org.cniska.phaser.debug;

import android.graphics.Canvas;
import org.cniska.phaser.node.Node;

public interface Debuggable {

	/**
	 * Debugs the object.
	 *
	 * @param parent The parent node.
	 * @param canvas The canvas context.
	 */
	public void debug(Node parent, Canvas canvas);
}
