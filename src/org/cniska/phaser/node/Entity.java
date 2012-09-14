package org.cniska.phaser.node;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import org.cniska.phaser.collision.Collidable;
import org.cniska.phaser.collision.CollisionEvent;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.event.Event;
import org.cniska.phaser.event.Subscriber;

public abstract class Entity extends Node implements Collidable {

	// Member variables
	// ----------------------------------------

	protected int x, y;
	protected int width, height;
	protected float vx, vy;
	protected float ax, ay;
	protected boolean removed = false;

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

	public void remove() {
		notify(new Event("remove", this));
		removed = true;
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
	 * Returns whether the entity intersects the other entity.
	 *
	 * @param other The other entity.
	 * @return The result.
	 */
	public boolean intersects(Entity other) {
		Rect r1 = new Rect(x, y, x2(), y2());
		Rect r2 = new Rect(other.getX(), other.getY(), other.x2(), other.y2());
		return r1.intersect(r2);
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

	public void notify(Event event) {
		for (int i = 0, len = subscribers.size(); i < len; i++) {
			Subscriber subscriber = subscribers.get(i);

			if (subscriber instanceof EntityListener) {
				if (event.getAction() == "remove") {
					((EntityListener) subscribers.get(i)).onRemove(event);
				}
			}
		}
	}

	// Overridden methods
	// ----------------------------------------

	@Override
	public void update(Updateable parent) {
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

	@Override
	public boolean collides(Entity other) {
		return false;
	}

	@Override
	public void onCollision(CollisionEvent event) {
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

	public boolean isRemoved() {
		return removed;
	}

	public void setRemoved(boolean removed) {
		this.removed = removed;
	}
}
