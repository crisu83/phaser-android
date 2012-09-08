package org.cniska.phaser.core;

import android.graphics.Canvas;

public abstract class GameObject {

	// Member variables
	// ----------------------------------------

	protected String name;
	protected int x, y, width, height;
	protected float vx, vy;
	protected GameView view;

	protected boolean initialized = false;

	public GameObject(GameView view) {
		this.view = view;
	}

	public void init() {
		initialized = true;
	}

	public void update() {
		if (!initialized) {
			init();
		}

		if (vx != 0 || vy != 0) {
			x += vx;
			y += vy;
		}
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void setVelocity(float vx, float vy) {
		this.vx = vx;
		this.vy = vy;
	}

	public int getX2() {
		return x + width;
	}

	public int getY2() {
		return y + height;
	}

	// Abstract methods
	// ----------------------------------------

	public abstract void draw(Canvas canvas);

	// Getters and setters
	// ----------------------------------------

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public void setVx(int vx) {
		this.vx = vx;
	}

	public float getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}
}
