package org.cniska.phaser.debug.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.debug.Monitor;
import org.cniska.phaser.ui.GamePanel;

import java.text.DecimalFormat;

public class MonitorPanel extends GamePanel {

	private Paint text;
	private DecimalFormat df;

	public MonitorPanel(GameView view) {
		super(view);

		//df = new DecimalFormat("0.##");

		text = new Paint(Paint.ANTI_ALIAS_FLAG);
		text.setColor(Color.WHITE);
		text.setStyle(Paint.Style.FILL);
		text.setTextSize(12);

		/*
		Paint background = new Paint();
		background.setColor(Color.DKGRAY);
		background.setAlpha(80);
		setBackground(background);
		*/

		setPosition(10, 10);
		setSize(60, 30);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);

		canvas.drawText("FPS: " + Monitor.getFps(), x, y, text);
		/*
		text.setColor(Color.GREEN);
		canvas.drawText("FPS: " + df.format(Monitor.getFps()), x + 10, y + 20, text);
		text.setColor(Color.CYAN);
		canvas.drawText("UPS: " + df.format(Monitor.getUps()), x + 10, y + 35, text);
		text.setColor(Color.YELLOW);
		canvas.drawText("Update: " + df.format(Monitor.getUpdateTime()) + "s", x + 10, y + 50, text);
		text.setColor(Color.MAGENTA);
		canvas.drawText("Draw: " + df.format(Monitor.getDrawTime()) + "s", x + 10, y + 65, text);
		text.setColor(Color.GRAY);
		canvas.drawText("Sleep: " + df.format(Monitor.getSleepTime()) + "s", x + 10, y + 80, text);
		*/
	}
}
