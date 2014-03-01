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
import java.awt.Rectangle;
import java.util.LinkedList;

import Trevi.Dalthow.Common.Main;
import Trevi.Dalthow.Object.Item;

public class ObjectManager
{
	// Declaration
	
	public LinkedList<Item> Items = new LinkedList<Item>();
	public Item tempItem;
	
	
	// Everything in the game that renders
	
	public void renderItem(Graphics Graphics)
	{
		for(int Par1 = 0; Par1 < Items.size(); Par1++)
		{
			tempItem = Items.get(Par1);
			
			tempItem.render(Graphics);
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
				
			}
			
			tempItem.tick();	
		}
	}
	
	
	// Adds a record to the linked list 
	
	public void addItem(Item Item)
	{
		Items.add(Item);
	}
	
	
	// Removes a record from the linked list
	
	public void removeItem(int Record)
	{
		Items.remove(Record);
	}
}
