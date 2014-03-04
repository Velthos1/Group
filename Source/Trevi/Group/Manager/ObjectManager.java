/**
 * Dalthow
 *
 * 
 * @Author Trevi Awater 
 * @License Creative Commons 4
 * 
 **/

package Trevi.Group.Manager;

import java.awt.Graphics;
import java.util.LinkedList;

import Trevi.Group.Common.Main;
import Trevi.Group.Object.Block;
import Trevi.Group.Object.Item;
import Trevi.Group.Util.RandomGenerator;

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
					if(Main.Character.getHealth() <= 9)
					{
						Main.Character.setHealth(Main.Character.getHealth() + 1.0);
						SoundManager.playSound("Pickup.wav");
						removeItem(Par1);
					}		
				}

				else if(tempItem.getName() == "Coin")
				{
					if(Main.Character.getCurrency() <= 999)
					{
						Main.Character.setCurrency(Main.Character.getCurrency() + 1.0);
						SoundManager.playSound("Pickup.wav");
						removeItem(Par1);
					}
				}

				else if(tempItem.getName() == "Potion")
				{
					if(Main.Character.getEnergy() <= 80)
					{
						Main.Character.setEnergy(Main.Character.getEnergy() + 20.0);
						SoundManager.playSound("Pickup.wav");
						removeItem(Par1);
					}
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

					for(int Par2 = RandomGenerator.randomNumber(1, 3); Par2 > 0; Par2--)
					{
						addItem(new Item(2, 1, tempBlock.getX() + 10 + RandomGenerator.randomNumber(-32, 32), tempBlock.getY() + 10 + RandomGenerator.randomNumber(-32, 32), Main.Instance, "Coin"));
					}

					for(int Par2 = RandomGenerator.randomNumber(0, 1); Par2 > 0; Par2--)
					{
						addItem(new Item(3, 1, tempBlock.getX() + 10 + RandomGenerator.randomNumber(-32, 32), tempBlock.getY() + 10 + RandomGenerator.randomNumber(-32, 32), Main.Instance, "Potion"));
					}
					
					for(int Par2 = RandomGenerator.randomNumber(0, 1); Par2 > 0; Par2--)
					{
						addItem(new Item(1, 1, tempBlock.getX() + 10 + RandomGenerator.randomNumber(-32, 32), tempBlock.getY() + 10 + RandomGenerator.randomNumber(-32, 32), Main.Instance, "Heart"));
					}
					
					removeBlock(Par1);
				}
				
				if(tempBlock.getName() == "closedDoor")
				{
					addBlock(new Block(5, 1, tempBlock.getX(), tempBlock.getY(), Main.Instance, "openDoor"));
					removeBlock(Par1);
				}
				
				if(tempBlock.getName() == "openDoor")
				{
					tempBlock.isSolid = false;
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
