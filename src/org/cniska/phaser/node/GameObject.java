package org.cniska.phaser.node;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.draw.Drawable;

public abstract class GameObject extends GameNode implements Drawable {

	// Member variables
	// ----------------------------------------

	protected int x, y;
	protected int width, height;
	protected int zIndex = 0;
	protected float vx, vy;
	protected float ax, ay;

	private Paint paint;

	private boolean visible = true;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new game object.
	 *
	 * @param view The parent view.
	 */
	public GameObject(GameView view) {
		super(view);

		paint = new Paint();
		paint.setColor(Color.MAGENTA);
	}

	/**
	 * Sets the object position.
	 *
	 * @param x The coordinate on the x-axis.
	 * @param y The coordinate on the y-axis.
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Sets the object size.
	 *
	 * @param width The width in pixels.
	 * @param height The height in pixels.
	 */
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Sets the object velocity.
	 *
	 * @param vx The velocity on the x-axis.
	 * @param vy The velocity on the y-axis.
	 */
	public void setVelocity(float vx, float vy) {
		this.vx = vx;
		this.vy = vy;
	}

	/**
	 * Sets the object acceleration.
	 *
	 * @param ax The acceleration on the x-axis.
	 * @param ay The acceleration on the y-axis.
	 */
	public void setAcceleration(float ax, float ay) {
		this.ax = ax;
		this.ay = ay;
	}

	/**
	 * Returns the x-coordinate of the object's right edge.
	 *
	 * @return The coordinate.
	 */
	public int getX2() {
		return x + width;
	}

	/**
	 * Returns the y-coordinate of the object's the bottom edge.
	 *
	 * @return The coordinate.
	 */
	public int getY2() {
		return y + height;
	}

	/**
	 * Returns true if the object is moving, otherwise false.
	 *
	 * @return The result.
	 */
	protected boolean isMoving() {
		return vx != 0 || vy != 0;
	}

	/**
	 * Returns true if the object is accelerating, otherwise false.
	 *
	 * @return The result.
	 */
	protected boolean isAccelerating() {
		return ax != 0 || ay != 0;
	}

	/**
	 * Handles collision for this object.
	 * Override to apply collision logic.
	 */
	public void handleCollision() {
	}

	/**
	 * Handles input for this object.
	 * Override to apply input logic.
	 */
	public void handleInput() {
	}

	// Overridden methods
	// ----------------------------------------

	@Override
	public void update() {
		super.update();

		handleCollision();
		handleInput();

		// Apply movement if necessary.
		if (isMoving()) {
			x += vx;
			y += vy;
		}

		// Apply acceleration if necessary.
		if (isAccelerating()) {
			vx += ax;
			vy += ay;
		}
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawRect(x, y, getX2(), getY2(), paint);
	}

	// Getters and setters
	// ----------------------------------------

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getVx() {
		return vx;
	}

	public void setVx(float vx) {
		this.vx = vx;
	}

	public float getVy() {
		return vy;
	}

	public void setVy(float vy) {
		this.vy = vy;
	}

	public float getAx() {
		return ax;
	}

	public void setAx(float ax) {
		this.ax = ax;
	}

	public float getAy() {
		return ay;
	}

	public void setAy(float ay) {
		this.ay = ay;
	}

	public int getzIndex() {
		return zIndex;
	}

	public void setzIndex(int zIndex) {
		this.zIndex = zIndex;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
