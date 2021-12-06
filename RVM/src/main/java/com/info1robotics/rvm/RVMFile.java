package com.info1robotics.rvm;

import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RVMFile {

//	private final String[] flagList= {"teleop" , "auto", "disbaled", "name", "oninit", "thread"};

	// Metadata
	public String name;
	public String uploadedBy;
	public String filePath;
	private HashMap<String, String> flags = new HashMap<String, String>();

	public String content;

	public RVMFile(String name, String filePath, String uploadedBy, String content)
	{
		this.name = name;
		this.filePath = filePath;
		this.uploadedBy = uploadedBy;
		this.content = content;

		parseFlags();
	}

	public boolean hasFlag(String flag)
	{
		return flags.containsKey(flag);
	}

	public String getFlagValue(String flag)
	{
		return flags.get(flag);
	}

	private void parseFlags()
	{
		if( content.length() < 2 || !(content.charAt(0) == '/' && content.charAt(1) == '/')) return;

		// substring to ignore // at the line start fucks up the system
		String[] strFlags = content.subSequence(2, content.indexOf('\n') != -1 ? content.indexOf('\n') : content.length()).toString().split(" ");

		for (String strFlag : strFlags) {
			if(strFlag.contains(":"))
			{
				flags.put(strFlag.substring(0, content.indexOf(':')), strFlag.substring(content.indexOf(':') + 1));
			}
			else {
				flags.put(strFlag, null);
			}
		}
	}

}
