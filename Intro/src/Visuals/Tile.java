package Visuals;
import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

import NotDefault.Vector;


public class Tile extends Vector {

	Color c;

	Image image;
	
	public Tile(double _x, double _y, String imageFile) {
		super(_x, _y);
		ImageIcon ii = new ImageIcon(imageFile);
		image = ii.getImage();
		c = Color.GRAY;
	}
	public Tile(double _x, double _y) 
	{
		super(_x, _y);
	}
	
	public Tile(double _x, double _y, Color _c) 
	{
		super(_x, _y);
		c=new Color(_c.getRed()/2,_c.getGreen()/2,_c.getBlue()/2);
		System.out.println(new Color(c.getRed(),c.getGreen(),c.getBlue(),Color.TRANSLUCENT/2));
	}

	public Tile(double _x, double _y, double _z,Color _c) 
	{
		super(_x, _y, _z);
		c=_c;
	}
	
	public Tile draw(Graphics2D g2d, Vector ref, Board b)
	{
		if(image==null)
		{
			Color oldColor = g2d.getColor();
			g2d.setColor(c);
			try
			{
				g2d.fillRect((int) Math.round(x[0].toDouble()+ref.x[0].toDouble()),(int) Math.round(x[1].toDouble()+ref.x[1].toDouble()),50,50);
			}
			catch(NullPointerException e)
			{
			}
			g2d.setColor(oldColor);
			return this;
		}
		else
		{
			Color oldColor = g2d.getColor();
			g2d.setColor(c);
			try
			{
				g2d.drawImage(image,(int) Math.round(x[0].toDouble()+ref.x[0].toDouble()),(int) Math.round(x[1].toDouble()+ref.x[1].toDouble()),b);
			}
			catch(NullPointerException e)
			{
			}
			return this;
		}
	}
}
