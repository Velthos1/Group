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
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Trevi.Dalthow.Handler.BufferLoader;
import Trevi.Dalthow.Handler.KeyInput;
import Trevi.Dalthow.Handler.MouseInput;
import Trevi.Dalthow.Manager.ProgressManager;
import Trevi.Dalthow.Object.Player;

public class Main extends Canvas implements Runnable
{
	// Declaration
	
	private boolean Running = false;
	private boolean Info = false;

	private int Frames, absoluteFrames;
	private int Ticks, absoluteTicks;
	
	private static Calendar Time = Calendar.getInstance();
	
	private static JFrame Frame = new JFrame(Reference.Title);
	
	private BufferedImage Image = new BufferedImage(Reference.Width, Reference.Height, BufferedImage.TYPE_INT_RGB);
	
	private BufferedImage Item, Player, Logo, Map;
	private State currentState;
	private Thread Loop;

	private static Player Character;
	
	private enum State
	{
		Splash, Menu, Game, Options
	}
	
	
	// Initializes
	
	public void init()
	{
		currentState = State.Splash;
		
		BufferLoader Loader = new BufferLoader();
		
		try
		{
			Item = Loader.loadImage("/Graphics/Game/Object/Item.png");
			Player = Loader.loadImage("/Graphics/Game/Object/Player.png");
			Logo = Loader.loadImage("/Graphics/Menu/Logo.png");
			Map = Loader.loadImage("/Graphics/Game/Terrain/Map.png");
		}
		
		catch(IOException Stacktrace)
		{
			Stacktrace.printStackTrace();
		}
		
		Character = new Player(Reference.Width - 16, Reference.Height - 32, 10.0, this);
		
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
				
				absoluteFrames = Frames;
				absoluteTicks = Ticks;
				
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
			Graphics.drawImage(Image, 0, 0, getWidth(), getHeight(), this);
			Graphics.drawImage(Logo, 0, 0, getWidth(), getHeight(), this);
		}
			
		if(currentState == State.Menu)
		{
			Graphics.drawImage(Image, 0, 0, getWidth(), getHeight(), this);
		}
		
		if(currentState == State.Game)
		{
			Graphics.drawImage(Image, 0, 0, getWidth(), getHeight(), this);
			Graphics.drawImage(Map, (int)Character.getX() - 512, (int)Character.getY() - 512, 1024, 1024, this);
			
			Character.render(Graphics, getWidth() / 50, getHeight() / 20);
			
			if(Info == true)
			{
				Graphics.drawString("VERSION: " + Reference.Version, 6, 15);
				Graphics.drawString("FPS: " + absoluteFrames, 6, 30);
				Graphics.drawString("TICKS: " + absoluteTicks, 6, 45);
				
				Graphics.drawString("X: " + Character.getX(), 6, 75);
				Graphics.drawString("Y: " + Character.getY(), 6, 90);
			}
		}
		
		Graphics.dispose();
		Strategy.show();
	}

	
	// Does something when a certain key gets pressed 
	
	public void keyPressed(KeyEvent Par1) throws Exception
	{
		int Key = Par1.getKeyCode();
	
		double movementSpeed = 3.75;
		
		if(currentState == State.Game)
		{
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

			else if(Key == KeyEvent.VK_F2)
			{
				Robot Par2 = new Robot();
		 
				BufferedImage screenShot = Par2.createScreenCapture(Frame.getBounds());
				ImageIO.write(screenShot, "PNG", new File(Time.get(Calendar.YEAR) + "-" + (Time.get(Calendar.MONTH) + 1) + "-" + Time.get(Calendar.DAY_OF_MONTH) + "-" + Time.get(Calendar.HOUR_OF_DAY) + "-" + Time.get(Calendar.MINUTE) + "-" + Time.get(Calendar.SECOND) + ".png"));
				
				JOptionPane.showMessageDialog(Frame, "You just made a screenshot.");
			}
				
			else if(Key == KeyEvent.VK_F3)
			{
				Info = true;
			}
			
			else if(Key == KeyEvent.VK_ESCAPE)
			{
				currentState = State.Menu;
			}
		}
	}
	
	
	// Does something when a certain key gets released 
	
	public void keyReleased(KeyEvent Par1)
	{
		int Key = Par1.getKeyCode();
		
		if(currentState == State.Game)
		{
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
	}
	

	// Does something when the mouse gets clicked

	public void mouseClicked(MouseEvent Par1) 
	{	
		if(Par1.getButton() == 1)
		{
			if(currentState == State.Menu)
			{
				
			}
			
			else if(currentState == State.Options)
			{
				
			}
			
			else if(currentState == State.Game)
			{
				
			}
		}
		
		else if(Par1.getButton() == 3)
		{
			if(currentState == State.Menu)
			{
				
			}
			
			else if(currentState == State.Options)
			{
				
			}
			
			else if(currentState == State.Game)
			{
				
			}
		}
	}
	

	// The place where the game gets started
	
	public static void main(String Args[])
	{
		Main Game = new Main();
		
		Game.setPreferredSize(new Dimension(Reference.Width * Reference.Scale, Reference.Height * Reference.Scale));
		
		Frame.add(Game);
		Frame.pack();
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setResizable(false);
		Frame.setLocationRelativeTo(null);
		Frame.setVisible(true);
		Frame.addWindowListener(new java.awt.event.WindowAdapter() 
		{
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent Par1) 
		    {
		    	ProgressManager.saveProgress(Character.getX(), Character.getY(), Character.getHealth());
		    }
		});
		
		Game.start();
	}
	
	
	// Getters
	
	public BufferedImage getPlayerSpriteSheet()
	{
		return Player;
	}
}