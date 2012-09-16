package org.cniska.phaser.util;

import android.view.View;
import org.cniska.phaser.debug.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class JSONParser {

	// Static variables
	// ----------------------------------------

	private static final String ENCODING = "iso-8859-1";

	// Member variables
	// ----------------------------------------

	protected View view;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new parser.
	 *
	 * @param view The parent view.
	 */
	public JSONParser(View view) {
		this.view = view;
	}

	/**
	 * Parses the JSON file with the given resource id.
	 *
	 * @param resourceId The resource identifier.
	 * @return The JSON object.
	 */
	public JSONObject parse(int resourceId) {
		String jsonString = null;
		JSONObject result = null;

		InputStream in = view.getResources().openRawResource(resourceId);

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, ENCODING), 8);
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}
			in.close();
			jsonString = sb.toString();
		} catch (UnsupportedEncodingException e) {
			Logger.error(getClass().getCanonicalName(), e.toString());
		} catch (IOException e) {
			Logger.error(getClass().getCanonicalName(), e.toString());
		}

		if (jsonString != null) {
			try {
				result = new JSONObject(jsonString);
			} catch (JSONException e) {
				Logger.error(getClass().getCanonicalName(), e.toString());
			}
		}

		return result;
	}
}
