package Thought;

import NotDefault.Body;
import NotDefault.Forcer;
import NotDefault.Mixture;
import NotDefault.PhysicsObject;
import NotDefault.Vector;

public class BasicTask {

	/*
	 * I sorta want this class to be like the pre-programmed ideas, like movement and death and other ideas that will
	 * be connected to thoughts.
	 * 
	 */
	BasicTaskType type;
	Object[] o;
		/*
		 * 0: move		(change the relative position vector)
		 * 		only applies to like moving on some surface
		 * 1: push		(exerting a force on an object)		
		 * 2: grab		(object enters your bag moves with you)
		 * 3: digest	(redistributes energy from consumed items to smaller packets)-v
		 * 4: breathe	(allows gas to permeate into the body) --------------------------------> both of these do not include the muscles required to do this.
		 * 5: extend	(begin to consider an object usable by yourself)
		 * 6: wait		(wait until some condition is met)
		 */
	
	public BasicTask(BasicTaskType type) 
	{
		this.type = type;
	}
	public void assignObjects(Object[] objects)
	{
		o = new Object[objects.length];
		for(int i=0; i<objects.length; i++)
		{
			if(objects[i]!=null)
			{
				o[i] = objects[i];
			}
		}
	}
	public boolean executeTask()
	{
		switch (type)
		{
			case move://o = {PhysicsObject, Vector}
				((PhysicsObject) o[0]).position = ((PhysicsObject) o[0]).position.add((Vector) o[1]);
				break;
			case push://o = {Forcer forcer, Vector force, PhysicsObject objectForced}
				((Forcer) o[0]).exertForce((Vector) o[1], (PhysicsObject) o[2]);
				break;
			case grab://o = {Conciousness c, Posessable p}
				((Conciousness) o[0]).addTool((Item) o[1]);
				break;
			case digest://o = {Body b, Posessable p}
				((Body) o[0]).stomach.addMixture((Mixture) o[1]);
				break;
			case breathe://o = {Body b, Mixture m}
				((Body) o[0]).lungs.addMixture((Mixture) o[1]);
				break;
			case extend:
				
				break;
			case wait:
				
				break;
		}
		return false;
	}

}
