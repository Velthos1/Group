/**
 * Dalthow
 *
 * 
 * @Author Trevi Awater 
 * @License Creative Commons 4
 * 
 **/

package Trevi.Dalthow.Manager;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Trevi.Dalthow.Common.Reference;
 
public class ProgressManager
{
	// Declaration
	
	public static double xPos;
	public static double yPos;
	
	public static double Health;
	
	
	// The method called when the game is closed and when progress needs to be saved.
	
	public static void saveProgress(double xPos, double yPos, double Health)
	{
		 try 
		 {
			 	// Creating a document and adding a element to put information in
			 
				DocumentBuilderFactory Par1 = DocumentBuilderFactory.newInstance();
				DocumentBuilder Par2 = Par1.newDocumentBuilder();
		 
				Document Par3 = Par2.newDocument();
				Element Par4 = Par3.createElement(Reference.Title);
				
				Par3.appendChild(Par4);
		 
				
				// Saving the players horizontal coordinate 
				
				Element Par5 = Par3.createElement("xPos");
				
				Par5.appendChild(Par3.createTextNode(Double.toString(xPos)));
				Par4.appendChild(Par5);
		 
				
				// Saving the players vertical coordinate
				
				Element Par6 = Par3.createElement("yPos");
				
				Par6.appendChild(Par3.createTextNode(Double.toString(yPos)));
				Par4.appendChild(Par6);
		 
				
				// Saving the players health
				
				Element Par7 = Par3.createElement("Health");

				Par7.appendChild(Par3.createTextNode(Double.toString(Health)));
				Par4.appendChild(Par7);
		 
				
				// Saving the file
				
				TransformerFactory Par8 = TransformerFactory.newInstance();
				Transformer Par9 = Par8.newTransformer();
				DOMSource Par10 = new DOMSource(Par3);
				StreamResult Par11 = new StreamResult(new File("Progress.xml"));
		 
				Par9.transform(Par10, Par11);
		} 
		 
		catch (ParserConfigurationException Stacktrace) 
		{
			Stacktrace.printStackTrace();
		} 
		 
		catch (TransformerException Stacktrace) 
		{
			Stacktrace.printStackTrace();
		}
	}
	
	
	// The method called when the game is opened and data needs to be loaded.

	public static void loadProgress()
	{
		try 
		{
			// Opening the file and telling it to look under our section
			
			File Par1 = new File("Progress.xml");
			
			DocumentBuilderFactory Par2 = DocumentBuilderFactory.newInstance();
			DocumentBuilder Par3 = Par2.newDocumentBuilder();
			Document Par4 = Par3.parse(Par1);
		 
			Par4.getDocumentElement().normalize();
		 
			NodeList Par5 = Par4.getElementsByTagName(Reference.Title);
		 
			
			// Looping through the file looking for the selected variables 
			
			for (int Temp = 0; Temp < Par5.getLength(); Temp++) 
			{
				Node Par6 = Par5.item(Temp);
		 
				if (Par6.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element eElement = (Element) Par6;
		 
					xPos = Double.parseDouble((String) eElement.getElementsByTagName("xPos").item(0).getTextContent());
					yPos = Double.parseDouble((String) eElement.getElementsByTagName("yPos").item(0).getTextContent());
					Health = Double.parseDouble((String) eElement.getElementsByTagName("Health").item(0).getTextContent());
				}
			}
		}
		
		catch(Exception Stacktrace)
		{
			Stacktrace.printStackTrace();
		}
	}
}