package org.cniska.phaser.core;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import org.cniska.phaser.debug.Logger;

public abstract class GameView extends SurfaceView implements SurfaceHolder.Callback {

    // Member variables
    // ----------------------------------------

    private volatile boolean running = false;

    private GameLoop loop;
    private Thread animator;

    // Methods
    // ----------------------------------------

    /**
     * Creates the game view.
	 *
	 * @param period Time between draws (in nanoseconds).
     * @param context The parent activity.
     */
    public GameView(long period, Context context) {
        super(context);

        // Register the surface holder to allow us to control the surface size and format,
        // edit the pixels in the surface and monitor changes to the surface.
        getHolder().addCallback(this);
		loop = new GameLoop(period, this);
		init();
    }

    /**
     * Starts the game.
     */
    public void start() {
        if (!running) {
            Runtime r = Runtime.getRuntime();
            r.gc(); // Run garbage collection

            animator = new Thread(loop);
            animator.setName("GameLoop");
            animator.start();
            running = true;
            Logger.info(getClass().getCanonicalName(), "Game started.");
        } else {
            Logger.warn(getClass().getCanonicalName(), "Trying to start game while it is already running.");
        }
    }

    /**
     * Stops the game.
     */
    public void stop() {
        if (running) {
            loop.end();
            animator = null;
            running = false;
            Logger.info(getClass().getCanonicalName(), "Game stopped.");
        } else {
            Logger.warn(getClass().getCanonicalName(), "Trying to stop game while it is not running");
        }
    }

	// Abstract methods
	// ----------------------------------------

	/**
	 * Initializes the game.
	 */
	public abstract void init();

	/**
	 * Updates the game logic.
	 *
	 * @param timeDelta Time passed since the last call.
	 */
	public abstract void update(long timeDelta);

	/**
	 * Draws the game.
	 *
	 * @param canvas The canvas.
	 */
	public abstract void draw(Canvas canvas);

	// Interface methods
	// ----------------------------------------

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        start();
        Logger.info(getClass().getCanonicalName(), "Surface created.");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Nothing for now ...
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Logger.info(getClass().getCanonicalName(), "Surface destroyed.");
        stop();
    }
}
