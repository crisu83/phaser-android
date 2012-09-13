package org.cniska.phaser.input;

import android.view.MotionEvent;
import org.cniska.phaser.event.Publisher;
import org.cniska.phaser.event.Subscriber;

import java.util.Vector;

public class TouchHandler extends Publisher {

	// Methods
	// ----------------------------------------

	public TouchHandler() {
		super();
	}

	public void onTouch(MotionEvent event) {
		for (Subscriber obj : subscribers) {
			((TouchListener) obj).onTouch(event);
		}
	}
}
