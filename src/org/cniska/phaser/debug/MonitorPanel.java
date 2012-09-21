package org.cniska.phaser.debug;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.scene.Scene;
import org.cniska.phaser.ui.Panel;

import java.text.DecimalFormat;

public class MonitorPanel extends Panel {

	private DecimalFormat formatter;

	public MonitorPanel(GameView view, Scene scene) {
		super(view, scene);
		formatter = new DecimalFormat("0.##");
	}

	@Override
	public void init() {
		super.init();

		text.setTextSize(12);
		text.setAlpha(50);

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
		canvas.drawText("FPS: " + formatter.format(Monitor.getFps()), x + padding, y + 20, text);
		text.setColor(Color.YELLOW);
		canvas.drawText("Update: " + formatter.format(Monitor.getUpdateTime()), x + padding, y + 35, text);
		text.setColor(Color.MAGENTA);
		canvas.drawText("Draw: " + formatter.format(Monitor.getDrawTime()), x + padding, y + 50, text);
		text.setColor(Color.GRAY);
		canvas.drawText("Sleep: " + formatter.format(Monitor.getSleepTime()), x + padding, y + 65, text);
	}
}
