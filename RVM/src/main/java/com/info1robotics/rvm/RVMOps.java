package com.info1robotics.rvm;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RVMOps {

	RVMOps()
	{
		RVLocalStorage.init();
	}

	void loadProject(JsonObject data)
	{
		if(data == null) Log.d("RVM_DEBUG", "hjkhjkh");
		RVMFileUtility.saveJsonOnFilesystem(data);
	}
}
