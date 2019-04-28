package Visuals;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import NotDefault.Vector;


public class BuildPhase 
{
	//this point represents the offset from the origin, and helps me make scrolling possible
	private static int viewX;
	private static int viewY;
	//for selection tool
	private static Rectangle selectTool;
    //these are the clicks themselves, select-x1,y1 and select-x2,y2
		private static int sx1;
		private static int sy1;
		private static int sx2;
		private static int sy2;
	//these are the same numbers, but reassigned so the selectTool doesn't have to start in any given corner
		private static int topY;
		private static int bottomY;
		private static int leftX;
		private static int rightX;
	//tells us whether the selected key is being pressed
		private static HashMap<Integer, Boolean> keys;
	private static Color selectedC;
	private static int area=0;
	private static Ship ship;
	/*the level of the part. Building will start with the "foundation", or the walls and tiles.
	The builder can then move on to more complicated parts and furniture, then the networking between them, 
	and finally people/crew, although maybe crew shouldn't happen in the build phase.*/
		private static int partLevel;
		private static Gui[] guis;
		private static Gui materialSelect;
	private static int personX = 700;
	private static int personY = 400;
	//current gui
		private static Gui gui;
	
	public static void init(Board b)
	{
		viewX = 0;
		viewY = 0;
		b.setBackground(Color.BLUE);
		keys = new HashMap<Integer, Boolean>();
	    	keys.put(KeyEvent.VK_UP,false);
	    	keys.put(KeyEvent.VK_DOWN,false);
	    	keys.put(KeyEvent.VK_LEFT,false);
	    	keys.put(KeyEvent.VK_RIGHT,false);
	    	keys.put(KeyEvent.VK_W,false);
	    	keys.put(KeyEvent.VK_A,false);
	    	keys.put(KeyEvent.VK_S,false);
	    	keys.put(KeyEvent.VK_D,false);
	    	keys.put(KeyEvent.VK_DELETE, false);
	    	keys.put(KeyEvent.VK_SHIFT, false);
	    	
	    selectedC = new Color(128,128,128);//selectedC=Color.gray;
    	partLevel=0;
    	ship = new Ship(0,0);
    	
    	//gui
	    	guis = new Gui[3];
	    	GuiButton guiButtons[] = new GuiButton[2];
	    		for(int i=0; i<guiButtons.length;i++)
	    		{
	    			guiButtons[i]=new GuiButton(i*140,800-70,140,70, "     "+(i+1),new Color(60,70,70),true,36);
	    		}
	    		materialSelect = new Gui(guiButtons);
	    	guiButtons = new GuiButton[2];
	    		guiButtons[0] = new GuiButton(0,800-140,140,70,"    Wall",new Color(80,70,70),true,36);
	    		guiButtons[1] = new GuiButton(140,800-140 ,140,70,"    Tile" ,new Color(80,70,70),true,36);
	    		guis[0] = new Gui(guiButtons);
	    	guiButtons = new GuiButton[2];
	    		guiButtons[0] = new GuiButton(0,800-140 ,200,70,"     Engine" ,new Color(80,70,70),true,36);
	    		guiButtons[1] = new GuiButton(200,800-140 ,200,70,"   Computer" ,new Color(80,70,70),true,36);
	    		guis[1] = new Gui(guiButtons);
	    	gui = guis[partLevel];
	}
		
