package Thought;

import NotDefault.*;

public class Item {

	String itemType; //this is gonna get revamped, but this will do for now
	Vector location;
	
	public Item(String item) 
	{
		itemType = item;
	}
	public void setLocation(Vector v)
	{
		location = new Vector(v.x[0],v.x[1],v.x[2]);
	}
	
}
