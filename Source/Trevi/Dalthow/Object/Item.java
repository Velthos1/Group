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
import Trevi.Dalthow.Handler.GlobalPosition;
import Trevi.Dalthow.Handler.SpriteGrabber;

public class Item extends GlobalPosition
{
	private BufferedImage Icon;
	
	public Item(int xPos, int yPos, int Row, int Col, Main Main) 
	{
		super(xPos, yPos);
		
		SpriteGrabber Sprite = new SpriteGrabber(Main.getItemSpriteSheet());
		
		Icon = Sprite.grabItemImage(Row, Col, 32, 32);
	}
	
	public void tick() throws Exception
	{
		
	}
	
	public void render(Graphics Graphics)
	{
		Graphics.drawImage(Icon, (int)Main.Character.getX() + xPos, (int)Main.Character.getY() + yPos, 32, 32, null);
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