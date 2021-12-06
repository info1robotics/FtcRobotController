package com.info1robotics.rvm;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class RVLocalStorage {

	private static Context context;

	public static boolean init()
	{
		if(context == null) context = getContext();
		if(context == null) return false;
		return true;
	}

	public static String[] listFiles()
	{
		return context.fileList();
	}

	public static void saveFile(String filename, String data)
	{
		try
		{
			FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
			fos.write(data.getBytes());
			fos.flush();
			fos.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static String readFile(String filename)
	{
		try {
			FileInputStream fin = context.openFileInput(filename);
			int a;
			StringBuilder temp = new StringBuilder();
			while ((a = fin.read()) != -1) {
				temp.append((char) a);
			}
			fin.close();
			return temp.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "something went wrong";
	}

	private static Context getContext() {
		try {
			final Class<?> activityThreadClass =
					Class.forName("android.app.ActivityThread");
			//find and load the main activity method
			final Method method = activityThreadClass.getMethod("currentApplication");
			return (Application) method.invoke(null, (Object[]) null);
		} catch (final Throwable e) {
			// handle exception
			return null;
		}
	}
}
