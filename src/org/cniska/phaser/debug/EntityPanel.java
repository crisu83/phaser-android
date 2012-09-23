package org.cniska.phaser.debug;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.event.Event;
import org.cniska.phaser.node.Entity;
import org.cniska.phaser.scene.Scene;
import org.cniska.phaser.ui.Element;

public class EntityPanel extends Element {

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
	protected void init() {
		super.init();

		Paint background = new Paint();
		background.setColor(Color.DKGRAY);
		background.setAlpha(50);
		setBackground(background);

		position(10, 90);
		size(100, 35);
		setzIndex(100);
		setPadding(10);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		canvas.drawText("Entities: " + entityCount, contentX(), lineY(1), text);
	}

	@Override
	public void onEntityCreate(Event event) {
		entityCount++;
	}

	@Override
	public void onEntityRemove(Event event) {
		Entity entity = (Entity) event.getSource();
		entity.unsubscribe(this);
		entityCount--;
	}
}
