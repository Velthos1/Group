/**
 * Dalthow
 *
 * 
 * @Author Trevi Awater 
 * @License Creative Commons 4
 * 
 **/

package Trevi.Group.Manager;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundManager
{
	// Declaration
	
	private static Clip musicClip;
	private static Clip soundClip;
    
	private static double musicVolume = 75.0;
	private static double soundVolume = 85.0;
	
	
	// Can be called from anywhere to play a sound
	
	public static void playSound(String Par1) 
	{
	    try 
	    {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/Resources/Sound/" + Par1).getAbsoluteFile());
	       
	        soundClip = AudioSystem.getClip();
	        soundClip.open(audioInputStream);
	        
	        FloatControl Par2 = (FloatControl) soundClip.getControl(FloatControl.Type.MASTER_GAIN);
      	  
	        Par2.setValue((float) - (80 - ((float)soundVolume) / 1.25));
	        
	        soundClip.start();
	    } 
	    
	    catch(Exception Stacktrace) 
	    {
	        Stacktrace.printStackTrace();
	    }
	}
	
	
	// Can be called from anywhere to play music
	
	public static void playMusic(String Par1) 
	{
	    try 
	    {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/Resources/Music/" + Par1).getAbsoluteFile());
	    
	        musicClip = AudioSystem.getClip();
	        musicClip.open(audioInputStream);
	        
	        FloatControl Par2 = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
	        	  
	        Par2.setValue((float) - (80 - ((float)musicVolume) / 1.25));
	        
	        musicClip.start();
	    } 
	    
	    catch(Exception Stacktrace) 
	    {
	        Stacktrace.printStackTrace();
	    }
	}
	
	
	// Can be called from anywhere to stop the music
	
	public static void stopMusic()
	{
		musicClip.stop();
	}
	
	
	// Can be called from anywhere to stop all the sounds
	
	public static void stopSound()
	{
		soundClip.stop();
	}
}
