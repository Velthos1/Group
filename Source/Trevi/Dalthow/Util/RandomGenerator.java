/**
 * Dalthow
 *
 * 
 * @Author Trevi Awater 
 * @License Creative Commons 4
 * 
 **/

package Trevi.Dalthow.Util;

import java.util.Random;

public class RandomGenerator 
{
	// Generates a random positive or negative number
	
	public static int randomNumber(int Min, int Max)
	{
		Random Generator = new Random();
		
		int Value = Generator.nextInt(Max - Min + 1) + Min;
		
		return Value;
	}
}
