/**
 * Dalthow
 *
 * 
 * @Author Trevi Awater 
 * @License Creative Commons 4
 * 
 **/

package Trevi.Group.Common;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Trevi.Group.Handler.BufferLoader;
import Trevi.Group.Handler.KeyInput;
import Trevi.Group.Handler.MouseInput;
import Trevi.Group.Handler.SpriteGrabber;
import Trevi.Group.Manager.ObjectManager;
import Trevi.Group.Manager.ProgressManager;
import Trevi.Group.Manager.SoundManager;
import Trevi.Group.Object.Block;
import Trevi.Group.Object.Player;
import Trevi.Group.Util.MathHelper;

public class Main extends Canvas implements Runnable
{
	// Declaration
	
	private boolean isRunning = false;
	private boolean canAttack = true;
	
	public static boolean Info = false;
	
	private int Frames, absoluteFrames;
	private int Ticks, absoluteTicks;
	
	private double creditSpeed;
	
	private Calendar Time = Calendar.getInstance();
	
	public static JFrame Frame = new JFrame(Reference.Title);
	
	private BufferedImage Image = new BufferedImage(Reference.Width, Reference.Height, BufferedImage.TYPE_INT_RGB);
	
	private BufferedImage Item, Block, Player, Shadow, Heart, Currency, Energy, Logo, Button, Map;
	private State currentState;
	private Thread Loop;
	private Font Console, Fancy;
	
	public static Player Character;
	
	private static ObjectManager Object;

	public static Main Instance;
	
	private enum State
	{
		Splash, Title, Game, Menu, Credits, Options
	}
	
	
	// Initializes
	
