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

	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_LEVELS = "levels";
	private static final String TAG_ACTORS = "actors";
	private static final String TAG_WIDTH = "width";
	private static final String TAG_HEIGHT = "height";
	private static final String TAG_X = "x";
	private static final String TAG_Y = "y";
	private static final String TAG_VX = "vx";
	private static final String TAG_VY = "vy";
	private static final String TAG_AX = "ax";
	private static final String TAG_AY = "ay";
	private static final String TAG_Z_INDEX = "zIndex";
	private static final String TAG_DEFAULT_ANIMATION = "defaultAnimation";
	private static final String TAG_ANIMATIONS = "animations";
	private static final String TAG_FRAMES = "frames";
	private static final String TAG_LOOP = "loop";

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
					LevelData levelDataItem = new LevelData();
					levelDataItem.id = level.getInt(TAG_ID);
					levelDataItem.name = level.getString(TAG_NAME);

					if (level.has(TAG_ACTORS)) {
						levelDataItem.actors = new ArrayList<ActorLevelData>();
						JSONArray actors = level.getJSONArray(TAG_ACTORS);

						for (int j = 0, len2 = actors.length(); j < len2; j++) {
							JSONObject actorLevelItem = actors.getJSONObject(j);
							ActorLevelData actorLevelData = new ActorLevelData();
							actorLevelData.id = actorLevelItem.getInt(TAG_ID);
							actorLevelData.x = actorLevelItem.getInt(TAG_X);
							actorLevelData.y = actorLevelItem.getInt(TAG_Y);
							levelDataItem.actors.add(actorLevelData);
						}
					}

					levelData.put(levelDataItem.id, levelDataItem);
				}

			} catch (JSONException e) {
				Logger.error(getClass().getCanonicalName(), e.toString());
			}
		} else {
			Logger.error(getClass().getCanonicalName(), "Failed to load level data.");
		}
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

					ActorData actorDataItem = new ActorData();
					actorDataItem.id = actor.getInt(TAG_ID);
					actorDataItem.name = actor.getString(TAG_NAME);
					actorDataItem.vx = (float) actor.getDouble(TAG_VX);
					actorDataItem.vy = (float) actor.getDouble(TAG_VY);
					actorDataItem.ax = (float) actor.getDouble(TAG_AX);
					actorDataItem.ay = (float) actor.getDouble(TAG_AY);
					actorDataItem.width = actor.getInt(TAG_WIDTH);
					actorDataItem.height = actor.getInt(TAG_HEIGHT);
					actorDataItem.zIndex = actor.getInt(TAG_Z_INDEX);
					actorDataItem.defaultAnimation = actor.getString(TAG_DEFAULT_ANIMATION);

					if (actor.has(TAG_ANIMATIONS)) {
						actorDataItem.animations = new HashMap<String, AnimationData>();
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

								for (int j = 0, len2 = frames.length(); j < len2; j++) {
									JSONArray frame = frames.getJSONArray(j);
									FrameData frameData = new FrameData();
									frameData.x = frame.getInt(0);
									frameData.y = frame.getInt(1);
									frameData.endTime = frame.getInt(2);
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
		public ArrayList<ActorLevelData> actors;
	}

	public class ActorLevelData {
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
		public ArrayList<FrameData> frames;
	}

	public class FrameData {
		public int x;
		public int y;
		public int endTime;
	}
}
