package org.cniska.invaders;

import org.cniska.phaser.core.GameData;
import org.cniska.phaser.core.GameView;
import org.json.JSONException;
import org.json.JSONObject;

public class SpaceData extends GameData {

	public static final String TAG_ALIEN_ROW_COUNT = "alienRowCount";
	public static final String TAG_ALIEN_COL_COUNT = "alienColCount";

	/**
	 * Creates new game data.
	 *
	 * @param view The game view.
	 */
	public SpaceData(GameView view) {
		super(view);
	}

	@Override
	protected LevelData parseLevelData(JSONObject level) throws JSONException {
		SpaceLevelData data = new SpaceLevelData();
		data.id = level.getInt(TAG_ID);
		data.name = level.getString(TAG_NAME);
		data.alienRowCount = level.getInt(TAG_ALIEN_ROW_COUNT);
		data.alienColCount = level.getInt(TAG_ALIEN_COL_COUNT);
		return data;
	}

	public class SpaceLevelData extends LevelData {
		public int alienRowCount, alienColCount;
	}
}
