package org.firstinspires.ftc.teamcode.X;

public class Position {
	public float x, y, h;
	public Position(float x, float y, float h)
	{
		this.x = x;
		this.y = y;
		this.h = h;
	}

	public void normaliseAngle()
	{
		h = (h % (2 * (float)Math.PI));
		if(h < 0) h = 2 * (float)Math.PI - h;
	}
}
