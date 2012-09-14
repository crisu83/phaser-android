package org.cniska.phaser.input;

import android.view.MotionEvent;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.event.Subscriber;
import org.cniska.phaser.node.Node;

public class TouchHandler extends Node {

	// Methods
	// ----------------------------------------

	public TouchHandler(GameView view) {
		super(view);
	}

	public void register(MotionEvent motion) {
		TouchEvent event = new TouchEvent("touch", this);
		event.setMotion(motion);
		notify(event);
	}

	public void notify(TouchEvent event) {
		for (int i = 0, len = subscribers.size(); i < len; i++) {
			Subscriber subscriber = subscribers.get(i);

			if (subscriber instanceof TouchListener) {
				if (event.getAction() == "touch") {
					((TouchListener) subscribers.get(i)).onTouch(event);
				}
			}
		}
	}
}
