package org.cniska.phaser.debug;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.event.Event;
import org.cniska.phaser.scene.Scene;
import org.cniska.phaser.ui.Panel;

public class EntityPanel extends Panel {

	protected int entityCount = 0;

	/**
	 * Creates a new panel.
	 *
	 * @param view  The game view.
	 * @param scene The parent scene.
	 */
	public EntityPanel(GameView view, Scene scene) {
		super(view, scene);
	}

	@Override
	public void init() {
		super.init();

		text.setColor(Color.WHITE);
		text.setTextSize(12);

		Paint background = new Paint();
		background.setColor(Color.DKGRAY);
		background.setAlpha(50);
		setBackground(background);

		position(view.getWidth() - 120, 10);
		size(100, 30);
		setzIndex(100);
		setPadding(10);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		canvas.drawText("Entities: " + entityCount, x + padding, y + 20, text);
	}

	@Override
	public void onEntityInit(Event event) {
		entityCount++;
	}

	@Override
	public void onEntityRemove(Event event) {
		//event.getSource().detach(this);
		entityCount--;
	}
}