	//will also be mode based
    public static void update(Graphics g, Board b) 
    {
        Graphics2D g2d = (Graphics2D) g;
    	manageGui();
    	manageView();
        //this is the building space rectangle
	        g2d.setColor(Color.black);
	        g2d.fillRect(viewX,viewY,1400,800);
        ship.draw(g2d, new Vector(viewX, viewY),b, partLevel);
        //draws the selectTool
	        if(selectTool!=null)
	        {
	        	g2d.setColor(Color.WHITE);
	        	g2d.drawRect((int)(selectTool.x+viewX), (int)(selectTool.y+viewY), (int)(selectTool.width), (int)(selectTool.height));
	        }
	    gui.draw(g2d);
	    g2d.setColor(Color.GREEN);
    	g2d.fillOval(personX+viewX, personY+viewY, 10,10);
	    for(int i=0; i<gui.buttons.length; i++)
		{
			if(gui.buttons[i].pressed)
			{
				materialSelect.draw(g2d);
			}
		}
    }
    private static void manageGui()
    {
    	gui = guis[partLevel];
		for(int i=0; i<gui.buttons.length; i++)
		{
			if(gui.buttons[i].pressed)
			{
				if(gui.lastPressed!=i)
				{
					gui.buttons[i].pressed=false;
					for(int j=0; j<materialSelect.buttons.length; j++)
    	    		{
						materialSelect.buttons[j].pressed=false;
						materialSelect.lastPressed=-1;
    	    		}
				}
	    		for(int j=0; j<materialSelect.buttons.length; j++)
	    		{
	    			if(materialSelect.lastPressed!=j)
	    			{
	    				materialSelect.buttons[j].pressed=false;
	    			}
	    		}
			}
		}
		if(gui.lastPressed==0)
		{
			
		}
		else if(gui.lastPressed==1)
		{
			
		}
		else if(gui.lastPressed==2)
		{
			
		}
		else if(gui.lastPressed==3)
		{
			
		}
    }
    private static void deleteSelected()
    {
    	if(selectTool!=null)
    	{
    		int minBoxX = (selectTool.x+25)/50;
    		int minBoxY = (selectTool.y+25)/50;
    		int maxBoxX = (selectTool.x+selectTool.width-25)/50;
    		int maxBoxY = (selectTool.y+selectTool.height-25)/50;	
    		for(int i=minBoxX; i<=maxBoxX; i++)
    		{
    			for(int j=minBoxY; j<=maxBoxY; j++)
    			{
    				ship.tiles[i][j]=null;
    			}
    		}
    		minBoxX = (selectTool.x)/50+1;
    		maxBoxX = (selectTool.x+selectTool.width)/50;
    		for(int i=minBoxX; i<=maxBoxX; i++)
    		{
    			for(int j=minBoxY; j<=maxBoxY; j++)
    			{
    				ship.verticalWalls[i][j]=null;
    			}
    		}
    		
    		maxBoxY = (selectTool.y+selectTool.height)/50;
    		minBoxY = (selectTool.y)/50+1;
    		minBoxX = (selectTool.x+25)/50;
    		maxBoxX = (selectTool.x+selectTool.width-25)/50;
    		
    		
    		for(int i=minBoxX; i<=maxBoxX; i++)
    		{
    			for(int j=minBoxY; j<=maxBoxY; j++)
    			{
    				ship.horizontalWalls[i][j]=null;
    			}
    		}
    	}
    }
    //will probably not be mode based, but might be
    private static void manageView()
    {
    	boolean up = keys.get(KeyEvent.VK_UP);
    	boolean down = keys.get(KeyEvent.VK_DOWN);
    	if((up&&down)||!(up||down))
    	{
    	}
    	else if(up)
    	{
    		viewX+=5;
    		personX-=5;
    	}
    	else if(down)
    	{
    		viewX-=5;
    		personX+=5;
    	}
    	boolean left = keys.get(KeyEvent.VK_LEFT);
    	boolean right = keys.get(KeyEvent.VK_RIGHT);
    	if((left&&right)||!(left||right))
    	{
    	}
    	else if(left)
    	{
    		viewX+=5;
    		personX-=5;
    	}
    	else if(right)
    	{
    		viewX-=5;
    		personX+=5;
    	}
    }
    
