package org.cniska.phaser.core;

import android.graphics.Canvas;
import android.view.SurfaceHolder;
import org.cniska.phaser.debug.Logger;
import org.cniska.phaser.debug.Monitor;

public class GameLoop implements Runnable {

	// Static variables
	// ----------------------------------------

	private static final int DELAYS_PER_YIELD = 16;

	// Member variables
	// ----------------------------------------

    private volatile boolean finished = false;
	private volatile boolean paused = false;

	private long period;
	private long startTime;
	private GameView view;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new game loop.
	 *
	 * @param period The update period (in nanoseconds).
	 * @param view The parent view.
	 */
    public GameLoop(long period, GameView view) {
        this.period = period;
		this.view = view;
    }

	/**
	 * Finishes the game loop.
	 */
	public void finish() {
		finished = true;
	}

	// Interface methods
	// ----------------------------------------

    @Override
    public void run() {
		long lastTime, afterTime, elapsedTime, sleepTime, dt;
		long overTime = 0L;
		int delayCount = 0;

		startTime = System.nanoTime();
		lastTime = startTime;

        Logger.info(getClass().getCanonicalName(), "Starting game loop.");

        while (!finished) {
			Monitor.beginUpdate();

            view.update();

			Monitor.beginDraw();

			synchronized (this) {
				SurfaceHolder holder = view.getHolder();
				Canvas canvas = holder.lockCanvas();

				if (canvas != null) {
					view.draw(canvas);
					holder.unlockCanvasAndPost(canvas);
				}
			}

			Monitor.beginSleep();

			afterTime = System.nanoTime();
			elapsedTime = afterTime - lastTime;
			sleepTime = (period - elapsedTime) - overTime;
			overTime = 0L;

			// Sleep if there is time, otherwise continue without sleeping.
			if (sleepTime > 0) {
				try {
					Thread.sleep(sleepTime / 1000000L); // ns -> ms
				} catch (InterruptedException e) {
					Logger.info(getClass().getCanonicalName(), "Sleep interrupted.");
				}

				// Calculate how much time we overslept, so that we can compensate for this next time.
				overTime = (System.nanoTime() - afterTime) - sleepTime;
			} else {
				// Give other threads a chance to run.
				if (++delayCount >= DELAYS_PER_YIELD) {
					Thread.yield();
					delayCount = 0;
				}
			}

			lastTime = System.nanoTime();
		}
	}
}