	public void init() throws Exception
	{
		Instance = this;
		currentState = State.Splash;
		creditSpeed = 0;
		
		BufferLoader Loader = new BufferLoader();
		
		try
		{
			Item = Loader.loadImage("/Graphics/Game/Object/Item.png");
			Block = Loader.loadImage("/Graphics/Game/Object/Block.png");
			Player = Loader.loadImage("/Graphics/Game/Object/Player.png");
			Shadow = Loader.loadImage("/Graphics/Game/Terrain/Shadow.png");
			Map = Loader.loadImage("/Graphics/Game/Terrain/Map.png");
			Button = Loader.loadImage("/Graphics/Menu/Button.png");
			Logo = Loader.loadImage("/Graphics/Splash/Logo.png");
			
			Console = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(System.getProperty("user.dir") + "/Resources/Font/Console.ttf"))).deriveFont(Font.PLAIN, 13);
			Fancy = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(System.getProperty("user.dir") + "/Resources/Font/Fancy.ttf"))).deriveFont(Font.PLAIN, 28);
		}
		
		catch(IOException Stacktrace)
		{
			Stacktrace.printStackTrace();
		}
		
		Character = new Player(Reference.Width - 16, Reference.Height - 32, 10.0, this);
		
		Object = new ObjectManager();
		
		Object.addBlock(new Block(1, 1, 200, 400, this, "closedChest"));
		Object.addBlock(new Block(1, 1, 300, 400, this, "closedChest"));
		
		for(int Par1 = 0; Par1 < 1024; Par1 += 64)
		{
			Object.addBlock(new Block(3, 1, 0 + Par1, 0, this, "Wall"));
			Object.addBlock(new Block(3, 1, Par1, 960, this, "Wall"));
			Object.addBlock(new Block(3, 1, 0, 0  + Par1, this, "Wall"));
			Object.addBlock(new Block(3, 1, 960, 0  + Par1, this, "Wall"));
		}
		
		addKeyListener(new KeyInput(this));
		addMouseListener(new MouseInput(this));
		requestFocus();
	}
	
	
	// Starts the game loop
	
	private synchronized void start()
	{
		if(isRunning == true)
		{
			return;
		}
		
		Loop = new Thread(this);
		Loop.start();
		
		isRunning = true;
	}
	
	
	// Stops the game loop
	
	private synchronized void stop()
	{
		if(isRunning == false)
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
		
		isRunning = false;
		
		System.exit(1);
	}
	
	
	// The game loop itself
	
	public void run()
	{
		try 
		{
			init();
		} 
		
		catch (Exception Stacktrace) 
		{
			Stacktrace.printStackTrace();
		}
		
		long lastTime = System.nanoTime();
		
		double amountOfTicks = 60.0;
		double nanoSeconds = 1000000000 / amountOfTicks;
		double Delta = 0;
		
		long Timer = System.currentTimeMillis();
		
		while(isRunning == true)
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
				
				catch (Exception Stacktrace)
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
	
	private void tick() throws Exception 
	{
		if(currentState == State.Splash)
		{
			Thread.sleep(1750);
			
			currentState = State.Game;
		}
		
		else if(currentState == State.Game)
		{
			Character.tick();
			
			Object.tickItem();
			Object.tickBlock();
		}
		
		else if(currentState == State.Credits)
		{
			creditSpeed += 0.4;
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
			Graphics.drawImage(Image, 0, 0, Frame.getWidth(), Frame.getHeight(), this);
			Graphics.drawImage(Logo, 0, 0, Frame.getWidth(), Frame.getHeight(), this);
		}
			
		else if(currentState == State.Title)
		{
			Graphics.drawImage(Image, 0, 0, Frame.getWidth(), Frame.getHeight(), this);
		}
		
		else if(currentState == State.Menu)
		{
			Graphics.drawImage(Image, 0, 0, getWidth(), getHeight(), this);
			
			Graphics.drawImage(Button, Reference.Width - 100, Reference.Height, 200, 50, this);
			Graphics.drawImage(Button, Reference.Width - 100, Reference.Height + 75, 200, 50, this);
			Graphics.drawImage(Button, Reference.Width - 100, Reference.Height + 150, 200, 50, this);
			Graphics.drawImage(Button, Reference.Width - 100, Reference.Height + 225, 200, 50, this);
			
			Graphics.setFont(Fancy);
			
			Graphics.setColor(Color.black);
			
			Graphics.drawString("Return.", 415, 398);
			Graphics.drawString("Options.", 408, 471);
			Graphics.drawString("Credits", 417, 548);
			Graphics.drawString("Exit.", 443, 623);
		}
		
		else if(currentState == State.Options)
		{
			Graphics.drawImage(Image, 0, 0, Frame.getWidth(), Frame.getHeight(), this);
		}
		
		else if(currentState == State.Credits)
		{
			Graphics.drawImage(Image, 0, 0, Frame.getWidth(), Frame.getHeight(), this);

			Graphics.setFont(Fancy.deriveFont(Font.PLAIN, 48));
			Graphics.drawString("Group 12", Frame.getWidth() / 2 - 95, (int) (800 - creditSpeed));
			
			Graphics.setFont(Fancy.deriveFont(Font.PLAIN, 28));
			Graphics.drawString("Created by Dalthow Game Studios", Frame.getWidth() / 2 - 270, (int) (875 - creditSpeed));
		}
		
		else if(currentState == State.Game)
		{
			Graphics.drawImage(Image, 0, 0, Frame.getWidth(), Frame.getHeight(), this);
			Graphics.drawImage(Map, (int)Character.getX(), (int)Character.getY(), 1024, 1024, this);	

			Graphics.drawImage(Shadow, Frame.getWidth() / 2 - 47, Frame.getHeight() / 2 + 12, 48 * Reference.Scale, 48 * Reference.Scale, this);	
			
			Object.renderBlock(Graphics);
			Object.renderItem(Graphics);
			
			Character.render(Graphics, Frame.getWidth(), Frame.getHeight());
			
			if(Info == true)
			{
				Graphics.setFont(Console);
				
				Graphics.drawString("VERSION: " + Reference.Version, 6, 16);
				Graphics.drawString("FPS: " + absoluteFrames, 6, 31);
				Graphics.drawString("TICKS: " + absoluteTicks, 6, 46);
				
				Graphics.drawString("X: " + Character.getX(), 6, 76);
				Graphics.drawString("Y: " + Character.getY(), 6, 91);
			}

			SpriteGrabber Sprite = new SpriteGrabber(Item);
			
			Heart = Sprite.grabItemImage(1, 1, 32, 32);
			Currency = Sprite.grabItemImage(2, 1, 32, 32);
			Energy = Sprite.grabItemImage(3, 1, 32, 32);
			
			Graphics.drawImage(Heart, (Frame.getWidth() - (350 - 231)), 10, 32, 32 , null);
			Graphics.drawImage(Currency, (Frame.getWidth() - (350 - 231)), 47, 32, 32 , null);
			Graphics.drawImage(Energy, (Frame.getWidth() - (350 - 231)), 84, 32, 32 , null);
			
			Graphics.setFont(Fancy);
			Graphics.setColor(Color.darkGray);
			
			Graphics.drawString("" + MathHelper.round(Character.getHealth()), (Frame.getWidth() - (350 - 270)), 39);
			Graphics.drawString("" + MathHelper.round(Character.getCurrency()), (Frame.getWidth() - (350 - 270)), 76);
			Graphics.drawString("" + MathHelper.round(Character.getEnergy()), (Frame.getWidth() - (350 - 270)), 113);
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
				if(Character.canMoveUp == true)
				{
					Character.setVelY(movementSpeed);
					Character.canMoveDown = true;
				}
			}
			
			else if(Key == KeyEvent.VK_S)
			{
				if(Character.canMoveDown == true)
				{
					Character.setVelY(- movementSpeed);
					Character.canMoveUp = true;
				}
			}
			
			else if(Key == KeyEvent.VK_D)
			{
				if(Character.canMoveRight == true)
				{
					Character.setVelX(- movementSpeed);
					Character.canMoveLeft = true;
				}
			}
			
			else if(Key == KeyEvent.VK_A)
			{
				if(Character.canMoveLeft == true)
				{
					Character.setVelX(movementSpeed);
					Character.canMoveRight = true;
				}
			}
			
			else if(Key == KeyEvent.VK_SPACE)
			{
				if(Character.getEnergy() > 0)
				{
					if(canAttack == true)
					{
						Character.setEnergy(Character.getEnergy() - 5.0);
						Character.attack();
						
						canAttack = false;
					}
				}
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

		else if(currentState == State.Menu)
		{
			if(Key == KeyEvent.VK_ESCAPE)
			{
				currentState = State.Game;
			}
		}
		
		else if(currentState == State.Options)
		{
			if(Key == KeyEvent.VK_ESCAPE)
			{
				currentState = State.Menu;
			}
		}
		
		else if(currentState == State.Credits)
		{
			if(Key == KeyEvent.VK_ESCAPE)
			{
				currentState = State.Menu;
				creditSpeed = 0;
				
				SoundManager.stopMusic();
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
				Character.canMoveUp = true;
			}
			
			else if(Key == KeyEvent.VK_S)
			{
				Character.setVelY(0);
				Character.canMoveDown = true;
			}
			
			else if(Key == KeyEvent.VK_D)
			{
				Character.setVelX(0);
				Character.canMoveRight = true;
			}
			
			else if(Key == KeyEvent.VK_A)
			{
				Character.setVelX(0);
				Character.canMoveLeft = true;
			}
			
			else if(Key == KeyEvent.VK_SPACE)
			{
				canAttack = true;
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
		PointerInfo Par2 = MouseInfo.getPointerInfo();
		Point Par3 = new Point(Par2.getLocation());
		SwingUtilities.convertPointFromScreen(Par3, Par1.getComponent());
		
		if(Par1.getButton() == 1)
		{
			if(currentState == State.Menu)
			{
				if(Par3.x > 380 && Par3.x < 580)
				{
					if(Par3.y > 360 && Par3.y < 410) 
					{
						currentState = State.Game;
					}
				}
				
				if(Par3.x > 380 && Par3.x < 580)
				{
					if(Par3.y > 435 && Par3.y < 485) 
					{
						currentState = State.Options;
						
					}
				}
				
				if(Par3.x > 380 && Par3.x < 580)
				{
					if(Par3.y > 510 && Par3.y < 560) 
					{
						currentState = State.Credits;
						
						SoundManager.playMusic("2nd Flight.wav");
					}
				}
				
				if(Par3.x > 380 && Par3.x < 580)
				{
					if(Par3.y > 585 && Par3.y < 635) 
					{
						System.exit(0);
					}
				}
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
		    	ProgressManager.saveProgress(Character.getX(), Character.getY(), Character.getHealth(), Character.getEnergy(), Character.getCurrency());
		    }
		});
		
		Game.start();
	}
	
	
	// Getters
	
	public BufferedImage getPlayerSpriteSheet()
	{
		return Player;
	}


	public BufferedImage getItemSpriteSheet() 
	{
		return Item;
	}
	
	public BufferedImage getBlockSpriteSheet() 
	{
		return Block;
	}
}