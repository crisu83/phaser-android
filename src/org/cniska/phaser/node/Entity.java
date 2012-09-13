package org.cniska.phaser.node;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import org.cniska.phaser.core.GameView;

public abstract class Entity extends Node {

	// Member variables
	// ----------------------------------------

	protected int x, y;
	protected int width, height;
	protected float vx, vy;
	protected float ax, ay;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new entity.
	 *
	 * @param view The game view.
	 */
	protected Entity(GameView view) {
		super(view);
	}

	/**
	 * Handles input for the entity.
	 * Override this method to apply input logic.
	 */
	public void input() {
	}

	/**
	 * Sets the entity position.
	 *
	 * @param x The coordinate on the x-axis.
	 * @param y The coordinate on the y-axis.
	 */
	public void position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Sets the entity size.
	 *
	 * @param width The width in pixels.
	 * @param height The height in pixels.
	 */
	public void size(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Sets the entity velocity.
	 *
	 * @param vx The velocity on the x-axis.
	 * @param vy The velocity on the y-axis.
	 */
	public void velocity(float vx, float vy) {
		this.vx = vx;
		this.vy = vy;
	}

	/**
	 * Sets the entity acceleration.
	 *
	 * @param ax The acceleration on the x-axis.
	 * @param ay The acceleration on the y-axis.
	 */
	public void acceleration(float ax, float ay) {
		this.ax = ax;
		this.ay = ay;
	}

	/**
	 * Returns the x-coordinate of the entity right edge.
	 *
	 * @return The coordinate.
	 */
	public int x2() {
		return x + width;
	}

	/**
	 * Returns the y-coordinate of the entity bottom edge.
	 *
	 * @return The coordinate.
	 */
	public int y2() {
		return y + height;
	}

	/**
	 * Returns true if the object is moving, otherwise false.
	 *
	 * @return The result.
	 */
	public boolean isMoving() {
		return vx != 0 || vy != 0;
	}

	/**
	 * Returns true if the object is accelerating, otherwise false.
	 *
	 * @return The result.
	 */
	public boolean isAccelerating() {
		return ax != 0 || ay != 0;
	}

	// Overridden methods
	// ----------------------------------------

	@Override
	public void update(Node parent) {
		super.update(parent);

		input();

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
	public void debug(Node parent, Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.MAGENTA);
		paint.setStyle(Paint.Style.STROKE);
		canvas.drawRect(x, y, x2(), y2(), paint);
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
}
