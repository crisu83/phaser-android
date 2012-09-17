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

	// Static variables
	// ----------------------------------------

	private static final int RESOURCE_LEVELS = R.raw.levels;
	private static final int RESOURCE_ACTORS = R.raw.actors;

	protected static final String TAG_ID = "id";
	protected static final String TAG_NAME = "name";
	protected static final String TAG_LEVELS = "levels";
	protected static final String TAG_ACTORS = "actors";
	protected static final String TAG_WIDTH = "width";
	protected static final String TAG_HEIGHT = "height";
	protected static final String TAG_X = "x";
	protected static final String TAG_Y = "y";
	protected static final String TAG_VX = "vx";
	protected static final String TAG_VY = "vy";
	protected static final String TAG_AX = "ax";
	protected static final String TAG_AY = "ay";
	protected static final String TAG_Z_INDEX = "zIndex";
	protected static final String TAG_DEFAULT_ANIMATION = "defaultAnimation";
	protected static final String TAG_ANIMATIONS = "animations";
	protected static final String TAG_FRAMES = "frames";
	protected static final String TAG_LOOP = "loop";

	// Member variables
	// ----------------------------------------

	protected GameView view;
	protected JSONParser parser;
	protected HashMap<Integer, LevelData> levelData;
	protected HashMap<Integer, ActorData> actorData;

	// Methods
	// ----------------------------------------

	/**
	 * Creates new game data.
	 *
	 * @param view The game view.
	 */
	public GameData(GameView view) {
		this.view = view;
		parser = new JSONParser(view);
		levelData = new HashMap<Integer, LevelData>();
		actorData = new HashMap<Integer, ActorData>();
	}

	/**
	 * Returns the data for the level with the given id.
	 *
	 * @param id The level identifier.
	 * @return The level.
	 */
	public LevelData getLevel(int id) {
		return levelData.get(id);
	}

	/**
	 * Returns the data for the actor with the given id.
	 *
	 * @param id The actor identifier.
	 * @return The actor.
	 */
	public ActorData getActor(int id) {
		return actorData.get(id);
	}

	/**
	 * Loads the data.
	 */
	public void load() {
		parseLevels();
		parseActors();
	}

	/**
	 * Parses the level data.
	 */
	protected void parseLevels() {
		JSONObject object = loadJSON(RESOURCE_LEVELS);

		if (object != null) {
			try {
				JSONArray levels = object.getJSONArray(TAG_LEVELS);

				for (int i = 0, len = levels.length(); i < len; i++) {
					JSONObject level = (JSONObject) levels.get(i);
					LevelData levelData = parseLevelData(level);

					if (level.has(TAG_ACTORS)) {
						levelData.actors = new ArrayList<LevelActorData>();
						JSONArray actors = level.getJSONArray(TAG_ACTORS);

						for (int j = 0, len2 = actors.length(); j < len2; j++) {
							JSONObject actor = actors.getJSONObject(j);
							LevelActorData actorData = parseActorLevelData(actor);
							levelData.actors.add(actorData);
						}
					}

					this.levelData.put(levelData.id, levelData);
				}

			} catch (JSONException e) {
				Logger.error(getClass().getCanonicalName(), e.toString());
			}
		} else {
			Logger.error(getClass().getCanonicalName(), "Failed to load level data.");
		}
	}

	/**
	 * Parses level data.
	 *
	 * @param level The level data (as JSON).
	 * @return The data.
	 * @throws JSONException
	 */
	protected LevelData parseLevelData(JSONObject level) throws JSONException {
		LevelData data = new LevelData();
		data.id = level.getInt(TAG_ID);
		data.name = level.getString(TAG_NAME);
		return data;
	}

	/**
	 * Parses level actor data.
	 *
	 * @param actor The actor data (as JSON).
	 * @return The data.
	 * @throws JSONException
	 */
	protected LevelActorData parseActorLevelData(JSONObject actor) throws JSONException {
		LevelActorData data = new LevelActorData();
		data.id = actor.getInt(TAG_ID);
		data.x = actor.getInt(TAG_X);
		data.y = actor.getInt(TAG_Y);
		return data;
	}

	/**
	 * Parses the actor data.
	 */
	protected void parseActors() {
		JSONObject object = loadJSON(RESOURCE_ACTORS);

		if (object != null) {
			try {
				JSONArray actors = object.getJSONArray(TAG_ACTORS);

				for (int i = 0, len = actors.length(); i < len; i++) {
					JSONObject actor = (JSONObject) actors.get(i);
					ActorData actorDataItem = parseActorData(actor);

					if (actor.has(TAG_ANIMATIONS)) {
						actorDataItem.animations = new HashMap<String, AnimationData>();
						JSONObject animations = actor.getJSONObject(TAG_ANIMATIONS);
						Iterator animationKeys = animations.keys();

						while (animationKeys.hasNext()) {
							String animationKey = (String) animationKeys.next();
							JSONObject animation = (JSONObject) animations.get(animationKey);
							AnimationData animationData = parseAnimationData(animation);

							if (animation.has(TAG_FRAMES)) {
								animationData.frames = new ArrayList<AnimationFrameData>();
								JSONArray frames = (JSONArray) animation.get(TAG_FRAMES);

								for (int j = 0, len2 = frames.length(); j < len2; j++) {
									JSONArray frame = frames.getJSONArray(j);
									AnimationFrameData frameData = parseFrameData(frame);
									animationData.frames.add(frameData);
								}
							}

							actorDataItem.animations.put(animationKey, animationData);
						}
					}

					actorData.put(actorDataItem.id, actorDataItem);
				}
			} catch (JSONException e) {
				Logger.error(getClass().getCanonicalName(), e.toString());
			}
		} else {
			Logger.error(getClass().getCanonicalName(), "Failed to load actor data.");
		}
	}

	/**
	 * Parses actor data.
	 *
	 * @param actor The actor data (as JSON).
	 * @return The data.
	 * @throws JSONException
	 */
	protected ActorData parseActorData(JSONObject actor) throws JSONException {
		ActorData data = new ActorData();
		data.id = actor.getInt(TAG_ID);
		data.name = actor.getString(TAG_NAME);
		data.vx = (float) actor.getDouble(TAG_VX);
		data.vy = (float) actor.getDouble(TAG_VY);
		data.ax = (float) actor.getDouble(TAG_AX);
		data.ay = (float) actor.getDouble(TAG_AY);
		data.width = actor.getInt(TAG_WIDTH);
		data.height = actor.getInt(TAG_HEIGHT);
		data.zIndex = actor.getInt(TAG_Z_INDEX);
		data.defaultAnimation = actor.getString(TAG_DEFAULT_ANIMATION);
		return data;
	}

	/**
	 * Parses animation data.
	 *
	 * @param animation The animation data (as JSON).
	 * @return The data.
	 * @throws JSONException
	 */
	protected AnimationData parseAnimationData(JSONObject animation) throws JSONException {
		AnimationData data = new AnimationData();
		data.loop = animation.getBoolean(TAG_LOOP);
		return data;
	}

	/**
	 * Parses animation frame data.
	 *
	 * @param frame The frame data (as JSON).
	 * @return The data.
	 * @throws JSONException
	 */
	protected AnimationFrameData parseFrameData(JSONArray frame) throws JSONException {
		AnimationFrameData data = new AnimationFrameData();
		data.x = frame.getInt(0);
		data.y = frame.getInt(1);
		data.endTime = frame.getInt(2);
		return data;
	}

	/**
	 * Loads the JSON object for the given resource.
	 *
	 * @param resourceId The resource id.
	 * @return The JSON object.
	 */
	protected JSONObject loadJSON(int resourceId) {
		return parser.parse(resourceId);
	}

	// Inner classes
	// ----------------------------------------

	public class LevelData {
		public int id;
		public String name;
		public ArrayList<LevelActorData> actors;
	}

	public class LevelActorData {
		public int id;
		public int x, y;
	}

	public class ActorData {
		public int id;
		public String name;
		public int width, height;
		public float vx, vy;
		public float ax, ay;
		public int zIndex;
		public String defaultAnimation;
		public HashMap<String, AnimationData> animations;
	}

	public class AnimationData {
		public boolean loop;
		public ArrayList<AnimationFrameData> frames;
	}

	public class AnimationFrameData {
		public int x;
		public int y;
		public int endTime;
	}
}
