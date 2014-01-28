/**
 * Dalthow
 *
 * 
 * @Author Trevi Awater 
 * @License Creative Commons 4
 * 
 **/

package Trevi.Dalthow.Handler;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferLoader
{
	// Declaration
	
	private BufferedImage Image;
	
	
	// Loads and buffers the selected image
	
	public BufferedImage loadImage(String Path) throws IOException
	{
		Image = ImageIO.read(getClass().getResource(Path));
		
		return Image;
	}
}
