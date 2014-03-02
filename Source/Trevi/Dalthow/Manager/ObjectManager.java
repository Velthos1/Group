/**
 * Dalthow
 *
 * 
 * @Author Trevi Awater 
 * @License Creative Commons 4
 * 
 **/

package Trevi.Dalthow.Manager;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import Trevi.Dalthow.Common.Main;
import Trevi.Dalthow.Object.Block;
import Trevi.Dalthow.Object.Item;
import Trevi.Dalthow.Util.RandomGenerator;

public class ObjectManager
{
	// Declaration
	
	public LinkedList<Item> Items = new LinkedList<Item>();
	public LinkedList<Block> Blocks = new LinkedList<Block>();
	
	public Item tempItem;
	public Block tempBlock;

	
	// Everything in the game that renders
	
	public void renderItem(Graphics Graphics)
	{
		for(int Par1 = 0; Par1 < Items.size(); Par1++)
		{
			tempItem = Items.get(Par1);
			
			tempItem.render(Graphics);
		}
	}
	
	
	// Everything in the game that renders
	
	public void renderBlock(Graphics Graphics)
	{
		for(int Par1 = 0; Par1 < Blocks.size(); Par1++)
		{
			tempBlock = Blocks.get(Par1);
			
			tempBlock.render(Graphics);
		}
	}
	
	
	// Everything in the game that updates
	
	public void tickItem() throws Exception
	{
		for(int Par1 = 0; Par1 < Items.size(); Par1++)
		{
			tempItem = Items.get(Par1);
		
			if(tempItem.getBounds().intersects(Main.Character.getBounds(Main.Frame.getWidth(), Main.Frame.getHeight())))
			{
				if(tempItem.getName() == "Heart")
				{
					removeItem(Par1);
					SoundManager.playSound("Pickup.wav");
				}
				
				else if(tempItem.getName() == "Coin")
				{
					removeItem(Par1);
					SoundManager.playSound("Pickup.wav");
				}
				
				else if(tempItem.getName() == "Potion")
				{
					removeItem(Par1);
					SoundManager.playSound("Pickup.wav");
				}
			}
			
			tempItem.tick();	
		}
	}
	
	
	// Everything in the game that updates
	
	public void tickBlock() throws Exception
	{
		for(int Par1 = 0; Par1 < Blocks.size(); Par1++)
		{
			tempBlock = Blocks.get(Par1);
		
			if(tempBlock.getBounds().intersects(Main.Character.getBounds(Main.Frame.getWidth(), Main.Frame.getHeight())))
			{
				if(tempBlock.getName() == "closedChest")
				{
					SoundManager.playSound("Chest.wav");
					
					addBlock(new Block(2, 1, tempBlock.getX(), tempBlock.getY(), Main.Instance, "openChest"));
					
					addItem(new Item(2, 1, tempBlock.getX() + RandomGenerator.randomNumber(-50, 50), tempBlock.getY() + RandomGenerator.randomNumber(-50, 50), Main.Instance, "Coin"));
					addItem(new Item(3, 1, tempBlock.getX() + RandomGenerator.randomNumber(-50, 50), tempBlock.getY() + RandomGenerator.randomNumber(-50, 50), Main.Instance, "Potion"));
					
					removeBlock(Par1);
				}
			}
			
			tempBlock.tick();	
		}
	}
	
	
	// Adds a record to the linked list 
	
	public void addItem(Item Item)
	{
		Items.add(Item);
	}
	
	
	// Adds a record to the linked list 
	
	public void addBlock(Block Block)
	{
		Blocks.add(Block);
	}
	
	
	// Removes a record from the linked list
	
	public void removeItem(int Record)
	{
		Items.remove(Record);
	}
	
	
	// Removes a record from the linked list
	
	public void removeBlock(int Record)
	{
		Blocks.remove(Record);
	}
}
