/**
 * Dalthow
 *
 * 
 * @Author Trevi Awater 
 * @License Creative Commons 4
 * 
 **/

package Trevi.Group.Handler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Trevi.Group.Common.Main;

public class MouseInput extends MouseAdapter
{
	// Declaration
	
	Main Main;
	
	public MouseInput(Main Main)
	{
		this.Main = Main;
	}
	

	// Calls the method in the main class when the mouse gets clicked
	
	public void  mouseClicked(MouseEvent Par1)
	{
	     Main.mouseClicked(Par1);
	}
}
