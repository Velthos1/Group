/**
 * Dalthow
 *
 * 
 * @Author Trevi Awater 
 * @License Creative Commons 4
 * 
 **/

package Trevi.Group.Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Trevi.Group.Common.Main;
import Trevi.Group.Common.Reference;
import Trevi.Group.Handler.SpriteGrabber;
import Trevi.Group.Manager.ProgressManager;
import Trevi.Group.Manager.SoundManager;

public class Player 
{
	// Declaration
	
	private double xPos;
	private double yPos;
	
	private double Health;
	private double Energy;
	private double Currency;
	
	private double xVel;
	private double yVel;
	
	private BufferedImage upPlayer, downPlayer;
	
	public boolean canMoveUp = true;
	public boolean canMoveDown = true;
	public boolean canMoveRight = true;
	public boolean canMoveLeft = true;
	
	public Player(double xPos, double yPos, double Health, Main Main)
	{
		ProgressManager.loadProgress();
		
		this.xPos = ProgressManager.xPos;
		this.yPos = ProgressManager.yPos;
		
		this.Health = ProgressManager.Health;
		this.Energy = ProgressManager.Energy;
		this.Currency = ProgressManager.Currency;
		
		SpriteGrabber Sprite = new SpriteGrabber(Main.getPlayerSpriteSheet());
		
		upPlayer = Sprite.grabPlayerImage(1, 1, 32, 64);	
		downPlayer = Sprite.grabPlayerImage(2, 1, 32, 64);	
	}
	
	
	// Everything in the game that updates
	
	public void tick() throws Exception
	{
		xPos += xVel;
		yPos += yVel;
		
		if(Health <= 0)
		{
			respawn();
		}
	}
	

	// Creates a rectangle around used to player to measure collisions with other objects
	
	public Rectangle getBounds(int Par1, int Par2)
	{
		return new Rectangle(Par1 / 2 - 32, Par2 / 2 - 64, 32 * Reference.Scale, 64 * Reference.Scale);
	}
	
	public Rectangle getBoundsBottom(int Par1, int Par2)
	{
		return new Rectangle(Par1 / 2 - 32 + (32 / 2 * Reference.Scale) - ((32 * Reference.Scale / 2) / 2), Par2 / 2 - 48 + (64 / 2 * Reference.Scale), 32 / 2 * Reference.Scale, 48 * Reference.Scale / 2);
	}
	
	public Rectangle getBoundsTop(int Par1, int Par2)
	{
		return new Rectangle(Par1 / 2 - 32 + (32 / 2 * Reference.Scale) - ((32 * Reference.Scale / 2) / 2), Par2 / 2 - 24, 32 / 2 * Reference.Scale, 32 * Reference.Scale / 2);
	}
	
	public Rectangle getBoundsRight(int Par1, int Par2)
	{
		return new Rectangle(Par1 / 2 - 32 + 31 * Reference.Scale - 5 * Reference.Scale, Par2 / 2 - 24 + 5 * Reference.Scale, 5 * Reference.Scale, 42 * Reference.Scale - 10 * Reference.Scale);
	}
	
	public Rectangle getBoundsLeft(int Par1, int Par2)
	{
		return new Rectangle(Par1 / 2 - 31, Par2 / 2 - 24 + 5 * Reference.Scale, 5 * Reference.Scale, 42 * Reference.Scale - 10 * Reference.Scale);
	}
	
	
	// The rectangle used to see if there are enemies in range of the player
	
	public Rectangle getAttackBoundsBottom(int Par1, int Par2)
	{
		return new Rectangle(Par1 / 2 - 40 + (32 / 2 * Reference.Scale) - ((32 * Reference.Scale / 2) / 2), Par2 / 2 + (64 / 2 * Reference.Scale), 48 / 2 * Reference.Scale, 20 * Reference.Scale);
	}
	
	public Rectangle getAttackBoundsTop(int Par1, int Par2)
	{
		return new Rectangle(Par1 / 2 - 40 + (32 / 2 * Reference.Scale) - ((32 * Reference.Scale / 2) / 2), Par2 / 2 - (52 * Reference.Scale), 48 / 2 * Reference.Scale, 20 * Reference.Scale);
	}
	
	public Rectangle getAttackBoundsLeft(int Par1, int Par2)
	{
		return new Rectangle(Par1 / 2 - 36 * Reference.Scale, Par2 / 2 - 64 + 5 * Reference.Scale, 20 * Reference.Scale, 64 * Reference.Scale - 10 * Reference.Scale);
	}
	
	public Rectangle getAttackBoundsRight(int Par1, int Par2)
	{
		return new Rectangle(Par1 / 2 + 21 * Reference.Scale - 5 * Reference.Scale, Par2 / 2 - 64 + 5 * Reference.Scale, 20 * Reference.Scale, 64 * Reference.Scale - 10 * Reference.Scale);
	}
	
	
	// Everything in the game that renders
	
	public void render(Graphics Graphics, int Par1, int Par2)
	{
		if(yVel < 0.1)
		{
			Graphics.drawImage(upPlayer, Par1 / 2 - 32, Par2 / 2 - 64, 32 * Reference.Scale, 64 * Reference.Scale, null);
		}
		
		else if(yVel > 0.1)
		{
			Graphics.drawImage(downPlayer, Par1 / 2 - 32, Par2 / 2 - 64, 32 * Reference.Scale, 64 * Reference.Scale, null);
		}
		
		if(Main.Info == true)
		{
			Graphics2D Par3 = (Graphics2D) Graphics;
			
			Par3.setColor(Color.red);
			Par3.draw(getBoundsTop(Par1, Par2));
			
			Par3.setColor(Color.blue);
			Par3.draw(getBoundsBottom(Par1, Par2));
			
			Par3.setColor(Color.green);
			Par3.draw(getBoundsLeft(Par1, Par2));
			
			Par3.setColor(Color.yellow);
			Par3.draw(getBoundsRight(Par1, Par2));
			
			Par3.setColor(Color.magenta);
			Par3.draw(getAttackBoundsTop(Par1, Par2));
			Par3.draw(getAttackBoundsBottom(Par1, Par2));
			Par3.draw(getAttackBoundsLeft(Par1, Par2));
			Par3.draw(getAttackBoundsRight(Par1, Par2));
			
			Par3.setColor(Color.darkGray);
			Par3.draw(getBounds(Par1, Par2));
		}
	}
	
	
	// Sets the health back to the original amount when the player dies
	
	public void respawn() throws Exception
	{
		Thread.sleep(1500);
		
		xPos = 0;
		yPos = 0;
		
		Health = 10.0;
		Energy = 100.0;
	}
	
	
	// Checks if the player is not dead
	
	public boolean isDead()
	{
		if(Health < 0)
		{
			return true;
		}
		
		else return false;
	}
	
	
	// Makes the player take a specific amount of damage
	
	public void dealDamage(double Amount)
	{
		setHealth(getHealth() - Amount);
		
		SoundManager.playSound("Hurt.wav");
	}
	
	
	// Makes the player attack
	public void attack() 
	{
	
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
	
	public double getVelX()
	{
		return xVel;
	}
	
	public double getVelY()
	{
		return yVel;
	}
	
	public double getHealth()
	{
		return Health;
	}
	
	public double getEnergy()
	{
		return Energy;
	}
	
	public double getCurrency()
	{
		return Currency;
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
	
	public void setHealth(double Health)
	{
		this.Health = Health;
	}
	
	public void setEnergy(double Energy)
	{
		this.Energy = Energy;
	}
	
	public void setCurrency(double Currency)
	{
		this.Currency = Currency;
	}
}
