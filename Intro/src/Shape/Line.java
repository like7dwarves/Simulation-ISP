package Shape;
import java.awt.Graphics2D;

import NotDefault.Vector;


public class Line
{
	Vector p1;
	Vector p2;
	
	public Line(double x1, double y1, double x2, double y2)
	{
		p1 = new Vector(x1,y1); 
		p2 = new Vector(x2,y2);
	}
	public Line(Vector _p1, Vector _p2){
		p1 = _p1;
		p2 = _p2;
	}
	public Line draw(Graphics2D g2d, Vector ref)
	{
		try
		{
			g2d.drawLine((int) Math.round(p1.x[0].toDouble()+ref.x[0].toDouble()),(int) Math.round(p1.x[1].toDouble()+ref.x[1].toDouble()), (int) Math.round(p2.x[0].toDouble()+ref.x[0].toDouble()), (int) Math.round(p2.x[1].toDouble()+ref.x[1].toDouble()));
		}
		catch(NullPointerException e){
		}
		return this;
	}
	public double getSlope()
	{
		double run = p1.x[0].toDouble()-p2.x[0].toDouble();
		double rise = p1.x[1].toDouble()-p2.x[1].toDouble();
		return rise/run;
	}
	public double yIntercept()
	{
		double returned=(double) (-p1.x[0].toDouble()*this.getSlope()+p1.x[1].toDouble());
		return returned;
	}
	public double getValue(double x)
	{
		double returned = (double) (x*getSlope()+yIntercept());
		return returned; 
	}
	public Vector getIntercept(Line otherLine,Line lastLine)
	{
		if(getSlope()==otherLine.getSlope())
		{
			if(p1.x == otherLine.p1.x)
			{
				return getIntercept(lastLine);//still works, just in a wierd way
			}
		}
		
		if(Math.abs(getSlope())>10000000)	//this is a vertical line
		{
			return new Vector (this.p1.x[0].toDouble(), otherLine.getValue(this.p1.x[0].toDouble()));
		}
		
		if(Math.abs(getSlope())==0)			// horizontal line
		{
			return new Vector(otherLine.p1.x[0].toDouble(), this.getValue(otherLine.p1.x[0].toDouble()));
		}
		double differenceOfM = otherLine.getSlope()-this.getSlope();
		double differenceOfB =this.yIntercept()-otherLine.yIntercept();
		double divided = (differenceOfB/differenceOfM);
		return new Vector(divided, this.getValue(divided));
	}
	public Vector getIntercept(Line otherLine)
	{
		if(getSlope()==otherLine.getSlope()) {
			if(p1.x == otherLine.p1.x) 
				return null;
			System.out.println("will not work: getIntercept can't get an intercept if the lines have the same slope");
			return new Vector(0,0);
		}
		if(Math.abs(getSlope())>10000000){//this is a vertical line
			return new Vector (this.p1.x[0].toDouble(), otherLine.getValue(this.p1.x[0].toDouble()));
		}
		if(Math.abs(otherLine.getSlope())>10000000){//otherLine is vertical line
			return new Vector(otherLine.p1.x[0].toDouble(), this.getValue(otherLine.p1.x[0].toDouble()));
		}
		double differenceOfM = otherLine.getSlope()-this.getSlope();
		double differenceOfB =this.yIntercept()-otherLine.yIntercept();
		double divided = (differenceOfB/differenceOfM);
		return new Vector(divided, this.getValue(divided));
	}
	public Vector getMidpoint()
	{
		return new Vector((p1.x[0].toDouble()+p2.x[0].toDouble())/2, (p1.x[1].toDouble()+p2.x[1].toDouble())/2);
	}
	@Override
	public String toString()
	{
		return ("Line: ["+p1.toString()+", "+p2.toString()+"]");
	}
}