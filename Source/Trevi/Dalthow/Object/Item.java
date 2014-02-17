/**
 * Dalthow
 *
 * 
 * @Author Trevi Awater 
 * @License Creative Commons 4
 * 
 **/

package Trevi.Dalthow.Object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Trevi.Dalthow.Common.Main;
import Trevi.Dalthow.Handler.SpriteGrabber;

public class Item
{
	// Declaration
	
	private BufferedImage Item;
	
	public Item(double xPos, double yPos, int Col, int Row, String Name, Main Main)
	{
		SpriteGrabber Sprite = new SpriteGrabber(Main.getPlayerSpriteSheet());
		
		Item = Sprite.grabPlayerImage(Col, Row, 32, 32);
	}

	
	// Everything in the game that renders
	
	public void render(Graphics Graphics)
	{
		
	}
	

	
	// Everything in the game that updates
	
	public void tick() throws Exception
	{
		
	}
}
