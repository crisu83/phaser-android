package org.cniska.phaser.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import org.cniska.phaser.debug.Logger;
import org.cniska.phaser.debug.ui.MonitorPanel;
import org.cniska.phaser.draw.ImageLoader;
import org.cniska.phaser.draw.Renderer;
import org.cniska.phaser.input.TouchHandler;
import org.cniska.phaser.node.Node;
import org.cniska.phaser.node.SceneManager;

public abstract class GameView extends SurfaceView implements SurfaceHolder.Callback {

    // Member variables
    // ----------------------------------------

    private volatile boolean running = false;

    private GameLoop gameLoop;
    private Thread animator;
	private Node gameRoot;
	private ImageLoader imageLoader;
	private volatile Renderer renderer;
	private SceneManager sceneManager;
	private TouchHandler touchHandler;
	private MonitorPanel monitor;
	private boolean debug = false;


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

		renderer = new Renderer(this);
		gameRoot.add(renderer);

		sceneManager = new SceneManager(this);
		gameRoot.add(sceneManager);

		imageLoader = new ImageLoader(this);
		touchHandler = new TouchHandler();

		monitor = new MonitorPanel(this);
		gameRoot.add(monitor);
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
		renderer.render(canvas);

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
		touchHandler.onTouch(event);
		return true;
	}

	// Getters and setters
	// ----------------------------------------

	public ImageLoader getImageLoader() {
		return imageLoader;
	}

	public Renderer getRenderer() {
		return renderer;
	}

	public Node getGameRoot() {
		return gameRoot;
	}

	public SceneManager getSceneManager() {
		return sceneManager;
	}

	public TouchHandler getTouchHandler() {
		return touchHandler;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}
}
