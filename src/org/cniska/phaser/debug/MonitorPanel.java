package org.cniska.phaser.debug;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.scene.Scene;
import org.cniska.phaser.ui.Element;

import java.text.DecimalFormat;

public class MonitorPanel extends Element {

	private DecimalFormat formatter;

	public MonitorPanel(GameView view, Scene scene) {
		super(view, scene);
		formatter = new DecimalFormat("0.##");
	}

	@Override
	protected void init() {
		super.init();

		Paint background = new Paint();
		background.setColor(Color.DKGRAY);
		background.setAlpha(50);
		setBackground(background);

		position(10, 10);
		size(100, 75);
		setzIndex(100);
		setPadding(10);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);

		text.setColor(Color.CYAN);
		canvas.drawText("FPS: " + formatter.format(Monitor.getFps()), contentX(), lineY(1), text);
		text.setColor(Color.YELLOW);
		canvas.drawText("Update: " + formatter.format(Monitor.getUpdateTime()), contentX(), lineY(2), text);
		text.setColor(Color.MAGENTA);
		canvas.drawText("Draw: " + formatter.format(Monitor.getDrawTime()), contentX(), lineY(3), text);
		text.setColor(Color.GRAY);
		canvas.drawText("Sleep: " + formatter.format(Monitor.getSleepTime()), contentX(), lineY(4), text);
	}
}
