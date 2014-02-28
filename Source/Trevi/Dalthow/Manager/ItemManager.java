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

import Trevi.Dalthow.Object.Item;

public class ItemManager
{
	// Declaration
	
	public LinkedList<Item> List = new LinkedList<Item>();
	public Item tempItem;
	
	
	// Everything in the game that renders
	
	public void render(Graphics Graphics)
	{
		for(int Par1 = 0; Par1 < List.size(); Par1++)
		{
			tempItem = List.get(Par1);
			
			tempItem.render(Graphics);
		}
	}
	
	
	// Everything in the game that updates
	
	public void tick() throws Exception
	{
		for(int Par1 = 0; Par1 < List.size(); Par1++)
		{
			tempItem = List.get(Par1);
			
			tempItem.tick();	
		}
	}
	
	public void add(Item Item)
	{
		List.add(Item);
	}
	
	public void remove(int Record)
	{
		List.remove(Record);
	}
}
