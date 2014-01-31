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
import Trevi.Dalthow.Common.Reference;
import Trevi.Dalthow.Handler.SpriteGrabber;
import Trevi.Dalthow.Manager.ProgressManager;

public class Player 
{
	// Declaration
	
	private double xPos;
	private double yPos;
	
	private double Health;
	
	private double xVel;
	private double yVel;
	
	private BufferedImage Player;
	
	public Player(double xPos, double yPos, double Health, Main Main)
	{
		ProgressManager.loadProgress();
		
		this.xPos = ProgressManager.xPos;
		this.yPos = ProgressManager.yPos;
		
		this.Health = Health;
		
		SpriteGrabber Sprite = new SpriteGrabber(Main.getPlayerSpriteSheet());
		
		Player = Sprite.grabPlayerImage(1, 1, 32, 64);
	}
	
	
	// Everything in the game that updates
	
	public void tick()
	{
		xPos += xVel;
		yPos += yVel;
	}
	
	
	// Everything in the game that renders
	
	public void render(Graphics Graphics, int Par1, int Par2)
	{
		Graphics.drawImage(Player, Reference.Width - 16, Reference.Height - 32, Par1 * Reference.Scale, Par2 * Reference.Scale, null);
	}
	
	
	// Getters
	
	public double getX()
	{
		return xPos;
	}
	
	public double getY()
	{
		return yPos;
	}
	
	
	// Setters 
	
	public void setX(double xPos)
	{
		this.xPos = xPos;
	}
	
	public void setY(double yPos)
	{
		this.yPos = yPos;
	}
	
	public void setVelX(double xVel)
	{
		this.xVel = xVel;
	}
	
	public void setVelY(double yVel)
	{
		this.yVel = yVel;
	}
	
	public void setPosX(double xPos)
	{
		this.xPos = xPos;
	}
	
	public void setPosY(double yPos)
	{
		this.yPos = yPos;
	}
}
