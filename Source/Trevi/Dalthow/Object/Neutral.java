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

public class Neutral
{
	// Declaration
	
	private BufferedImage Neutral;
	
	public Neutral(double xPos, double yPos, String Name, Main Main, int Col, int Row)
	{
		SpriteGrabber Sprite = new SpriteGrabber(Main.getPlayerSpriteSheet());
		
		Neutral = Sprite.grabPlayerImage(Col, Row, 32, 32);
	}

	
	// Everything in the game that renders
	
	public void render(Graphics Graphics, int Par1, int Par2)
	{
		Graphics.drawImage(Neutral, Par1, Par2, 32, 32 , null);
	}
	

	
	// Everything in the game that updates
	
	public void tick() throws Exception
	{
		
	}
}
