package Visuals;

import java.awt.Graphics2D;

import NotDefault.Vector;


public class Gui 
{
	int lastPressed;
	GuiButton[] buttons;
	
	public Gui(GuiButton b1) 
	{
		buttons = new GuiButton[1];
		buttons[0] = b1;
		lastPressed=0;
	}
	public Gui(GuiButton b1,GuiButton b2) 
	{
		buttons = new GuiButton[2];
		buttons[0] = b1;
		buttons[1] = b2;
		lastPressed=-1;
	}
	public Gui(GuiButton b1, GuiButton b2, GuiButton b3) 
	{
		buttons = new GuiButton[3];
		buttons[0] = b1;
		buttons[1] = b2;
		buttons[2] = b3;
		lastPressed=-1;
	}
	public Gui(GuiButton[] _buttons)
	{
		buttons=_buttons;
		lastPressed=-1;
	}
	
	
	
	public GuiButton buttonPressed(int x, int y)
	{
		GuiButton returned=null;
		for(int i=0;i<buttons.length;i++)
		{
			if(buttons[i].x[0].toDouble()<x&&x<buttons[i].x[0].toDouble()+buttons[i].width&&buttons[i].x[1].toDouble()<y&&y<buttons[i].x[1].toDouble()+buttons[i].height)
			{
				if(buttons[i].pressed==true)
				{
					buttons[i].pressed=false;
				}
				else
				{
					buttons[i].pressed=true;
				}
				lastPressed=i;
				returned = buttons[i];
			}
		}
		return returned;
	}
	public void draw(Graphics2D g2d, Vector view)
	{
		for(int i=0;i<buttons.length;i++)
		{
			buttons[i].draw(view,g2d);
		}
	}
	public void draw(Graphics2D g2d)
	{
		for(int i=0;i<buttons.length;i++)
		{
			buttons[i].draw(g2d);
		}
	}
	
}
