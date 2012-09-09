package org.cniska.phaser.debug;

public class Monitor {

	protected static final long resolution = 1000000000L; // nanoseconds

	public static final int UPDATE = 1;
	public static final int DRAW = 2;
	public static final int SLEEP = 3;

	protected static int phase;
	protected static int frameCount, updateCount;
	protected static long timeLast;
	protected static long updateSum, drawSum, sleepSum;
	protected static float updateTime, drawTime, sleepTime;
	protected static float fps, ups;

	private Monitor() {
		reset();
	}

	/**
	 * Begins the update phase.
	 */
	public static void beginUpdate() {
		updateSum += deltaTime();
		updateCount++;
		phase = UPDATE;
		update();
	}

	/**
	 * Begins the draw phase.
	 */
	public static void beginDraw() {
		drawSum += deltaTime();
		frameCount++;
		phase = DRAW;
		update();
	}

	/**
	 * Begins the sleep phase.
	 */
	public static void beginSleep() {
		sleepSum += deltaTime();
		phase = SLEEP;
		update();
	}

	/**
	 * Updates the monitor.
	 */
	public static void update() {
		float timeSum, divider;

		timeSum = updateSum + drawSum + sleepSum;

		if (timeSum >= resolution) {
			updateTime = updateSum / timeSum;
			drawTime = drawSum / timeSum;
			sleepTime = sleepSum / timeSum;
			divider = timeSum / resolution;
			fps = frameCount / divider;
			ups = updateCount / divider;
			reset();
		}
	}

	/**
	 * Returns the time since the method was last called.
	 * @return Time in nanoseconds.
	 */
	private static long deltaTime() {
		long timeNow, timeDelta;

		timeNow = System.nanoTime();
		timeDelta = timeNow - timeLast;
		timeLast = timeNow;
		return timeDelta;
	}

	/**
	 * Resets the monitor.
	 */
	private static void reset() {
		updateSum = 0;
		drawSum = 0;
		sleepSum = 0;
		frameCount = 0;
		updateCount = 0;
	}

	// Getters and setters
	// ----------------------------------------

	public static float getFps() {
		return fps;
	}

	public static float getUps() {
		return ups;
	}

	public static float getUpdateTime() {
		return updateTime;
	}

	public static float getDrawTime() {
		return drawTime;
	}

	public static float getSleepTime() {
		return sleepTime;
	}
}
