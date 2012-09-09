package org.cniska.phaser.input;

import android.view.MotionEvent;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.GameNode;
import org.cniska.phaser.node.GameObject;

import java.util.Vector;

public class TouchHandler extends GameNode {

	private Vector<TouchListener> listeners;

	/**
	 * Creates a new input manager.
	 *
	 * @param view The parent view.
	 */
	public TouchHandler(GameView view) {
		super(view);

		listeners = new Vector<TouchListener>();
	}

	public void addListener(TouchListener listener) {
		listeners.add(listener);
	}

	public void removeListener(TouchListener listener) {
		listeners.remove(listener);
	}

	public void onTouch(MotionEvent event) {
		for (TouchListener obj : listeners)
			obj.onTouch(event);
	}
}
