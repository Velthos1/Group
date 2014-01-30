/**
 * Dalthow
 *
 * 
 * @Author Trevi Awater 
 * @License Creative Commons 4
 * 
 **/

package Trevi.Dalthow.Handler;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class SoundLoader
{
	// Declaration
	
	public String Name;
	public AudioClip Sound;
	
	public static SoundLoader staticSound = new SoundLoader();
	
	private SoundLoader()
	{
		
	}
	
	public SoundLoader(String Name, URL Address) 
	{
		this.Name = Name;
		
		try
		{
			Sound = Applet.newAudioClip(Address);
		}
		
		catch(Exception Stacktrace)
		{
			Stacktrace.printStackTrace();
		}
	}
	
	
	// Plays a sound
	
	public void play()
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				if(Sound != null)
				{
					Sound.play();
				}
			}
		}).start();
	}
	
	
	// Loops a sound
	
	public void loop()
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				if(Sound != null)
				{
					Sound.loop();
				}
			}
		}).start();
	}
	
	
	// Stops a sound
	
	public void stop()
	{
		if(Sound != null)
		{
			Sound.stop();
		}
	}
	
	
	// Selects which file you want to play
	
	public static URL getUrl(String Par1)
	{
		return staticSound.getClass().getResource("/Dalthow/Resources/Sound/" + Par1);
	}
}
