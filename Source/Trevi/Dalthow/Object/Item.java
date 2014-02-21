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
	
	private double xPos;
	private double yPos;
	
	public Item(double xPos, double yPos, int Col, int Row, Main Main)
	{
		SpriteGrabber Sprite = new SpriteGrabber(Main.getItemSpriteSheet());
		
		Item = Sprite.grabItemImage(Col, Row, 32, 32);
		
		this.xPos = xPos;
		this.yPos = yPos;
	}

	
	// Everything in the game that renders
	
	public void render(Graphics Graphics)
	{
		Graphics.drawImage(Item, (int)xPos, (int)yPos, 32, 32, null);
	}
	

	
	// Everything in the game that updates
	
	public void tick() throws Exception
	{
		
	}
}