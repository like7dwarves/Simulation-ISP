package Orbital;

import NotDefault.SciNumb;
import NotDefault.Vector;

public interface Orbiter 
{
	public SciNumb getMass();
	
	public Vector getPos();
	
	public Vector getVel();
	
	public Vector getForce(Orbited o);
	
	public SciNumb getPotentialEnergy(Orbited o);
	
	public void update(int dt, Orbited[] influences);
}
