package NotDefault;

public class Body extends PhysicsObject {

	public Mixture stomach;
	public Mixture lungs;
	
	/*
	 * I don't really care which is which, but we can only get energy from reactions in our bodies, 
	 * and each organ is responsible for getting one of those two necessary chemicals.
	 */
	
	public Body(SciNumb _x, SciNumb _y, SciNumb _z, SciNumb _mass) 
	{
		super(_x, _y, _z, _mass);
		// TODO Auto-generated constructor stub
	}
	
	

}
