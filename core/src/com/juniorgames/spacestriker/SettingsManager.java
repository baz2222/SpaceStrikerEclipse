package com.juniorgames.spacestriker;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public final class SettingsManager {
public static String currentLocalization = "en";//default value
public static String defaultLocalization = "en";//default value
public static int screenWidth = 800;
public static int screenHeight = 600;
public static String screenOrientation = "landscape";
//=================================
private SettingsManager()
{}//constructor never runs
//=================================
public static void readSettings()
{
	try{
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("app.json"));
		JSONObject jsonObject = (JSONObject) obj;
		currentLocalization = (String) jsonObject.get("current-localization");
		defaultLocalization = (String) jsonObject.get("default-localization");
	} catch (Exception e) {
		e.printStackTrace();
	}//try-catch
}//readSettings
}
