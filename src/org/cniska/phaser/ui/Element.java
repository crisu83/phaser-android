package org.cniska.phaser.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.Sprite;
import org.cniska.phaser.scene.Scene;

public class Element extends Sprite {

	// Member variables
	// ----------------------------------------

	public static final int DEFAULT_TEXT_SIZE = 12;

	protected Scene scene;
	protected String body;
	protected Paint background;
	protected Paint text;
	protected int padding = 0;
	protected float lineHeight = 1.2f;

	// Methods
	// ----------------------------------------

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
		text = new Paint(Paint.ANTI_ALIAS_FLAG);
	}

	/**
	 * Centers the element in the view.
	 */
	public void center() {
		position(view.getWidth() / 2 - width / 2, view.getHeight() / 2 - height / 2);
	}

	/**
	 * Returns the content coordinate on the x-axis.
	 *
	 * @return The coordinate.
	 */
	public int contentX() {
		return x + padding;
	}

	/**
	 * Returns the content coordinate on the y-axis.
	 *
	 * @return The coordinate.
	 */
	public int contentY() {
		return y + padding;
	}

	/**
	 * Returns the coordinate on the y-axis for the given text line.
	 *
	 * @param line The line number (beginning from 1).
	 * @return The coordinate.
	 */
	public int lineY(int line) {
		return contentY() + (int) text.getTextSize() + (int) (text.getTextSize() * lineHeight) * (line - 1);
	}

	// Overridden methods
	// ----------------------------------------


	@Override
	protected void init() {
		super.init();

		text.setColor(Color.WHITE);
		text.setTextSize(DEFAULT_TEXT_SIZE);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawRect(x, y, x2(), y2(), background);

		if (body != null) {
			canvas.drawText(body, contentX(), lineY(1), text);
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
