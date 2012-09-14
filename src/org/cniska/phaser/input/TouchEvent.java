package org.cniska.phaser.input;

import android.view.MotionEvent;
import org.cniska.phaser.event.Event;
import org.cniska.phaser.event.Publisher;

public class TouchEvent extends Event {

	protected MotionEvent motion;

	public TouchEvent(String action, Publisher source) {
		super(action, source);
	}

	public MotionEvent getMotion() {
		return motion;
	}

	public void setMotion(MotionEvent motion) {
		this.motion = motion;
	}
}
