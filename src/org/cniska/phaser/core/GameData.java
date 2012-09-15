package org.cniska.phaser.core;

import org.cniska.invaders.R;
import org.cniska.phaser.debug.Logger;
import org.cniska.phaser.util.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GameData {

	private static final int RESOURCE_ACTORS = R.raw.actors;

	private static final String TAG_X = "x";
	private static final String TAG_Y = "y";
	private static final String TAG_WIDTH = "width";
	private static final String TAG_HEIGHT = "height";
	private static final String TAG_VX = "vx";
	private static final String TAG_VY = "vy";
	private static final String TAG_AX = "ax";
	private static final String TAG_AY = "ay";
	private static final String TAG_Z_INDEX = "zIndex";
	private static final String TAG_DEFAULT_ANIMATION = "defaultAnimation";
	private static final String TAG_ANIMATIONS = "animations";
	private static final String TAG_FRAMES = "frames";
	private static final String TAG_LOOP = "loop";

	protected GameView view;
	protected JSONParser parser;
	protected HashMap<String, ActorData> actorData;

	public GameData(GameView view) {
		this.view = view;
		parser = new JSONParser(view);
		actorData = new HashMap<String, ActorData>();
	}

	public JSONObject loadJSON(int resourceId) {
		return parser.parse(resourceId);
	}

	public ActorData getActor(String name) {
		return actorData.get(name);
	}

	public void load() {
		parseActors();
	}

	protected void parseActors() {
		JSONObject actors = loadJSON(RESOURCE_ACTORS);

		try {
			Iterator keys = actors.keys();
			while (keys.hasNext()) {
				String key = (String) keys.next();
				JSONObject actor = (JSONObject) actors.get(key);

				ActorData actorData = new ActorData();
				actorData.x = actor.getInt(TAG_X);
				actorData.y = actor.getInt(TAG_Y);
				actorData.vx = (float) actor.getDouble(TAG_VX);
				actorData.vy = (float) actor.getDouble(TAG_VY);
				actorData.ax = (float) actor.getDouble(TAG_AX);
				actorData.ay = (float) actor.getDouble(TAG_AY);
				actorData.width = actor.getInt(TAG_WIDTH);
				actorData.height = actor.getInt(TAG_HEIGHT);
				actorData.zIndex = actor.getInt(TAG_Z_INDEX);
				actorData.defaultAnimation = actor.getString(TAG_DEFAULT_ANIMATION);

				if (actor.has(TAG_ANIMATIONS)) {
					actorData.animations = new HashMap<String, AnimationData>();
					JSONObject animations = actor.getJSONObject(TAG_ANIMATIONS);
					Iterator animationKeys = animations.keys();

					while (animationKeys.hasNext()) {
						String animationKey = (String) animationKeys.next();
						JSONObject animation = (JSONObject) animations.get(animationKey);

						AnimationData animationData = new AnimationData();
						animationData.loop = animation.getBoolean(TAG_LOOP);

						if (animation.has(TAG_FRAMES)) {
							animationData.frames = new ArrayList<FrameData>();
							JSONArray frames = (JSONArray) animation.get(TAG_FRAMES);

							for (int i = 0, len = frames.length(); i < len; i++) {
								JSONArray frame = frames.getJSONArray(i);
								FrameData frameData = new FrameData();
								frameData.x = frame.getInt(0);
								frameData.y = frame.getInt(1);
								frameData.endTime = frame.getInt(2);
								animationData.frames.add(frameData);
							}
						}

						actorData.animations.put(animationKey, animationData);
					}
				}

				this.actorData.put(key, actorData);
			}
		} catch (JSONException e) {
			Logger.error(getClass().getCanonicalName(), e.toString());
		}
	}

	public class ActorData {
		public int x, y;
		public int width, height;
		public float vx, vy;
		public float ax, ay;
		public int zIndex;
		public String defaultAnimation;
		public HashMap<String, AnimationData> animations;
	}

	public class AnimationData {
		public boolean loop;
		public ArrayList<FrameData> frames;
	}

	public class FrameData {
		public int x;
		public int y;
		public int endTime;
	}
}
