/**
 * Dalthow
 *
 * 
 * @Author Trevi Awater 
 * @License Creative Commons 4
 * 
 **/

package Trevi.Group.Object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Trevi.Group.Common.Main;
import Trevi.Group.Handler.GlobalPosition;
import Trevi.Group.Handler.SpriteGrabber;

public class Item extends GlobalPosition
{
	// Declaration
	
	private BufferedImage Icon;
	private String Name;
	
	public Item(int Row, int Col, int xPos, int yPos, Main Main, String Name) 
	{
		super(xPos, yPos);
		
		this.Name = Name;
		
		SpriteGrabber Sprite = new SpriteGrabber(Main.getItemSpriteSheet());
		
		Icon = Sprite.grabItemImage(Row, Col, 32, 32);
	}
	
	
	// Everything in the game that updates
	
	public void tick() throws Exception
	{
		
	}

	
	// Creates a rectangle around used to player to measure collisions with other objects
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)Main.Character.getX() + xPos, (int)Main.Character.getY() + yPos, 32, 32);
	}
	
	
	// Everything in the game that renders
	
	public void render(Graphics Graphics)
	{
		Graphics.drawImage(Icon, (int)Main.Character.getX() + xPos, (int)Main.Character.getY() + yPos, 32, 32, null);
		
		if(Main.Info == true)
		{
			Graphics.drawRect((int)getBounds().getX(), (int)getBounds().getY(), (int)getBounds().getWidth(), (int)getBounds().getHeight());
		}
	}
	
	
	// Getters
	
	public int getX()
	{
		return xPos;
	}
	
	public int getY()
	{
		return yPos;
	}
	
	public String getName()
	{
		return Name;
	}
	
	
	// Setters 
	
	public void setX(int xPos)
	{
		this.xPos = xPos;
	}
	
	public void setY(int yPos)
	{
		this.yPos = yPos;
	}
}