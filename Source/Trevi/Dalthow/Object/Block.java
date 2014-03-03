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
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Trevi.Dalthow.Common.Main;
import Trevi.Dalthow.Common.Reference;
import Trevi.Dalthow.Handler.GlobalPosition;
import Trevi.Dalthow.Handler.SpriteGrabber;

public class Block extends GlobalPosition
{
	// Declaration
	
	private BufferedImage Icon;
	private String Name;
	
	private int xPos;
	private int yPos;
	
	public Block(int Row, int Col, int xPos, int yPos, Main Main, String Name) 
	{
		super(xPos, yPos);
		
		this.Name = Name;
		
		this.xPos = xPos;
		this.yPos = yPos;
		
		SpriteGrabber Sprite = new SpriteGrabber(Main.getBlockSpriteSheet());
		
		Icon = Sprite.grabBlockImage(Row, Col, 32, 32);
	}
	
	
	// Everything in the game that updates
	
	public void tick() throws Exception
	{
		if(Main.Character.getBoundsBottom(Main.Frame.getWidth(), Main.Frame.getHeight()).intersects(getBounds()))
		{
			Main.Character.setVelY(0);
			Main.Character.canMoveDown = false;
		}
		
		else if(Main.Character.getBoundsTop(Main.Frame.getWidth(), Main.Frame.getHeight()).intersects(getBounds()))
		{
			Main.Character.setVelY(0);
			Main.Character.canMoveUp = false;
		}
		
		else if(Main.Character.getBoundsRight(Main.Frame.getWidth(), Main.Frame.getHeight()).intersects(getBounds()))
		{
			Main.Character.setVelX(0);
			Main.Character.canMoveRight = false;
		}
		
		else if(Main.Character.getBoundsLeft(Main.Frame.getWidth(), Main.Frame.getHeight()).intersects(getBounds()))
		{
			Main.Character.setVelX(0);
			Main.Character.canMoveLeft = false;
		}
		
		else
		{
			Main.Character.canMoveDown = true;
			Main.Character.canMoveUp = true;
			Main.Character.canMoveRight = true;
			Main.Character.canMoveLeft = true;
		}
	}

	
	// Creates a rectangle around used to player to measure collisions with other objects
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)Main.Character.getX() + xPos, (int)Main.Character.getY() + yPos, 32* Reference.Scale, 32* Reference.Scale);
	}
	
	
	// Everything in the game that renders
	
	public void render(Graphics Graphics)
	{
		Graphics.drawImage(Icon, (int)Main.Character.getX() + xPos, (int)Main.Character.getY() + yPos, 32 * Reference.Scale, 32 * Reference.Scale, null);
		
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