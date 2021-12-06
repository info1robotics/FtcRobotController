package com.info1robotics.rvm;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Iterator;

public class RVMOpModeRegisterUtility {

	public static ArrayList<RVMOpModeData> getOpModeData()
	{
		RVLocalStorage.init();
		Gson gson = new Gson();
		Log.d("RVM_DEBUG", "hhh");
		ArrayList<RVMOpModeData> opModes = new ArrayList<RVMOpModeData>();
		Log.d("RVM_DEBUG", "hh1");
		ArrayList<RVMFile> files = RVMFileUtility.GetFilesByExtension("js");
		Log.d("RVM_DEBUG", "hh2");
		Log.d("RVM_DEBUG", Integer.toString(files.size()));
		for (RVMFile file : files) {
			Log.d("RVM_DEBUG", Boolean.toString(file.hasFlag("auto")));
			if((file.hasFlag("auto") || file.hasFlag("teleop")) && !file.hasFlag("disabled"))
			{
				opModes.add(new RVMOpModeData(
					file.hasFlag("name") ? file.getFlagValue("name") : file.name.substring(file.name.lastIndexOf("/") + 1, file.name.lastIndexOf(".")),
					file.hasFlag("auto") ? 0 : 1,
					file.filePath
				));
			}
		}

		return opModes;
	}
}