    public static void keyPressed(KeyEvent e)
    {
    	int key = e.getKeyCode();
    	keys.put(e.getKeyCode(), true);
    	if(key==KeyEvent.VK_DELETE)
        {
        	deleteSelected();
        }
    	else if(key==KeyEvent.VK_ESCAPE)
    	{
    		Board.mode = "build";
    	}
    	else
    	{
    		if(keys.get(KeyEvent.VK_SHIFT))
    		{
        		if(key=='=')
        		{
        			partLevel++;
        		}
    		}
    		else if(key=='-')
    		{
    			partLevel--;
    		}
    	}
    }
    public static void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        keys.put(key, false);
    }
    
    public static void mouseClicked(MouseEvent e) 
	{
		selectTool=null;
		if(e.getY()<800-150)
		{
    		int xClick = (int)(e.getX()-viewX);
    		int yClick = (int)(e.getY()-viewY);
    		if(partLevel==0)
    		{
    			if(gui.buttons[0].pressed&&materialSelect.lastPressed!=-1)
    			{
		    		if(Math.abs(yClick-Math.round(yClick/50)*50)<6.0)//horizontal wall
		    		{
		    			//walls.add(new Wall(xClick-xClick%50,Math.round(yClick/50)*50,xClick-xClick%50+50,Math.round(yClick/50)*50));
		    			ship.addHorizontalWall(new Wall(Math.round(xClick/50)*50,Math.round(yClick/50)*50,false));
		    		}
		    		else if(Math.abs(yClick-Math.round(yClick/50)*50)>44.0)
		    		{
		    			ship.addHorizontalWall(new Wall(Math.round(xClick/50)*50,Math.round(yClick/50+1)*50,false));
		    		}
		    		else if(Math.abs(xClick-Math.round(xClick/50)*50)<6.0)//vertical wall
		    		{
		    			//walls.add(new Wall(Math.round(xClick/50)*50,yClick-yClick%50,Math.round(xClick/50)*50,yClick-yClick%50+50));
		    			ship.addVerticalWall(new Wall(Math.round(xClick/50)*50,Math.round(yClick/50)*50,true));
		    		}
		    		else if((Math.abs(xClick-Math.round(xClick/50)*50)>44.0))
		    		{
		    			ship.addVerticalWall(new Wall(Math.round(xClick/50+1)*50,Math.round(yClick/50)*50,true));
		    		}
    			}
    			else if(gui.buttons[1].pressed&&materialSelect.lastPressed!=-1)
    			{
    				double x = ((int)((xClick)/50))*50;
		    		double y = ((int)((yClick)/50))*50;
		    		ship.addTile(new Tile(x,y,selectedC));
    			}
    		}
    		else if(partLevel==1)
    		{
    			if(gui.buttons[0].pressed&&materialSelect.lastPressed!=-1)
    			{
    				double x = ((int)((xClick)/50))*50;
		    		double y = ((int)((yClick)/50))*50;
		    		ship.addTile(new Tile(x,y,"src//engine.png"));
    			}
    			else if(gui.buttons[1].pressed&&materialSelect.lastPressed!=-1)
    			{
    				double x = ((int)((xClick)/50))*50;
		    		double y = ((int)((yClick)/50))*50;
		    		if(ship.tiles[(int)(x/50)][(int)(y/50)]!=null)
		    		{
		    			ship.addTile(new Tile(x,y,"src//computer.png"));
		    		}
    			}
    		}
		}
	}
    public static void mousePressed(MouseEvent e)
	{
		gui.buttonPressed(e.getX(), e.getY());
		materialSelect.buttonPressed(e.getX(), e.getY());
		sx1 = (int) (e.getX()-viewX);
		sy1 = (int) (e.getY()-viewY);
	}
    public static void mouseDragged(MouseEvent e)
	{
		sx2 = (int) (e.getX()-viewX);
		sy2 = (int) (e.getY()-viewY);
		if(Math.abs(sx2-sx1)>3&&Math.abs(sy2-sy1)>3)
		{
    		if(sx1<sx2){
    			leftX=sx1;
    			rightX=sx2;
    		}
    		else{
    			rightX=sx1;
    			leftX=sx2;
    		}
    		if(sy1<sy2){
    			topY=sy1;
    			bottomY=sy2;
    		}
    		else{
    			bottomY=sy1;
    			topY=sy2;
    		}
    		
    		selectTool = new Rectangle(leftX, topY, rightX-leftX, bottomY-topY);
		}
	}
    public static void mouseReleased(MouseEvent e)
	{

		sx2 = (int) (e.getX()-viewX);
		sy2 = (int) (e.getY()-viewY);
		if(!(Math.abs(sx2-sx1)>3&&Math.abs(sy2-sy1)>3))
		{
			mouseClicked(e);
		}
	}
    
}
