package Thought;

import NotDefault.PhysicsObject;
import NotDefault.Vector;

public class Conciousness {

	public Item[] toolsAtDisposal;
	
	public PhysicsObject body; //eventually will replace toolsAtDisposal
	
	public Conciousness(Item[] tools) 
	{
		toolsAtDisposal = new Item[0];
	}
	
	public void addTool(Item i)
	{
		Item[] newArray = new Item[toolsAtDisposal.length+1];
		for(int j=0; j<toolsAtDisposal.length; j++)
		{
			newArray[j] = toolsAtDisposal[j];
		}
		newArray[toolsAtDisposal.length]=i;
		toolsAtDisposal = newArray;
	}
}
