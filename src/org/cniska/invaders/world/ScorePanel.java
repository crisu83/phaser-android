package org.cniska.invaders.world;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.event.Event;
import org.cniska.phaser.node.Actor;
import org.cniska.phaser.node.ActorListener;
import org.cniska.phaser.scene.Scene;
import org.cniska.phaser.ui.Element;

public class ScorePanel extends Element implements ActorListener {

	protected int score = 0;

	/**
	 * Creates a new panel.
	 *
	 * @param view  The game view.
	 * @param scene The parent scene.
	 */
	public ScorePanel(GameView view, Scene scene) {
		super(view, scene);
	}

	public void addScore(int amount) {
		score += amount;
	}

	public void subScore(int amount) {
		if ((score - amount) >= 0) {
			score -= amount;
		}
	}

	@Override
	protected void init() {
		super.init();

		text.setTextSize(32);

		Paint background = new Paint();
		background.setColor(Color.DKGRAY);
		background.setAlpha(30);
		setBackground(background);

		position(0, view.getHeight() - 60);
		size(view.getWidth(), 60);
		setzIndex(100);
		setPadding(10);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		canvas.drawText("SCORE: " + score, contentX(), lineY(1), text);
	}

	@Override
	public void onActorBirth(Event event) {
	}

	@Override
	public void onActorDeath(Event event) {
		Actor actor = (Actor) event.getSource();
		if (actor instanceof Alien) {
			addScore(100);
		} else if (actor instanceof Rocket) {
			subScore(10);
		}
	}
}
