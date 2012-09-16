package org.cniska.phaser.node;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import org.cniska.phaser.collision.Collidable;
import org.cniska.phaser.core.GameData;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.debug.Debuggable;
import org.cniska.phaser.event.Event;
import org.cniska.phaser.render.Animation;
import org.cniska.phaser.scene.World;
import org.cniska.phaser.util.QuadTreeable;

import java.util.HashMap;

public abstract class Actor extends Sprite implements Collidable, QuadTreeable {

	// Member variables
	// ----------------------------------------

	protected World world;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new actor.
	 *
	 * @param view The game view.
	 * @param world The parent world.
	 */
	protected Actor(GameView view, World world) {
		super(view);
		this.world = world;
		world.addNode(this);
		world.getPhysics().addActor(this);
		world.getRenderer().addSprite(this);
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

		if (id > 0) {
			GameData.ActorData data = view.getData().getActor(id);

			if (data != null) {
				if (data.width > 0) {
					width = data.width;
				}

				if (data.height > 0) {
					height = data.height;
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

				if (data.ay != 0) {
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
	public void debug(Debuggable parent, Canvas canvas) {
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
