/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import org.jdom2.output.*;
import org.jdom2.*;

import java.io.*;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import agenda.Triple;
import agenda.Agenda;

public class Database{
    
	//
    //  Données membres
    //
	private HashMap<Integer, List<Triple>>calendar = new HashMap<Integer, List<Triple>>();
    
    //
    //  Constructeur
    //
    public Database()
    {
      
    }
    
    //
    //  Méthodes
    //
    
    public Document xmlDataWriter(HashMap<Integer, List<Triple>> map)
    {
        
    	Element root = new Element("data");
    	Document document = new Document(root);
    	Element info = new Element("Info");
		root.addContent(info);		
		Element entrees = new Element("Entrees");
		root.addContent(entrees);
		
		//	Parcours de la map date par date
		for(Map.Entry<Integer, List<Triple>> entry : map.entrySet())
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
    
    
    public static void main(String[] args)
    {
    	
    	
    	/**HashMap<Integer, List<Triple>> map = new HashMap<Integer, List<Triple>>();
    	
    	map.put(20180101,new ArrayList<Triple>());
    	map.get(20180101).add(new Triple("08h00", "04h00", "Reunion"));
    	map.get(20180101).add(new Triple("12h00", "02h00", "Repas"));
    	map.put(20180102,new ArrayList<Triple>());
    	map.get(20180102).add(new Triple("14h00", "04h00", "Reunion2"));
    	
    	//Triple t = new Triple("time", "duration", "desc");
    	//System.out.println(t.getHeure()+"\n"+t.getDuree()+"\n"+t.getDescevent());
    	
    	int[] date = {20180101, 20180102};
    	
    	for(Map.Entry<Integer, List<Triple>> entry : map.entrySet()) 
    	{
    		
    		//	Récupération dateKey
    		int key = entry.getKey();
    		System.out.println(key);
    		
    		//	Récupération de chause Liste de triplet associé a une date
    		List<Triple> t = entry.getValue();
    		System.out.println(t);
    		
    		 //	Récuperation des valeurss de chaque triplet de la liste
    		 for(Triple trp:t)
    		 {
    			 	String heure = trp.getHeure();
    			 	String durée = trp.getDuree();
    			 	String details = trp.getDescevent();
    		 }	


    	}
            // la clef peut être obtenue par entry.getKey()
            // la valeur correspondante par entry.getValue()**/
    	
    }
}
