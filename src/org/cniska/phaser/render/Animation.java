package org.cniska.phaser.render;

import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.util.List;

public class Animation implements Updateable {

	// Member variables
	// ----------------------------------------

	protected int currentIndex;
	protected int frameCount = 0;
	protected long startTime, playTime, totalTime;
	protected List<Frame> frames;
	protected boolean playing = false;
	protected boolean loop = true;

	// Methods
	// ----------------------------------------

	public Animation() {
		frames = new List<Frame>();
	}

	/**
	 * Adds a frame to the animation.
	 *
	 * @param x The frame offset on the x-axis.
	 * @param y The frame offset on the y-axis.
	 * @param playTime The frame length (in milliseconds).
	 */
	public void addFrame(int x, int y, long playTime) {
		totalTime += playTime * 1000000L; // ms -> ns
		frames.add(new Frame(x, y, totalTime));
		frameCount++;
	}

	/**
	 * Plays the animation.
	 */
	public void play() {
		startTime = System.nanoTime();
		currentIndex = 0;
		playTime = 0L;
		playing = true;
	}

	/**
	 * Stops the animation.
	 */
	public void stop() {
		playing = false;
	}

	/**
	 * Returns the frame offset on the x-axis.
	 *
	 * @return The offset in pixels.
	 */
	public int ox() {
		Frame frame = frames.get(currentIndex);
		return frame != null ? frame.getX() : 0;
	}

	/**
	 * Returns the frame offset on the y-axis.
	 *
	 * @return The offset in pixels.
	 */
	public int oy() {
		Frame frame = frames.get(currentIndex);
		return frame != null ? frame.getY() : 0;
	}

	/**
	 * Returns whether the animation has ended.
	 *
	 * @return The result.
	 */
	protected boolean hasEnded() {
		return playTime >= totalTime;
	}

	@Override
	public void update(Updateable parent) {
		frames.update(this);

		if (playing && frameCount > 0) {
			Frame frame = frames.get(currentIndex);
			playTime = System.nanoTime() - startTime;

			if (playTime > frame.getEndTime() && playTime < totalTime) {
				currentIndex++;
			}

			if (loop && hasEnded()) {
				play();
			}
		}
	}

	// Getters and setters
	// ----------------------------------------

	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	// Inner classes
	// ----------------------------------------

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
