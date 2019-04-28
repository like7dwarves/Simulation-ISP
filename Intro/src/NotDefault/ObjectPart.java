package NotDefault;
import Shape.Shape3D;


public class ObjectPart extends Vector 
{

	double mass;
	Material material;
	Vector orientation;
	Shape3D shape;
	
	public ObjectPart(double _x, double _y, double _z, double _mass, Material _material, Vector _orientation, Shape3D _shape) 
	{
		super(_x,_y,_z);
		mass=_mass;
		material=_material;
		orientation=_orientation;
		shape=_shape;
	}
	public ObjectPart(Vector v, double _mass, Material _material, Vector _orientation, Shape3D _shape) 
	{
		super(v.x[0],v.x[1],v.x[2]);
		mass=_mass;
		material=_material;
		orientation=_orientation;
		shape=_shape;
	}

	public ObjectPart(double _x, double _y) 
	{
		super(_x, _y);
	}
	
	public ObjectPart(double _x, double _y, double _z, double _mass) 
	{
		super(_x, _y,_z);
		mass=_mass;
	}

	public ObjectPart(double _x, double _y, double _z) 
	{
		super(_x, _y, _z);
	}
	public void setVec(Vector v)
	{
		x[0] = v.x[0];
		x[1] = v.x[1];
		x[2] = v.x[2];
	}
}
