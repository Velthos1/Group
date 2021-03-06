/**
 * Dalthow
 *
 * 
 * @Author Trevi Awater 
 * @License Creative Commons 4
 * 
 **/

package Trevi.Group.Handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Trevi.Group.Common.Main;

public class KeyInput extends KeyAdapter
{
	// Declaration
	
	Main Main;
	
	public KeyInput(Main Main)
	{
		this.Main = Main;
	}
	
	
	// Calls the method in the main class when a key gets pressed
	
	public void keyPressed(KeyEvent Par1)
	{
		try 
		{
			Main.keyPressed(Par1);
		} 
		
		catch (Exception Stacktrace)
		{
			Stacktrace.printStackTrace();
		} 
	}
	
	
	// Calls the method in the main class when a key gets released
	
	public void keyReleased(KeyEvent Par1)
	{
		Main.keyReleased(Par1);
	}
}
