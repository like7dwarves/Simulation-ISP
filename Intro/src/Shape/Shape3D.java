package Shape;

public class Shape3D
{
	int type;
	public Shape3D(int _type) 
	{
		type = _type;
	}
	/*
	 * 0 = combo
	 * 1 = 2d planar addition combo
	 * 2 = sphere
	 * 3 = prism (or prism w/ one side ending at a point
	 */
	public double volume()
	{
		return 0;
	}
	public double surfaceArea()
	{
		return 0;
	}
	public double narrowCrossSection()
	{
		return 0;
	}
}
