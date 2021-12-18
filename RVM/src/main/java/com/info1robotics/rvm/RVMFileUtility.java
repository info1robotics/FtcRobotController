package com.info1robotics.rvm;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Iterator;

public class RVMFileUtility {

	public static JsonArray getFileSystemAsJson()
	{
		String[] files = RVLocalStorage.listFiles();
		JsonArray fs = new JsonArray();

		for (String filePath: files) {
			JsonObject file = new JsonObject();
			file.addProperty("name", filePath.substring(5).replace('$', '/'));
			file.addProperty("content", RVLocalStorage.getInstance().readFile(filePath));
			fs.add(file);
		}
		return fs;
	}

	public static ArrayList<RVMFile> GetFilesByExtension(String extension)
	{
		String[] filePaths = RVLocalStorage.listFiles();
		Log.d("RVM_DEBUG", "hhh1");
		ArrayList<RVMFile> files = new ArrayList<>();
		for (String filePath: filePaths) {
			if(filePath.matches(".*\\." + extension + "$"))
			{
				Log.d("RVM_DEBUG", "hhh" + filePath);
				files.add(new RVMFile(filePath.substring(5).replace('$', '/'),filePath,  null, RVLocalStorage.getInstance().readFile(filePath)));
				Log.d("RVM_DEBUG", "hhh" + files.get(files.size() - 1).hasFlag("auto"));
			}
		}
		Log.d("RVM_DEBUG", "hhh2");
		return files;
	}

	public static void saveJsonOnFilesystem(JsonObject json)
	{
		Iterator<JsonElement> files = json.get("files").getAsJsonArray().iterator();
		while (files.hasNext()) {
			JsonObject file = files.next().getAsJsonObject();
			RVLocalStorage.getInstance().saveFile("$RVM$" + file.get("name").getAsString().replace('/', '$'), file.get("content").getAsString());
		}
	}

	public static boolean cleanFilesystem()
	{
		return false;
	}

}
