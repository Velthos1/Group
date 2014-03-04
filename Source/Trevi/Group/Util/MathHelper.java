/**
 * Dalthow
 *
 * 
 * @Author Trevi Awater 
 * @License Creative Commons 4
 * 
 **/

package Trevi.Group.Util;

public class MathHelper
{
	  public static final double Phi = 1.618034D;

	  public static float approachLinear(float Var1, float Var2, float Var3)
	  {
	      return Var2 - Var1 < Var3 ? Var2 : Var1 > Var2 ? Var1 - Var3 : Var1 - Var2 < Var3 ? Var2 : Var1 + Var3;
	  }

	  public static double approachLinear(double Var1, double Var2, double Var3)
	  {
	      return Var2 - Var1 < Var3 ? Var2 : Var1 > Var2 ? Var1 - Var3 : Var1 - Var2 < Var3 ? Var2 : Var1 + Var3;
	  }

	  public static float interpolate(float Var1, float Var2, float Var3)
	  {
	      return Var1 + (Var2 - Var1) * Var3;
	  }

	  public static double interpolate(double Var1, double Var2, double Var3)
	  {
	      return Var1 + (Var2 - Var1) * Var3;
	  }

	  public static double approachExp(double Var1, double Var2, double Var3)
	  {
	      return Var1 + (Var2 - Var1) * Var3;
	  }

	  public static double approachExp(double Var1, double Var2, double Var3, double Var4)
	  {
	      double Var5 = (Var2 - Var1) * Var3;
	      
	      if (Math.abs(Var5) > Var4)
	      {
	          Var5 = Math.signum(Var5) * Var4;
	      }
	      
	      return Var1 + Var2;
	  }

	  public static double retreatExp(double Var1, double Var2, double Var3, double Var4, double Var5)
	  {
    	  double Var6 = (Math.abs(Var3 - Var1) + Var5) * Var4;
    	  
    	  if (Var6 > Math.abs(Var2 - Var1)) 
    	  {
    	      return Var2;
    	  }
    	  
    	  return Var1 + Math.signum(Var2 - Var1) * Var6;
	  }

	  public static double clamp(double Par1, double Par2, double Par3)
	  {
    	  if (Par1 > Par3) 
    	  {
    	      Par1 = Par3;
    	  }
    	  
    	  if (Par1 < Par2) 
    	  {
    	      Par1 = Par2;
    	  }
    	    
    	  return Par1;
	  }

	  public static boolean between(double Var1, double Var2, double Var3)
	  {
	      return (Var1 <= Var2) && (Var2 <= Var3);
	  }

	  public static int approachExpI(int Var1, int Var2, double Var3)
	  {
    	   int Var4 = (int)Math.round(approachExp(Var1, Var2, Var3));
    	   return Var4 == Var1 ? Var2 : Var4;
	  }

	  public static int retreatExpI(int Var1, int Var2, int Var3, double Var4, int Var5)
	  {
	      int Var6 = (int)Math.round(retreatExp(Var1, Var2, Var3, Var4, Var5));
	      return Var6 == Var1 ? Var2 : Var6;
	  }

	  public static int round(double Var1)
	  {
	      return (int)(Var1 + 0.5D);
	  }

	  public static int ceil(double Var1)
	  {
	      return (int)(Var1 + 0.9999D);
	  }

	  public static float minFloat(float Var1, float Var2)
	  {
	      return Var1 < Var2 ? Var1 : Var2;
	  }

	  public static float maxFloat(float Var1, float Var2)
	  {
	      return Var1 > Var2 ? Var1 : Var2;
	  }

	  public static int minInteger(int Var1, int Var2)
	  {
	      return Var1 < Var2 ? Var1 : Var2;
	  }

	  public static int maxInteger(int Var1, int Var2)
	  {
	      return Var1 > Var2 ? Var1 : Var2;
	  }
}
