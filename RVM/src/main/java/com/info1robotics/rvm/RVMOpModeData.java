package com.info1robotics.rvm;

public class RVMOpModeData {
	public String name;
	public int flavour; // 0 - auto ; 1 - teleop
	public String file;


	RVMOpModeData(String name, int flavour, String file)
	{
		this.name = name;
		this.flavour = flavour;
		this.file = file;

	}
}