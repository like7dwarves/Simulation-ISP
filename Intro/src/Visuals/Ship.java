package Visuals;
import java.awt.Graphics2D;

import NotDefault.Vector;

//this might seeem a little silly of an idea, but lets say that a Ship is just the drawing capabilities of a PhysicsObject
public class Ship extends Vector {

	//56,32
	public Tile[][] tiles;
	public Wall[][] verticalWalls;
	public Wall[][] horizontalWalls;
	
	Vector velocity;

	public Ship(double _x, double _y) 
	{
		super(_x, _y);
		tiles = new Tile[28][16];
		verticalWalls = new Wall[29][16];
		horizontalWalls = new Wall[28][17];
		velocity = new Vector(0,0,0);
	}

	public Ship(double _x, double _y, double _z) 
	{
		super(_x, _y, _z);
	}
	
	
	public Ship draw(Graphics2D g2d, Vector ref, Board b)
	{
		try
		{
			for(int i=0; i<tiles.length; i++)
			{
				for(int j=0; j<tiles[i].length;j++)
				{
					if(tiles[i][j]!=null)
					{
						tiles[i][j].draw(g2d,ref,b);
					}
				}
			}
			for(int i=0; i<verticalWalls.length; i++)
			{
				for(int j=0; j<verticalWalls[i].length;j++)
				{
					if(verticalWalls[i][j]!=null)
					{
						verticalWalls[i][j].draw(g2d,ref);
					}
				}
			}
			for(int i=0; i<horizontalWalls.length; i++)
			{
				for(int j=0; j<horizontalWalls[i].length;j++)
				{
					if(horizontalWalls[i][j]!=null)
					{
						horizontalWalls[i][j].draw(g2d,ref);
					}
				}
			}
		}
		catch(NullPointerException e)
		{
		}
		return this;
	}
	public Ship draw(Graphics2D g2d, Vector ref, Board b,int partLevel)
	{
		try
		{
			if(partLevel>=0)
			{
				for(int i=0; i<tiles.length; i++)
				{
					for(int j=0; j<tiles[i].length;j++)
					{
						if(tiles[i][j]!=null)
						{
							tiles[i][j].draw(g2d,ref,b);
						}
					}
				}
				for(int i=0; i<verticalWalls.length; i++)
				{
					for(int j=0; j<verticalWalls[i].length;j++)
					{
						if(verticalWalls[i][j]!=null)
						{
							verticalWalls[i][j].draw(g2d,ref);
						}
					}
				}
				for(int i=0; i<horizontalWalls.length; i++)
				{
					for(int j=0; j<horizontalWalls[i].length;j++)
					{
						if(horizontalWalls[i][j]!=null)
						{
							horizontalWalls[i][j].draw(g2d,ref);
						}
					}
				}
			}
			if(partLevel>=1)
			{
				
			}
		}
		catch(NullPointerException e)
		{
		}
		return this;
	}
	
	public void addTile(Tile t)
	{
		int x = (int) t.x[0].toDouble()/50;
		int y = (int) t.x[1].toDouble()/50;
		if(0<=x && x<28 && 0<=y && y<16)
		{
			if(tiles[x][y]==null||t.image!=null)
			{
				tiles[x][y]=t;
			}
		}
		
	}
	public void addHorizontalWall(Wall w)
	{
		//horizontal line
		int x = (int) w.x[0].toDouble()/50;
		int y = (int) w.x[1].toDouble()/50;
		if(0<=x && x<29 && 0<=y && y<=16)
		{
			horizontalWalls[x][y]=w;
		}
	}
	public void addVerticalWall(Wall w)
	{
		//vertical line
		int x = (int) w.x[0].toDouble()/50;
		int y = (int) w.x[1].toDouble()/50;
		if(0<=x && x<28 && 0<=y && y<=17)
		{
			verticalWalls[x][y]=w;
		}
	}
}
