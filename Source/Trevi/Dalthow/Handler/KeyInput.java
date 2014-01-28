/**
 * Dalthow
 *
 * 
 * @Author Trevi Awater 
 * @License Creative Commons 4
 * 
 **/

package Trevi.Dalthow.Handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Trevi.Dalthow.Common.Main;

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
		Main.keyPressed(Par1);
	}
	
	
	// Calls the method in the main class when a key gets released
	
	public void keyReleased(KeyEvent Par1)
	{
		Main.keyReleased(Par1);
	}
}
