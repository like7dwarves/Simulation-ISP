package Orbital;

import java.util.ArrayList;
import java.util.List;

import NotDefault.SciNumb;
import NotDefault.Vector;

public abstract class Celestial implements Orbited, Orbiter 
{	
	
	Vector position;
	Vector velocity;
	
	private SciNumb mass;
	
	public Celestial(Vector position, Vector velocity, int mass)
	{
		this.position = position;
		this.velocity = velocity;
		this.mass = new SciNumb(mass,0).normalize();
	}
	public Celestial(Vector position, Vector velocity, SciNumb mass)
	{
		this.position = position;
		this.velocity = velocity;
		this.mass = mass;
	}

	@Override
	public Vector getVel()
	{
		return velocity;
	}
	
	@Override
	public Vector getPos() 
	{
		return position;
	}

	@Override
	public void update(int dt, Orbited[] influences) 
	{		
		for(int i=0; i<influences.length; i++)
		{
			if(this!=influences[i])
			{
				Vector relPos1 = getPos().add(influences[i].getPos().scale(-1.0));
				SciNumb aMag = new SciNumb("-6.67e-11").multiply(influences[i].getMass()).divide(relPos1.mag().pow(2));
				Vector acc = relPos1.hat().scale(aMag);
				velocity = velocity.add(acc.scale(dt));
				position = position.add(velocity.scale(dt));
			}
		}
	}

	public SciNumb getPotentialEnergy(Orbited o) 
	{
		Vector relPos = getPos().add(o.getPos().scale(-1.0));
		return new SciNumb("-6.67e-11").multiply(o.getMass()).multiply(getMass()).divide(relPos.mag());
	}
	
	public SciNumb getKineticEnergy()
	{
		return new SciNumb("5e-1").multiply(velocity.mag().pow(2)).multiply(mass);
	}
	
	@Override
	public SciNumb getMass()
	{
		return mass;
	}
	
	public Vector getForce(Orbited o)
	{
		Vector relPos = this.getPos().add(o.getPos().scale(-1.0));
		return relPos.hat().scale(new SciNumb("-6.67e-11").multiply(o.getMass()).multiply(getMass()).divide(relPos.mag().pow(2)));
	}
}
