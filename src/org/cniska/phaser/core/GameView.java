package org.cniska.phaser.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import org.cniska.phaser.debug.Logger;
import org.cniska.phaser.debug.MonitorPanel;
import org.cniska.phaser.input.TouchHandler;
import org.cniska.phaser.node.Node;
import org.cniska.phaser.render.ImageLoader;
import org.cniska.phaser.scene.Director;

public abstract class GameView extends SurfaceView implements SurfaceHolder.Callback {

    // Member variables
    // ----------------------------------------

    private volatile boolean running = false;

    private GameLoop gameLoop;
    private Thread animator;
	private MonitorPanel monitor;
	private boolean debug = false;

	protected Node gameRoot;
	protected ImageLoader imageLoader;
	protected Director director;
	protected TouchHandler touchHandler;

    // Methods
    // ----------------------------------------

    /**
     * Creates a new game view.
	 *
	 * @param period Time between draws (in nanoseconds).
     * @param context The parent activity.
     */
    public GameView(long period, Context context) {
        super(context);

		// Register the surface holder to allow us to control the surface size and format,
		// edit the pixels in the surface and monitor changes to the surface.
		getHolder().addCallback(this);
		gameLoop = new GameLoop(period, this);

        init();
		setup();
    }

	/**
	 * Initializes the game.
	 */
	protected void init() {
		gameRoot = new Node(this);

		director = new Director(this);
		gameRoot.addNode(director);

		imageLoader = new ImageLoader(this);

		touchHandler = new TouchHandler(this);
		gameRoot.addNode(touchHandler);

		monitor = new MonitorPanel(this);
		gameRoot.addNode(monitor);
	}

    /**
     * Starts the game.
     */
	protected void start() {
        if (!running) {
            Runtime r = Runtime.getRuntime();
            r.gc(); // Run garbage collection

            animator = new Thread(gameLoop);
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
	protected void stop() {
        if (running) {
            gameLoop.end();
            animator = null;
            running = false;
            Logger.info(getClass().getCanonicalName(), "Game stopped.");
        } else {
            Logger.warn(getClass().getCanonicalName(), "Trying to stop game while it is not running");
        }
    }

	/**
	 * Updates the game logic.
	 */
	public void update() {
		gameRoot.update(null);
	}

	/**
	 * Draws the game.
	 *
	 * @param canvas The canvas context.
	 */
	public void draw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		director.render(canvas);

		if (debug) {
			gameRoot.debug(null, canvas);
			monitor.draw(canvas);
		}
	}

	// Abstract methods
	// ----------------------------------------

	/**
	 * Setups the game.
	 */
	public abstract void setup();

	/**
	 * Returns the game data.
	 *
	 * @return The data.
	 */
	public abstract GameData getData();

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

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		touchHandler.notify(event);
		return true;
	}

	// Getters and setters
	// ----------------------------------------

	public Director getDirector() {
		return director;
	}

	public ImageLoader getImageLoader() {
		return imageLoader;
	}

	public Node getGameRoot() {
		return gameRoot;
	}

	public TouchHandler getTouchHandler() {
		return touchHandler;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}
}
