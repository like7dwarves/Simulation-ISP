package Orbital;

import java.util.ArrayList;
import java.util.List;

import NotDefault.SciNumb;
import NotDefault.Vector;

public class OrbitSystem extends Celestial 
{
	List<Celestial> bodies;
	
	public OrbitSystem(Vector position, Vector velocity, int mass) 
	{
		super(position, velocity, mass);
		bodies = new ArrayList<Celestial>();
	}
	public OrbitSystem(Vector position, Vector velocity, SciNumb mass) 
	{
		super(position, velocity, mass);
		bodies = new ArrayList<Celestial>();
	}
	public OrbitSystem()
	{
		super(new Vector(0,0,0), new Vector(0,0,0), 0);
		bodies = new ArrayList<Celestial>();
	}
	
	public void addBody(Celestial p)
	{
		bodies.add(p);
	}
	
	public void addBodies(List<Celestial> p)
	{
		bodies.addAll(p);
	}
	
	public SciNumb getInternalE()
	{
		SciNumb PE = new SciNumb(0,0);
		SciNumb KE = new SciNumb(0,0);
		for(int i=0; i<bodies.size(); i++)
		{
			for(int j=i+1; j<bodies.size();j++)
			{
				Vector relPos= bodies.get(i).getPos().add(bodies.get(j).getPos().scale(-1.0));
				PE=PE.add(bodies.get(i).getPotentialEnergy(bodies.get(j)));
			}
			KE=KE.add(bodies.get(i).getMass());
		}
		return KE.add(PE);
	}
	//(1.0789550310751332e10,-1.5373278779983686e11,0.0e0)
	@Override
	public void update(int time, Orbited[] influences)
	{
		int dt = 36000;
		int successCounter=0;
		double oldtheta = 0.0;
		for(int t=0; t<time; t+=dt)
		{
			List<Celestial> copy = getBodiesCopy();
			SciNumb Ei = getInternalE();
			for(int i=0; i<bodies.size(); i++)
			{
				bodies.get(i).update(dt, bodies.toArray(new Orbited[0]));
			}
			if((getInternalE().subtract(Ei).abs().divide(Ei).toDouble())>.01)
			{
				System.out.println("hey i failed");
				successCounter=0;
				dt=dt/2;
				bodies = copy;
			}
			else
			{
				successCounter++;
				if(successCounter==10)
				{
					successCounter=0;
					//dt=dt*2;
				}
				System.out.println(bodies.get(1).getPos());
			}
			System.out.println(Math.atan(bodies.get(1).getPos().x[1].divide(bodies.get(1).getPos().x[0]).toDouble())-oldtheta);
			oldtheta = (Math.atan(bodies.get(1).getPos().x[1].divide(bodies.get(1).getPos().x[0]).toDouble()));
			System.out.println(oldtheta+"\n");
		}
		super.update(time, influences);
	}
	public List<Celestial> getBodies()
	{
		return bodies;
	}
	public List<Celestial> getBodiesCopy()
	{
		List<Celestial> returned = new ArrayList<Celestial>();
		for(Celestial p : bodies)
		{
			Celestial nP;
			if(p instanceof OrbitSystem)
			{
				nP = new OrbitSystem(p.getPos(),p.getVel(),p.getMass());
				((OrbitSystem) nP).addBodies(((OrbitSystem) p).getBodiesCopy());
			}
			else
			{
				nP = new Planet(p.getPos().toNew(),p.getVel().toNew(),p.getMass().toNew());
			}
			returned.add(nP);
		}
		return returned;
	}
}
