/**
 * Dalthow
 *
 * 
 * @Author Trevi Awater 
 * @License Creative Commons 4
 * 
 **/

package Trevi.Dalthow.Manager;

import java.util.ArrayList;

import Trevi.Dalthow.Handler.SoundLoader;

public abstract class SoundManager
{
	// Declaration
	
	public ArrayList <SoundLoader> Sounds = new ArrayList <SoundLoader>();
	
	public SoundManager()
	{
		initSounds();
	}
	
	public abstract void initSounds();
	
	
	// Adds a sound to the library
	
	public void addSound(String Name, SoundLoader Sound)
	{
		Sounds.add(Sound);
	}
	
	
	// Removes a sound to the library
	
	public void removeSound(String Name, SoundLoader Sound)
	{
		Sounds.remove(Sound);
	}
	
	
	// Plays a sound
	
	public void playSound(String Name)
	{
		for(SoundLoader Sound : Sounds)
		{
			if(Sound.equals(Name))
			{
				Sound.play();
			}
		}
	}
	
	
	// Loops a sound
	
	public void loopSound(String Name)
	{
		for(SoundLoader Sound : Sounds)
		{
			if(Sound.equals(Name))
			{
				Sound.loop();
			}
		}
	}
	
	
	// Stops a sound
	
	public void stopSound(String Name)
	{
		for(SoundLoader Sound : Sounds)
		{
			if(Sound.equals(Name))
			{
				Sound.stop();
			}
		}
	}
}
