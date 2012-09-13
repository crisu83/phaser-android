package org.cniska.phaser.draw;

import org.cniska.phaser.debug.Logger;
import org.cniska.phaser.node.Node;
import org.cniska.phaser.node.Updateable;

import java.util.Vector;

public class Animation implements Updateable {

	private int currentIndex;
	private Vector<Frame> frames;
	private long startTime, playTime, totalTime;
	private boolean loop = true;

	public Animation() {
		frames = new Vector<Frame>();
	}

	public void addFrame(int x, int y, long playTime) {
		totalTime += playTime * 1000000L;
		frames.add(new Frame(x, y, totalTime));
	}

	public void play() {
		startTime = System.nanoTime();
		currentIndex = 0;
		playTime = 0;
	}

	public int bitmapX() {
		Frame frame = frames.get(currentIndex);
		return frame != null ? frame.getX() : 0;
	}

	public int bitmapY() {
		Frame frame = frames.get(currentIndex);
		return frame != null ? frame.getY() : 0;
	}

	private boolean hasEnded() {
		return playTime >= totalTime;
	}

	@Override
	public void update(Node parent) {
		int frameCount = frames.size();

		playTime = System.nanoTime() - startTime;

		if (frameCount > 0) {
			if (loop && hasEnded()) {
				play();
			}

			Frame frame = frames.get(currentIndex);

			if (frame != null && playTime > frame.getEndTime() && currentIndex < (frameCount - 1)) {
				currentIndex++;
				Logger.debug(getClass().getCanonicalName(), "Animation index increased to " + currentIndex + ", offset x is now " + bitmapX());
			}
		}
	}

	private class Frame {
		private int x, y;
		private long endTime;

		public Frame(int x, int y, long endTime) {
			this.x = x;
			this.y = y;
			this.endTime = endTime;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public long getEndTime() {
			return endTime;
		}
	}
}
