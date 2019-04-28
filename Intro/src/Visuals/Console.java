package Visuals;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.lang.Object;
import java.util.Scanner;

import NotDefault.PhysicsObject;
import NotDefault.SciNumb;
import NotDefault.Vector;


public class Console 
{
	String current;
	int fontSize;
	String font;
	int cursorPos;
	String path;
	
	PhysicsObject ship;
	PhysicsObject star;
	public Console(String s, int fS, String f) 
	{
		path = "C:";
		current=">"+s;
		fontSize=fS;
		font=f;
		cursorPos=0;
		ship = new PhysicsObject(new Vector(new SciNumb(0,0),new SciNumb(0,0),new SciNumb(0,0)),new SciNumb(3.33,5));
		ship.velocity = new Vector(0,3064.7,0);
		star = new PhysicsObject(0,0,0,new SciNumb("6e24"));
		star.velocity= new Vector(0,0,0);
	}
	
	public int getCursorDepth()
	{
		String[] lines = current.split("\n");
		return lines[lines.length-1].length();
	}
	
	public void print(Object obj)
	{
		current = current + obj.toString();
		String[] lines = obj.toString().split("\n");
		cursorPos+=lines.length;
	}
	public void println(Object obj)
	{
		print(obj);
		//cursorPos++;
		current = current + "\n";
	}
	public void onEnterPressed()
	{
		String[] lines = current.split("\n");
		String lastLine = lines[lines.length-1];
		Scanner s;
		if(lastLine.startsWith(">"))
		{
			lastLine = lastLine.toLowerCase().substring(1);
			if(lastLine.toLowerCase().startsWith("cd"))
			{
				s=new Scanner(lastLine.substring(2));
				try
				{
					path=path+s.nextLine();
				}
				catch(Exception e)
				{
					println("Could not enter directory. Try \"cd [[desired directory]]\"");
				}
			}
			else if(lastLine.startsWith("dir"))
			{
				s=new Scanner(lastLine.substring(2));
				println("This will eventually print other things at this directory");
			}
			else if(lastLine.startsWith("clear"))
			{
				current="";
				cursorPos=0;
			}
			else if(lastLine.toLowerCase().startsWith("ship:position"))
			{
				s=new Scanner(lastLine.substring(13));
				//println(lastLine.substring(14, lastLine.length()));
				try
				{
					double x = s.nextDouble();
					double y = s.nextDouble();
					double z = s.nextDouble();
					Vector velocity = ship.velocity;
					ship = new PhysicsObject(x,y,z);
					ship.velocity = velocity;
				}
				catch(Exception e)
				{
					println("position: <"+ship.position.x[0]+", "+ship.position.x[1]+", "+ship.position.x[2]+">");
				}
			}
			else if(lastLine.toLowerCase().startsWith("ship:velocity"))
			{
				s=new Scanner(lastLine.substring(13));
				//println(lastLine.substring(14, lastLine.length()));
				try
				{
					double x = s.nextDouble();
					double y = s.nextDouble();
					double z = s.nextDouble();
					ship.velocity= new Vector(x,y,z);
				}
				catch(Exception e)
				{
					println("velocity: <"+ship.velocity.x[0]+", "+ship.velocity.x[1]+", "+ship.velocity.x[2]+">");
				}
			}
			else if(lastLine.toLowerCase().startsWith("ship:update"))
			{
				ship.orbit(3600, star);
				println("position: <"+ship.position.x[0]+", "+ship.position.x[1]+", "+ship.position.x[2]+">");
			}
			else if(lastLine.toLowerCase().startsWith("ship"))
			{
				println("There are many things to do with a ship. To set the ships position or velocity,\nplease enter \"Ship:position\"(or Ship:velocity) with the x, y, and z components separated by spaces \n(e.g. Ship:position 5.3 10 15.25555)\nTo read a ships position or velocity, simply ask \"position\" or \"velocity\". \nTo update the ship please say \"update [time interval]\".\nThe ship has a default mass of 250 kg.");
			}
		}
	}
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
    	char c = e.getKeyChar();
    	if(key==(KeyEvent.VK_SHIFT))
        {
        	
        }
        else if(key==(KeyEvent.VK_ENTER))
        {
        	current=current+"\n";
        	cursorPos++;
        	onEnterPressed();
        	current = current+">";
        }
        else if(key==KeyEvent.VK_BACK_SPACE)
        {
        	char cha = current.charAt(current.length()-1);
        	if(current.length()>=1&&c!='\n'&&cha!='>')
        	{
        		current = current.substring(0, current.length()-1);
        	}
        }
        else
        {
        	current=current+c;
        }
	}
	
	public void draw(Graphics2D g2d)
	{
		try
		{
			g2d.setColor(Color.black);
			g2d.fillRect(0, 0, 1400, 800);
			g2d.setColor(Color.white);
			g2d.setFont(new Font(font,0,fontSize));
			int depth = getCursorDepth();
			String[] lines = current.split("\n");
			int offset = 800-((lines.length+2)*fontSize);
			if(offset>0)//if the y depth is not below the page
			{
				if((int)((depth+3)*fontSize/2)+(int)(fontSize)<1340)//if the x depth is not off the page to the right
				{
					for(int i=0;i<lines.length;i++)
					{
						g2d.drawString(lines[i],20,(int)(fontSize)+(i)*fontSize);
					}
					g2d.fillRect((int)((depth+1)*fontSize/1.85),((lines.length-1)*fontSize)+fontSize/5,2,(int)(fontSize*.9));
				}
				else
				{
					for(int i=0;i<lines.length;i++)
					{
						g2d.drawString(lines[i],(int)(1340-(depth*fontSize/1.85)),(int)(fontSize)+(i)*fontSize);
					}
					g2d.fillRect(1340,30+(int)(fontSize/(1.87))+(cursorPos-1)*fontSize,2,(int)(fontSize*.9));
				}
			}
			else //if the y axis is off the page
			{
				if((int)((depth+3)*fontSize/2)+(int)(fontSize)<1340)
				{
					for(int i=lines.length-1;i>=0;i--)
					{
						g2d.drawString(lines[i],20,offset+i*fontSize);
					}
					g2d.fillRect((int)((depth+1)*fontSize/1.85),offset+(lines.length-2)*fontSize+(int)(.24*fontSize),2,(int)(fontSize*.9));
				}
				else
				{
					for(int i=lines.length-1;i>=0;i--)
					{
						g2d.drawString(lines[i],(int)(1340-((depth+1)*fontSize/1.85)),offset+i*fontSize);
					}
					g2d.fillRect(1340,offset+(int)((cursorPos-1)*fontSize)+fontSize/4,2,(int)(fontSize*.9));
				}
			}
		}
		catch(Exception e)
		{}
		
	}
}
