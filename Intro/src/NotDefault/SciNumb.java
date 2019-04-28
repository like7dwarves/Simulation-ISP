package NotDefault;
public class SciNumb
{
   private double dec;
   private int pow;
   
   public SciNumb(String s)
   {
      if(s.contains("e"))
      {
         String[] parts = s.split("e");
         dec = Double.parseDouble(parts[0]);
         pow = Integer.parseInt(parts[1]);
         normalize();
      }
      else
      {
         dec = Double.parseDouble(s);
         pow = 0;
         normalize();
      }
   }
   public SciNumb(double d, int p)
   {
      dec = d;
      pow = p;
   }
   public SciNumb normalize()
   {
	  if(dec==0.0)
		  return this;
      while (Math.abs(dec)>10)
      {
         pow++;
         dec=dec/10;
      }
      while (Math.abs(dec)<1)
      {
         pow--;
         dec=dec*10;
      }
      return this;
   }
   public SciNumb add(SciNumb s)
   {
      int difDec = s.pow-pow;
      SciNumb p =  new SciNumb(Math.pow(10,difDec)*s.dec+dec,pow);
      p.normalize();
      return p;
   }
   public SciNumb subtract(SciNumb s)
   {
      SciNumb p = new SciNumb(-s.dec,s.pow);
      return p.add(this);
   }
   public SciNumb multiply(SciNumb s)
   {
      return (new SciNumb(dec*s.dec, pow+s.pow)).normalize();
   }
   public SciNumb divide(SciNumb s)
   {
      return (new SciNumb(dec/s.dec, pow-s.pow)).normalize();
   }
   public SciNumb pow(int power)
   {
      return (new SciNumb(Math.pow(dec,power), power*pow)).normalize();
   }
   public SciNumb sqrt()
   {
      if(dec==0)
      {
         return new SciNumb("0e0");
      }
      if(pow%2==0)
      {
         return new SciNumb(dec/Math.abs(dec)*Math.sqrt(Math.abs(dec)),pow/2).normalize();
      }
      else
      {
         int extraPow = pow%2;
         return new SciNumb(dec/Math.abs(dec)*Math.sqrt(Math.abs(dec)*Math.pow(10,extraPow)),(pow-extraPow)/2).normalize();
      }
   }
   public String toString()
   {
      return dec+"e"+pow;
   }
   //this could break. be careful.
   public double toDouble()
   {
	   return dec*Math.pow(10, pow);
   }
   public SciNumb abs()
   {
	   return new SciNumb(Math.abs(dec),pow);
   }
   public int toInt()
   {
	   return (int) toDouble();
   }

   public boolean equals(SciNumb n)
   {
	   return n.dec==dec&&n.pow==pow;
   }
   
   public boolean isZero()
   {
	   return dec==0&&pow==0;
   }
   public static SciNumb one()
   {
	   return new SciNumb(1,0);
   }
   public SciNumb toNew()
   {
	   return new SciNumb(dec, pow);
   }
}