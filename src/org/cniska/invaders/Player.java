package org.cniska.invaders;

import android.view.MotionEvent;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.render.Animation;
import org.cniska.phaser.input.TouchListener;
import org.cniska.phaser.node.Actor;

public class Player extends Actor implements TouchListener {

	protected MotionEvent touch;

	/**
	 * Creates a new game object.
	 *
	 * @param view The parent view.
	 */
	public Player(GameView view) {
		super(view);

		position(600, 600);
		size(20, 20);

		loadBitmap(R.drawable.ship_01);

		Animation animation = new Animation();
		animation.addFrame(0, 0, 100);
		animation.addFrame(20, 0, 100);
		addAnimation("idle", animation);
		playAnimation("idle");
	}

	@Override
	public void input() {
		if (touch != null) {
			x = (int) (touch.getX() - 10);
			touch = null;
		}
	}

	@Override
	public void onTouch(MotionEvent event) {
		this.touch = event;
	}
}
