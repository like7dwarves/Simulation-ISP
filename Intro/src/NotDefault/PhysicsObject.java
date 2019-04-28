package NotDefault;
import java.util.ArrayList;
import java.util.List;

import Shape.Shape3D;


public class PhysicsObject implements Forcer
{

	List<Vector> forces;
	
	List<ObjectPart> partsParts;
	List<PhysicsObject> objectParts;
	//ideally, objectParts is empty, but I want objects like spaceships to have multiple layers.
	
	Material material;
	//the shape is defined by the objects inside of it.
	
	Vector angle;
	Vector angularVelocity;
	//this is very confusing, but the x, y and z vectors represent radians/second rotated around 
	//a the center of mass through each point.
	public Vector position;
	public Vector velocity;
	
	
	//these variables are standins. I'll probably update them when significant changes to the ship occur, but not too often
		SciNumb mass;
		Vector centerOfMass;//preferably, this is a constant 0,0,0 and the rest of the ship is oriented around it.
	
	public PhysicsObject(double _x, double _y, double _z, SciNumb _mass) 
	{
		forces = new ArrayList<Vector>();
		position = new Vector(_x, _y, _z);
		mass=_mass;
	}
	public PhysicsObject(double _x, double _y, double _z, ObjectPart[] o) 
	{
		forces = new ArrayList<Vector>();
		position = new Vector(_x, _y, _z);
		partsParts = new ArrayList<ObjectPart>();
		for(int i=0; i<o.length; i++)
		{
			partsParts.add(o[i]);
			mass.add(new SciNumb(partsParts.get(i).mass,0).normalize());
		}
	}
	
	public PhysicsObject(SciNumb _x, SciNumb _y, SciNumb _z, SciNumb mass) 
	{
		position = new Vector(_x,_y,_z);
		this.mass=mass;
	}
	public PhysicsObject(Vector v, SciNumb mass) 
	{
		position = v;
		this.mass=mass;
	}
	public void getForced(Vector f)
	{
		forces.add(f);
	}
	
	public void exertForce(Vector f, PhysicsObject otherObject)
	{
		forces.add(f.scale(-1));
		otherObject.reactiveForce(new Vector(f.x[0], f.x[1], f.x[2]));
	}
	
	public void reactiveForce(Vector f)
	{
		forces.add(f);
	}
	
	public Vector getNetForce()
	{
		Vector Fnet = new Vector(0,0,0);
		for(Vector f : forces)
		{
			Fnet=Fnet.add(f);
		}
		return Fnet;
	}
	
	public void update(double dt)
	{
		velocity = velocity.add(getNetForce().scale(dt).scale(SciNumb.one().divide(mass)));
		position = position.add(velocity.scale(dt));
	}
	
	/*public Vector updateCOM()
	{
		Vector COM= new Vector(0,0,0);
		for(int i=0; i<partsParts.size(); i++)
		{
			COM = COM.add(partsParts.get(i).scale(partsParts.get(i).mass.scale(SciNumb.one().divide(mass))));
		}
		if(objectParts!=null)
			for(int i=0; i<objectParts.size(); i++)
			{
				COM = COM.add(objectParts.get(i).position.scale(objectParts.get(i).mass/mass));
			}
		return COM;
	}*/
	
	public void collide(PhysicsObject otherObject)
	{
		
	}
	/*
	 * centerOfRotation  is usually the cener of mass, unless the rotating object is attached to another part
	 * axisOfRotation is the 
	 */
	//TODO use some fancy matrix operations to make finding the momentOfInteria simpler
	public Vector momentOfInertia(Vector centerOfRotation,Vector axisOfRotation)
	{
		//Vector returned = angularVelocity.scale(point.getDistanceTo(centerOfMass)).add(velocity);
		return null;
	}
	public Vector Fg(PhysicsObject other)
	{
		Vector relPos = position.add(other.position.scale(-1));
		Vector returned = relPos.hat().scale(new SciNumb("-6.67e-11").multiply(other.mass).multiply(mass).divide(relPos.mag().pow(2)));
		return returned;
	}
	
	public PhysicsObject(double _x, double _y) 
	{
		position = new Vector(_x, _y);
	}

	public PhysicsObject(double _x, double _y, double _z) 
	{
		position = new Vector(_x, _y, _z);
	}
	
	/**
	 * assumes orbited object is not moving / final answers will be with respect to orbitedObject's velocity, and assume that orbitedObject is at 0,0,0
	 */
	public void orbit(int time, PhysicsObject orbitedObject)
	{
		double timeStep = (double)(time)/100;
		double t = 0;
		while(t<time)
		{
			PhysicsObject old = this;
			SciNumb e1 = energyCheck(orbitedObject);
			velocity = velocity.add(Fg(orbitedObject).scale(SciNumb.one().divide(mass).multiply(new SciNumb(timeStep,0).normalize())));
			position = position.add(velocity.scale(timeStep));
			SciNumb e2 = energyCheck(orbitedObject);
			double percentError = (e1.subtract(e2).abs()).divide(e1).toDouble()*100;
			System.out.println("t: "+t+"\ne1 "+e1+"\ne2: "+e2+"\n%err"+percentError);
			if(percentError>10)
			{
				position = old.position;
				velocity = old.velocity;
				timeStep = timeStep*(100-percentError)/100;
			}
			else
			{
				t+=timeStep;
			}
		}
	}
	
	public SciNumb energyCheck(PhysicsObject orbitedObject)
	{
		Vector relPos = position.add(orbitedObject.position.scale(-1.0));
		SciNumb PE = new SciNumb("-6.67e-11").multiply(orbitedObject.mass).multiply(mass).divide(relPos.mag());
		SciNumb KE = new SciNumb("5e-1").multiply(velocity.mag().pow(2).multiply(mass));
		return (KE.add(PE));
		
	}
	/*
	 * @Override
	public Orbited[] getInfluences() 
	{
		Orbited[] returned = new Orbited[2];
		int j=0;
		for(int i=0; j<2; i++)
		{
			if(planets.get(i)!=this)
			{
				returned[j]=planets.get(i);
				j++;
			}
		}
		for(int i=0; i<planets.size(); i++)
		{
			if(planets.get(i)!=this)
			{
				Celestial p = planets.get(i)
				if(getForceFrom(p).mag() > getForceFrom(orbited).mag())
				{
					orbited = p;
				}
			}
		}
		return orbited;
	}
	 */
}
