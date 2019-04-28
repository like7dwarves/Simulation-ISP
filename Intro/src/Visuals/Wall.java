package Visuals;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import NotDefault.Vector;


public class Wall extends Vector {

	//if wall is horzontal
		boolean vertical;
	
	//Material material
	//Double thickness
	
	public Wall(double x1, double y1, boolean _vertical) 
	{
		super(x1,y1);
		vertical = _vertical;
	}

	public Vector draw(Graphics2D g2d, Vector ref)
	{
		g2d.setColor(Color.WHITE);
		g2d.setStroke(new BasicStroke(3));
		Vector otherPoint;
		if(vertical)
		{
			otherPoint = new Vector(x[0].toDouble()+ref.x[0].toDouble(), x[1].toDouble()+50+ref.x[1].toDouble());
		}
		else
		{
			otherPoint = new Vector(x[0].toDouble()+50+ref.x[0].toDouble(), x[1].toDouble()+ref.x[1].toDouble());
		}
		g2d.drawLine((int)(x[0].toDouble()+ref.x[0].toDouble()),(int)(x[1].toDouble()+ref.x[1].toDouble()),(int)otherPoint.x[0].toDouble(),(int)otherPoint.x[1].toDouble());
		g2d.setStroke(new BasicStroke(0));
		return this;
	}
}
