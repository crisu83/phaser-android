package org.cniska.phaser.input;

import android.view.MotionEvent;

public interface TouchListener {
	/**
	 * Called when a touch event is received.
	 *
	 * @param event The event.
	 */
	public void onTouch(MotionEvent event);
}
