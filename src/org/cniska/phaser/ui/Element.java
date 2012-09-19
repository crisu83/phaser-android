package org.cniska.phaser.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.Sprite;
import org.cniska.phaser.scene.Scene;

public class Element extends Sprite {

	protected Scene scene;
	protected String body;
	protected Paint background;
	protected Paint text;
	protected int padding = 0;

	/**
	 * Creates a new element.
	 *
	 * @param view The game view.
	 * @param scene The parent scene.
	 */
	protected Element(GameView view, Scene scene) {
		super(view);
		this.scene = scene;

		background = new Paint();
		background.setColor(Color.TRANSPARENT);

		text = new Paint(Paint.ANTI_ALIAS_FLAG);
		text.setColor(Color.WHITE);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawRect(x, y, x2(), y2(), background);

		if (body != null) {
			canvas.drawText(body, x + padding, y + padding, text);
		}
	}

	// Getters and setters
	// ----------------------------------------

	public void setBody(String body) {
		this.body = body;
	}

	public void setBackground(Paint background) {
		this.background = background;
	}

	public void setText(Paint text) {
		this.text = text;
	}

	public void setPadding(int padding) {
		this.padding = padding;
	}
}
