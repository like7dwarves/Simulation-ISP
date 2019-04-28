package NotDefault;
import java.awt.Graphics2D;
import java.math.BigDecimal;

public class Vector {

	public SciNumb[] x;

	public Vector() 
	{
	}

	public Vector(double _x, double _y) 
	{
		x = new SciNumb[3];
		x[0] = new SciNumb(_x,0).normalize();
		x[1] = new SciNumb(_y,0).normalize();
		x[2] = new SciNumb(0, 0).normalize();
	}

	public Vector(double _x, double _y, double _z) 
	{
		x = new SciNumb[3];
		x[0] = new SciNumb(_x,0).normalize();
		x[1] = new SciNumb(_y,0).normalize();
		x[2] = new SciNumb(_z,0).normalize();
	}
	public Vector(SciNumb _x, SciNumb _y, SciNumb _z) 
	{
		x = new SciNumb[3];
		x[0] = _x;
		x[1] = _y;
		x[2] = _z;
	}

	public Vector draw(Graphics2D g2d, int refx, int refy) 
	{
		try 
		{
			g2d.fillOval((int)(x[0].toDouble() + refx-2), (int)(x[1].toDouble() + refy-2), 4, 4);
		} 
		catch (NullPointerException e) 
		{
			e.printStackTrace();
			System.out.println("Point Failed to Draw");
		}
		return this;
	}

	public Vector midpoint(Vector otherPoint) 
	{
		double newx = (double) ((otherPoint.x[0].toDouble() + x[0].toDouble()) / 2);
		double newy = (double) ((otherPoint.x[1].toDouble() + x[1].toDouble()) / 2);
		double newz = (double) ((otherPoint.x[2].toDouble() + x[2].toDouble()) / 2);
		return new Vector(newx, newy, newz);
	}

	public Vector xymidpoint(Vector otherPoint) 
	{
		double newx = (double) ((otherPoint.x[0].toDouble() + x[0].toDouble()) / 2);
		double newy = (double) ((otherPoint.x[0].toDouble() + x[0].toDouble()) / 2);
		return new Vector(newx, newy);
	}

	public SciNumb getDistanceTo(Vector otherPoint) 
	{
		return otherPoint.x[0].pow(2).add(otherPoint.x[1].pow(2)).add(otherPoint.x[0].pow(2)).sqrt();
	}
	public SciNumb mag()
	{
		return x[0].pow(2).add(x[1].pow(2)).add(x[2].pow(2)).sqrt();
	}
	public Vector hat()
	{
		SciNumb mag = mag();
		if(mag.isZero())
			return null;
		Vector hat = new Vector(x[0].divide(mag),x[1].divide(mag),x[2].divide(mag));
		return hat;
	}
	public Vector scale(SciNumb d)
	{
		Vector hat = new Vector(d.multiply(x[0]),d.multiply(x[1]), d.multiply(x[2]));
		return hat;
	}
	public Vector scale(double d)
	{
		Vector hat = new Vector(x[0].multiply(new SciNumb(d,0).normalize()),x[1].multiply(new SciNumb(d,0).normalize()),x[2].multiply(new SciNumb(d,0).normalize()));
		return hat;
	}
	public Vector add(Vector v)
	{
		Vector returned = new Vector(x[0].add(v.x[0]), x[1].add(v.x[1]), x[2].add(v.x[2]));
		return returned;
	}
	@Override
	public String toString() {
		return "(" + x[0] + "," + x[1] + ","+x[2]+")";
	}
	public Vector toNew()
	{
		return new Vector(x[0].toNew(),x[1].toNew(),x[2].toNew());
	}
}