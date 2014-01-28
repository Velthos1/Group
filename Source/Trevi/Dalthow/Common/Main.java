/**
 * Dalthow
 *
 * 
 * @Author Trevi Awater 
 * @License Creative Commons 4
 * 
 **/

package Trevi.Dalthow.Common;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

import Trevi.Dalthow.Handler.BufferLoader;
import Trevi.Dalthow.Handler.KeyInput;
import Trevi.Dalthow.Handler.MouseInput;
import Trevi.Dalthow.Object.Player;

public class Main extends Canvas implements Runnable
{
	// Declaration
	
	private boolean Running = false;
	private boolean Info = false;
	
	private int Frames, AbsoluteFrames;
	private int Ticks, AbsoluteTicks;
	
	private Thread Loop;
	
	private BufferedImage Image = new BufferedImage(Reference.Width, Reference.Height, BufferedImage.TYPE_INT_RGB);
	private BufferedImage ItemSprite = null, PlayerSprite = null, Logo = null, Prison = null;
	
	private Player Character;
	
	private enum State
	{
		Splash, Menu, Game, Options
	}
	
	private State currentState = State.Splash;
	
	
	// Initialises
	
	public void init()
	{
		BufferLoader Loader = new BufferLoader();
		
		try
		{
			ItemSprite = Loader.loadImage("/Graphics/Game/Object/Items.png");
			PlayerSprite = Loader.loadImage("/Graphics/Game/Object/Player.png");
			Logo = Loader.loadImage("/Graphics/Menu/Logo.png");
			Prison = Loader.loadImage("/Graphics/Game/Terrain/Prison.png");
		}
		
		catch(IOException Stacktrace)
		{
			Stacktrace.printStackTrace();
		}
		
		Character = new Player(Reference.Width - 16, Reference.Height - 32, this);
		
		addKeyListener(new KeyInput(this));
		addMouseListener(new MouseInput(this));
		requestFocus();
	}
	
	
	// Starts the game loop
	
	private synchronized void start()
	{
		if(Running == true)
		{
			return;
		}
		
		Loop = new Thread(this);
		Loop.start();
		
		Running = true;
	}
	
	
	// Stops the game loop
	
	private synchronized void stop()
	{
		if(Running == false)
		{
			return;
		}
		
		try
		{
			Loop.join();
		}
		
		catch(InterruptedException Stacktrace)
		{
			Stacktrace.printStackTrace();
		}
		
		Running = false;
		
		System.exit(1);
	}
	
	
	// The game loop itself
	
	public void run()
	{
		init();
		
		long lastTime = System.nanoTime();
		
		double amountOfTicks = 60.0;
		double nanoSeconds = 1000000000 / amountOfTicks;
		double Delta = 0;
		
		long Timer = System.currentTimeMillis();
		
		while(Running == true)
		{
			long currentTime = System.nanoTime();
			
			Delta += (currentTime - lastTime) / nanoSeconds;
			lastTime = currentTime;
			
			if(Delta >= 1)
			{
				try 
				{
					tick();
				} 
				
				catch (InterruptedException Stacktrace)
				{
					Stacktrace.printStackTrace();
				}
				
				Delta--;
				Ticks++;
			}
			
			render();
			Frames++;
			
			if(System.currentTimeMillis() - Timer > 1000)
			{
				Timer += 1000;
				
				AbsoluteFrames = Frames;
				AbsoluteTicks = Ticks;
				
				Ticks = 0;
				Frames = 0;
			}
		}
		
		stop();
	}	
	
	
	// Everything in the game that updates
	
	private void tick() throws InterruptedException 
	{
		if(currentState == State.Splash)
		{
			Thread.sleep(1750);
			
			currentState = State.Game;
		}
		
		else if(currentState == State.Game)
		{
			Character.tick();
		}
	}
	
	
	// Everything in the game that renders
	
	private void render()  
	{
		BufferStrategy Strategy = getBufferStrategy();
		
		if(Strategy == null)
		{
			createBufferStrategy(3);
			
			return;
		}
		
		Graphics Graphics = Strategy.getDrawGraphics();
		
		if(currentState == State.Splash)
		{
			Graphics.drawImage(Logo, 0, 0, getWidth(), getHeight(), this);
		}
			
		if(currentState == State.Menu)
		{
			
		}
		
		if(currentState == State.Game)
		{
			Graphics.drawImage(Image, 0, 0, getWidth(), getHeight(), this);
			Graphics.drawImage(Prison, (int)Character.getX() - 512, (int)Character.getY() - 512, 1024, 1024, this);
			
			Character.render(Graphics, getWidth() / 50, getHeight() / 20);
			
			if(Info == true)
			{
				Graphics.drawString("VERSION: " + Reference.Version, 6, 15);
				Graphics.drawString("FPS: " + AbsoluteFrames, 6, 30);
				Graphics.drawString("TICKS: " + AbsoluteTicks, 6, 45);
				
				Graphics.drawString("X: " + Character.getX(), 6, 75);
				Graphics.drawString("Y: " + Character.getY(), 6, 90);
			}
		}
		
		Graphics.dispose();
		Strategy.show();
	}

	
	// Does something when a certain key gets pressed 
	
	public void keyPressed(KeyEvent Par1)
	{
		int Key = Par1.getKeyCode();
	
		double movementSpeed = 3.75;
		
		if(Key == KeyEvent.VK_W)
		{
			Character.setVelY(movementSpeed);
		}
		
		else if(Key == KeyEvent.VK_S)
		{
			Character.setVelY(- movementSpeed);
		}
		
		else if(Key == KeyEvent.VK_D)
		{
			Character.setVelX(- movementSpeed);
		}
		
		else if(Key == KeyEvent.VK_A)
		{
			Character.setVelX(movementSpeed);
		}
		
		else if(Key == KeyEvent.VK_F3)
		{
			Info = true;
		}
	}
	
	
	// Does something when a certain key gets released 
	
	public void keyReleased(KeyEvent Par1)
	{
		int Key = Par1.getKeyCode();
		
		if(Key == KeyEvent.VK_W)
		{
			Character.setVelY(0);
		}
		
		else if(Key == KeyEvent.VK_S)
		{
			Character.setVelY(0);
		}
		
		else if(Key == KeyEvent.VK_D)
		{
			Character.setVelX(0);
		}
		
		else if(Key == KeyEvent.VK_A)
		{
			Character.setVelX(0);
		}
		
		else if(Key == KeyEvent.VK_F3)
		{
			Info = false;
		}
	}
	

	// Does something when the mouse gets clicked

	public void mouseClicked(MouseEvent Par1) 
	{	
		if(Par1.getButton() == 1)
		{
			
		}
		
		else if(Par1.getButton() == 3)
		{
			
		}
	}
	

	// The place where the game gets started
	
	public static void main(String Args[])
	{
		Main Game = new Main();
		
		Game.setPreferredSize(new Dimension(Reference.Width * Reference.Scale, Reference.Height * Reference.Scale));
		
		JFrame Frame = new JFrame(Reference.Title);
		
		Frame.add(Game);
		Frame.pack();
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setResizable(false);
		Frame.setLocationRelativeTo(null);
		Frame.setVisible(true);
		
		Game.start();
	}
	
	
	// Getters
	
	public BufferedImage getPlayerSpriteSheet()
	{
		return PlayerSprite;
	}
}