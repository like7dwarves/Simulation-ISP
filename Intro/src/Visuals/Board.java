package Visuals;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;










import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import NotDefault.Chemical;
import NotDefault.ChemicalReaction;
import NotDefault.Matrix;
import NotDefault.Mixture;
import NotDefault.ObjectPart;
import NotDefault.PhysicsObject;
import NotDefault.SciNumb;
import NotDefault.Vector;
import Orbital.Celestial;
import Orbital.OrbitSystem;
import Orbital.Orbited;
import Orbital.Planet;


public class Board extends JPanel implements ActionListener 
{
	private static final long serialVersionUID = 1L;
	
	//I don't quite understand events yet, but I understand them enough to know that this is neccessary for them to function
		private Timer timer;
		private final int DELAY = 10;
    //the "mode" of the game, whether it is building, flightPlanning, land, or some others I haven't thought of
    	static String mode;
   	Console console;
   	
   	ObjectPart[] blade;
   	
   	PhysicsObject fan; 
   	
   	
    public Board()
    {
        initBoard();
    }
    
    private void initBoard() 
    {    	
        //allows keys, mice, and mouseDragged() to be used
	        addKeyListener(new TAdapter());
	        addMouseListener(new MAdapter());
	        addMouseMotionListener(new MAdapter());
	        setFocusable(true);
	    BuildPhase.init(this);	
	    OrbitSystem system = new OrbitSystem();
	    system.addBody(new Planet(new Vector(0,0,0),new Vector(0,0,0), new SciNumb("1.989e30")));
	    system.addBody(new Planet(new Vector(new SciNumb(0,0),new SciNumb("1.496e11"),new SciNumb(0,0)),new Vector(new SciNumb(3,4),new SciNumb(0,0),new SciNumb(0,0)), new SciNumb("5.972e24")));
	    
	    //OrbitalPlanner.init(system, craft, this);
        timer = new Timer(DELAY, this);
        timer.start(); 
        mode = "console";
        console = new Console("",50,"Consolas");
        
        //System.out.println("asdfasd fa");
		system.update((int)new SciNumb("1.577e7").multiply(new SciNumb(2,0)).toDouble(),new Orbited[0]);
		system.update(3600*24*8, new Orbited[0]);
		//System.out.println(system.getBodies().get(1).getPos());
        //Mixture m = new Mixture("Stomach Acid", new Chemical[]{Chemical.Hp, Chemical.H2O}, new double[]{.8,.2});
        //m.addMixture(new Mixture("a", new Chemical[]{Chemical.C6H1206, Chemical.H2O}, new double[]{.8,.2}));
        //System.out.println(m);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(mode.equals("build"))
        {
        	BuildPhase.update(g, this);
        }
        else
        {
        	Graphics2D g2d = (Graphics2D) g;
        	console.draw(g2d);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        repaint();  
    }
    
    
    private class TAdapter extends KeyAdapter 
    {

        @Override
        public void keyPressed(KeyEvent e) 
        {
        	if(mode.equals("console"))
        	{
        		if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
        		{
        			mode="build";
        		}
        		else
        		{
        			console.keyPressed(e);
        		}
        	}
        	else if(mode.equals("build"))
        	{
        		BuildPhase.keyPressed(e);
        	}
        }
        @Override
        public void keyReleased(KeyEvent e) 
        { 
        	if(mode.equals("build"))
        		BuildPhase.keyReleased(e);
        }
        
    }
    class MAdapter extends MouseAdapter
    {
    	@Override
    	public void mouseClicked(MouseEvent e) 
    	{
    		if(mode.equals("build"))
    			BuildPhase.mouseClicked(e);
    	}
    	
    	@Override
    	public void mousePressed(MouseEvent e)
    	{
    		if(mode.equals("build"))
    			BuildPhase.mousePressed(e);
    	}
    	
    	@Override
    	public void mouseDragged(MouseEvent e)
    	{
    		if(mode.equals("build"))
    			BuildPhase.mouseDragged(e);
    	}
    	@Override
    	public void mouseReleased(MouseEvent e)
    	{
    		if(mode.equals("build"))
    			BuildPhase.mouseReleased(e);
    	}
    }
}