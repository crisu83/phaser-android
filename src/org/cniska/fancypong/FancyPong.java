package org.cniska.fancypong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import org.cniska.phaser.draw.Renderer;
import org.cniska.phaser.input.TouchHandler;
import org.cniska.phaser.node.GameNode;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.SceneManager;
import org.cniska.phaser.debug.ui.MonitorPanel;

public class FancyPong extends GameView {

	public enum SceneType implements GameNode.NodeType {
		GAMEPLAY
	};

	// Member variables
	// ----------------------------------------

	private Renderer renderer;
	private SceneManager scenes;
	private TouchHandler touchHandler;
	private MonitorPanel monitor;
	private Paint background;

	// Methods
	// ----------------------------------------

	public FancyPong(long period, Context context) {
		super(period, context);

		renderer = new Renderer(this);
		scenes = new SceneManager(this);
		touchHandler = new TouchHandler(this);

		background = new Paint();
		background.setColor(Color.BLACK);
	}

	// Overridden methods
	// ----------------------------------------

	@Override
	public void init() {
		scenes.add(SceneType.GAMEPLAY, new GamePlay(this));
		scenes.set(SceneType.GAMEPLAY);

		monitor = new MonitorPanel(this);
		renderer.add(monitor);
	}

	@Override
	public void update() {
		renderer.update();
		scenes.update();
		monitor.update();
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawRect(0, 0, getWidth(), getHeight(), background);
		renderer.render(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		touchHandler.onTouch(event);
		return true;
	}

	// Getters and setters
	// ----------------------------------------

	public TouchHandler getTouchHandler() {
		return touchHandler;
	}

	public Renderer getRenderer() {
		return renderer;
	}
}
