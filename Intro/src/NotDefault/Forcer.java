package NotDefault;

public interface Forcer 
{
	public default void exertForce(Vector f, PhysicsObject otherObject)
	{}
	
	public default void reactiveForce(Vector f, PhysicsObject otherObject)
	{}
	
	public default Vector getNetForce()
	{
		return null;
	}
	
	public default void update(double dt)
	{}
	
}
