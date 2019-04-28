package Orbital;

import NotDefault.SciNumb;
import NotDefault.Vector;

public class Planet extends Celestial {

	public Planet(Vector position, Vector velocity, int mass) 
	{
		super(position, velocity, mass);
	}
	public Planet(Vector position, Vector velocity, SciNumb mass) 
	{
		super(position, velocity, mass);
	}

}
