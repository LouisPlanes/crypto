/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import org.jdom2.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import agenda.Agenda2;


import agenda.Triple;

public class Database{
    
	//
    //  Données membres
    //
    private HashMap<Integer, List<Triple>>calendar = new HashMap<Integer, List<Triple>>();
    //
    //  Constructeur
    //
    public Database(HashMap<Integer, List<Triple>> map)
    {
        this.calendar = map;
    }
    
    //
    //  Méthodes
    //
    
    public Document xmlDataWriter()
    {
        
    	Element root = new Element("data");
    	Document document = new Document(root);
    	Element info = new Element("Info");
		root.addContent(info);		
		Element entrees = new Element("Entrees");
		root.addContent(entrees);
		
		//	Parcours de la map date par date
		for(Map.Entry<Integer, List<Triple>> entry : calendar.entrySet())
		{
			//	Récuperation de la key = date
			int key = entry.getKey();
			
			//	Récupération de chaque liste de triplet associé à la date Key
			List<Triple> t = entry.getValue();
			
			for(Triple trp:t)
   		 	{
				entrees.addContent(new Element("event")
						.setAttribute(new Attribute("duree", trp.getDuree()))
						.setAttribute(new Attribute("heure", trp.getHeure()))
						.setAttribute(new Attribute("date",new StringBuilder().append(""+key).toString()))
						.addContent(new Element("details")
								.setText(trp.getDescevent()))
						);
   		 	}	
			
		}
		
        return document;
    }
    
    public String xmlToString(Document xmlDoc)
    {
        SAXBuilder builder = new SAXBuilder();
        XMLOutputter sortie = new XMLOutputter(Format.getCompactFormat());
        String xmlString = sortie.outputString(xmlDoc);
        
        return xmlString;
    }
    
    public Document stringToXml(String xmlString) throws JDOMException, IOException
    {
        SAXBuilder builder = new SAXBuilder();
        InputStream stream = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
        Document xmlDoc = builder.build(stream);
        
        return xmlDoc;
    }
    
    public static void main(String[] args)
    {
    	
    }
}
