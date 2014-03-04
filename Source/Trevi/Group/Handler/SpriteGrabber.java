/**
 * Dalthow
 *
 * 
 * @Author Trevi Awater 
 * @License Creative Commons 4
 * 
 **/

package Trevi.Group.Handler;

import java.awt.image.BufferedImage;

public class SpriteGrabber 
{
	// Declaration
	
	private BufferedImage Image;
	
	public SpriteGrabber(BufferedImage Image)
	{
		this.Image = Image;
	}

	
	// Grabs the image of the sprite sheet based on the row and columns, this one is used for the items.
	
	public BufferedImage grabItemImage(int Col, int Row, int Width, int Height)
	{
		BufferedImage Graphic = Image.getSubimage((Col * 32) - 32, (Row * 32) - 32, Width, Height);
		
		return Graphic;
	}
	
	
	// Grabs the image of the sprite sheet based on the row and columns, this one is used for the blocks.
	
	public BufferedImage grabBlockImage(int Col, int Row, int Width, int Height)
	{
		BufferedImage Graphic = Image.getSubimage((Col * 32) - 32, (Row * 32) - 32, Width, Height);
		
		return Graphic;
	}
	
	
	// Grabs the image of the sprite sheet based on the row and columns, this one is used for the player.
	
	public BufferedImage grabPlayerImage(int Col, int Row, int Width, int Height)
	{
		BufferedImage Graphic = Image.getSubimage((Col * 32) - 32, (Row * 32) - 32 , Width, Height);
		
		return Graphic;
	}
}
