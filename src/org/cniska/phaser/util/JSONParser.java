package org.cniska.phaser.util;

import android.view.View;
import org.cniska.phaser.debug.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class JSONParser {

	private static final String ENCODING = "iso-8859-1";
	protected View view;

	public JSONParser(View view) {
		this.view = view;
	}

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
