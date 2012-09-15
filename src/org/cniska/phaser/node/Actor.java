package org.cniska.phaser.node;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import org.cniska.phaser.collision.Collidable;
import org.cniska.phaser.core.GameData;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.event.Event;
import org.cniska.phaser.render.Animation;
import org.cniska.phaser.scene.GameScene;

import java.util.HashMap;

public abstract class Actor extends Sprite implements Collidable {

	// Member variables
	// ----------------------------------------

	protected GameScene scene;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new actor.
	 *
	 * @param view The game view.
	 * @param scene The parent scene.
	 */
	protected Actor(GameView view, GameScene scene) {
		super(view);
		this.scene = scene;

		scene.addNode(this);
		scene.getWorld().addActor(this);
		scene.getRenderer().addSprite(this);
	}

	/**
	 * Returns whether the actor collides with the given actor.
	 *
	 * @param other The other actor.
	 * @return The result.
	 */
	public boolean collides(Actor other) {
		return false;
	}

	/**
	 * Fires the collision event to the given actor.
	 *
	 * @param other The other actor.
	 */
	public void collide(Actor other) {
		Event event = new Event("actor:collide", this);
		other.onCollision(event);
	}

	// Overridden methods
	// ----------------------------------------

	@Override
	public void init() {
		super.init();

		if (name != null) {
			GameData.ActorData data = view.getData().getActor(name);

			if (data != null) {
				if (data.width > 0) {
					width = data.width;
				}

				if (data.height > 0) {
					height = data.height;
				}

				if (data.x != 0) {
					x = data.x;
				}

				if (data.y != 0) {
					y = data.y;
				}

				if (data.vx != 0) {
					vx = data.vx;
				}

				if (data.vy != 0) {
					vy = data.vy;
				}

				if (data.ax != 0) {
					ax = data.ax;
				}

				if (data.y != 0) {
					ay = data.ay;
				}

				if (data.zIndex > 0) {
					setzIndex(data.zIndex);
				}

				if (data.animations != null) {
					for (HashMap.Entry<String, GameData.AnimationData> animationEntry : data.animations.entrySet()) {
						String animationName = animationEntry.getKey();
						GameData.AnimationData animationData = animationEntry.getValue();

						Animation animation = new Animation();
						animation.setLoop(animationData.loop);

						if (animationData.frames != null) {
							for (int i = 0, len = animationData.frames.size(); i < len; i++) {
								GameData.FrameData frameData = animationData.frames.get(i);
								animation.addFrame(frameData.x, frameData.y, frameData.endTime);
							}
						}

						addAnimation(animationName, animation);
					}

					if (defaultAnimation != "") {
						setDefaultAnimation(data.defaultAnimation);
						playAnimation(data.defaultAnimation);
					}
				}
			}
		}
	}

	@Override
	public void debug(Node parent, Canvas canvas) {
		if (visible) {
			Paint paint = new Paint();
			paint.setColor(Color.MAGENTA);
			paint.setStyle(Paint.Style.STROKE);
			canvas.drawRect(x, y, x2(), y2(), paint);
		}
	}

	@Override
	public void onCollision(Event event) {
	}
}
