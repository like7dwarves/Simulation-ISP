package Visuals;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import NotDefault.Vector;


public class GuiButton extends Vector {
	
	String s;
	boolean toggle; //whether this button can be toggled or not
	boolean pressed; //if it can be toggled, tells us whether the button is pressed or up
	int width;
	int height;
	Color c;
	int fontSize;
	Color textColor;
	
	public GuiButton(double _x, double _y, int _width, int _height,String _s,Color _c, boolean _toggle, int _fontSize) {
		super(_x, _y);
		c=_c;
		toggle=_toggle;
		pressed=false;
		fontSize=_fontSize;
		textColor=Color.white;
		s=_s;
		width=_width;
		height=_height;
	}
	public GuiButton(double _x, double _y, int _width, int _height,String _s) {
		super(_x, _y);
		c=Color.gray;
		toggle=true;
		pressed=false;
		fontSize=36;
		textColor=Color.white;
		s=_s;
		width=_width;
		height=_height;
	}
	
	public void draw(Vector ref, Graphics2D g2d)
	{
		if(pressed==false)
		{
			g2d.setColor(c);
			g2d.fillRect((int)(x[0].toDouble()+ref.x[0].toDouble()),(int)(x[1].toDouble()+ref.x[1].toDouble()),width,height);
			g2d.setColor(textColor);
		}
		else
		{
			g2d.setColor(textColor);
			g2d.fillRect((int)(x[0].toDouble()+ref.x[0].toDouble()),(int)(x[1].toDouble()+ref.x[1].toDouble()),width,height);
			g2d.setColor(c);
		}
			g2d.setFont(new Font("serif",0,fontSize));
			g2d.drawString(s,(int)(x[0].toDouble()+ref.x[0].toDouble()),(int)(x[1].toDouble()+ref.x[1].toDouble()));
	}
	public GuiButton draw(Graphics2D g2d)
	{
		try
		{
			if(pressed==false)
			{
				g2d.setColor(c);
				g2d.fillRect((int)(x[0].toDouble()),(int)(x[1].toDouble()),width,height);
				g2d.setColor(textColor);
			}
			else
			{
				g2d.setColor(textColor);
				g2d.fillRect((int)(x[0].toDouble()),(int)(x[1].toDouble()),width,height);
				g2d.setColor(c);
			}
			g2d.drawRect((int)(x[0].toDouble()),(int)(x[1].toDouble()),width,height);
			g2d.setFont(new Font("serif",0,fontSize));
			g2d.drawString(s,(int)(x[0].toDouble()+5),(int)(x[1].toDouble()+fontSize*1.2));
		}
		catch(Exception e)
		{}
		return this;
	}
}
