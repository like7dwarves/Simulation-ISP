package Visuals;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTextField;

import NotDefault.PhysicsObject;
import Orbital.Celestial;
import Orbital.OrbitSystem;

public class OrbitalPlanner 
{
	static OrbitSystem system;
	///static PhysicsObject craft;
	static String code;//will eventually be part of the craft
	
	public static void init(OrbitSystem _system, Board b)
	{
		system = _system;
		//craft = _craft;
		JTextField j = new JTextField();
		j.setVisible(true);
		b.add(j);
	}
	
	public static void update(Graphics g, Board b)
	{
		Graphics2D g2d = (Graphics2D) g;
		//for(Celestial p : system.getBodies())
	}
	
	
}
