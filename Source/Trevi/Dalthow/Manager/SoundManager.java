/**
 * Dalthow
 *
 * 
 * @Author Trevi Awater 
 * @License Creative Commons 4
 * 
 **/

package Trevi.Dalthow.Manager;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundManager
{
	// Can be called from anywhere to play a sound
	
	public static void playSound(String Par1) 
	{
	    try 
	    {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/Resources/Sound/" + Par1).getAbsoluteFile());
	        Clip Par2 = AudioSystem.getClip();
	       
	        Par2.open(audioInputStream);
	        Par2.start();
	    } 
	    
	    catch(Exception Stacktrace) 
	    {
	        Stacktrace.printStackTrace();
	    }
	}
}
