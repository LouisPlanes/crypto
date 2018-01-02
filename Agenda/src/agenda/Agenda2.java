/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import org.jdom2.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;



/**
 *
 * @author Khoob (Gabin MICHALET)
 */
public class Agenda2 extends Canvas{
    private HashMap<Integer, List<Triple>> calendar = new HashMap();
    private ArrayList ls = new ArrayList();
    //private Triple triple = new Triple();
    
    public Agenda2() {super.setSize(100,20);}
  
    public int transform(String date) 
    {
        String[] tmp = date.split("/");
        int temp = Integer.parseInt(tmp[0]) + Integer.parseInt(tmp[1]) * 100 + Integer.parseInt(tmp[2]) * 10000; 
        return temp;
    }
    
    public void addDayListener(DayListener l) { ls.add(l); }
    public void removeDayListener(DayListener l) { ls.remove(l); }
    public void fireDayEvent(DayEvent e) {
        Iterator i = ls.iterator();
        while (i.hasNext())
            ((DayListener)i.next()).day(e);
    }
    
    
/*      map.put(20180101,new ArrayList<Triple>());
    	map.get(20180101).add(new Triple("08h00", "04h00", "Reunion"));
    	map.get(20180101).add(new Triple("12h00", "02h00", "Repas"));
    	map.put(20180102,new ArrayList<Triple>());
    	map.get(20180102).add(new Triple("14h00", "04h00", "Reunion2"));*/
    
    public void addEvent(String date, Triple info) {    //addEvent
        //int temp = transform(date);
        //this.calendar.put(transform(date), info);
        int key = transform(date);
        if(!this.calendar.containsKey(key))
        {
            this.calendar.put(key, new ArrayList<>());
        }
        this.calendar.get(key).add(info);
    }
    
    public void addEvent(Evenement event)
    {
        String date = event.getDate();
        Triple info = event.getTriple();
        
        int key = transform(date);
        if(!this.calendar.containsKey(key))
        {
            this.calendar.put(key, new ArrayList<>());
        }
        this.calendar.get(key).add(info);
    }
    
    public Evenement getEvent(String d, String h)
    {
        try
        {
            Evenement event = new Evenement();
            List<Triple> list = this.getTripleList(d);

            for(Triple trp:list)
            {
                if(trp.getHeure().equals(h));
                {
                    event.setDate(d);
                    event.setTriple(h,trp.getDuree(),trp.getDescevent());
                    return event;
                }
            }
        }
        catch(Exception e){}
        
        return null; // Il faut gerer l'execption du cas où il n'y a pas
                     // d'evenement associé au couple date+heure 
    }
    
    /*public void setEvent(String heure, String duree, String descp)
    {
        this.triple.setHeure(heure);
        this.triple.setDuree(duree);
        this.triple.setDescevent(descp);
    }*/
    
    
    //
    //  Methode qui renvoie la liste des Triple associé à la date d
    //

    /**
     *
     * @param d
     * @return List of Triple
     * @throws org.jdom2.JDOMException
     * @throws java.io.IOException
     */
    public List<Triple> getTripleList (String d)throws JDOMException, IOException{

            List<Triple> eventList = new ArrayList();
            for(Map.Entry<Integer, List<Triple>> entry : this.calendar.entrySet())
            {
                if(entry.getKey() == (transform(d)))
                {
                    eventList = (entry.getValue());
                    return eventList;
                }
            }

            this.fireDayEvent(new DayEvent(this));

            //Exception a gerer; // Peut etre ajouter une methode ContainTripleList(String d)
        return null;    //je sais pas comment virer ca
    }

    
    public void paint(Graphics g){
        g.setFont(java.awt.Font.getFont(java.awt.Font.DIALOG));
        g.drawString("Agenda", 10, 10);
    }
    
    
    public HashMap<Integer, List<Triple>> getCalendar()
    {
        return calendar;
    }
    
     
    public String ToString()
    {
        // Ecriture de la HashMap sous forme de Document XML
        
        Element root = new Element("data");
    	Document document = new Document(root);

        //	Parcours de la map date par date
        for(Map.Entry<Integer, List<Triple>> entry : calendar.entrySet())
        {
                //	Récuperation de la key = date
                int key = entry.getKey();

                //	Récupération de chaque liste de triplet associé à la date Key
                List<Triple> t = entry.getValue();

                for(Triple trp:t)
                {
                        root.addContent(new Element("event")
                                        .setAttribute(new Attribute("duree", trp.getDuree()))
                                        .setAttribute(new Attribute("heure", trp.getHeure()))
                                        .setAttribute(new Attribute("date",new StringBuilder().append(""+key).toString()))
                                        .addContent(new Element("details")
                                                        .setText(trp.getDescevent()))
                                        );
                }	

        }
		
        
        //  Transformation du Document XML en string
        
        SAXBuilder builder = new SAXBuilder();
        XMLOutputter sortie = new XMLOutputter(Format.getCompactFormat());
        String xmlString = sortie.outputString(document);
        
        return xmlString;
    }
    
    
    //  je vais directement faire une methode qui consistera a reformer directement
    //  la Hashmap = base de donnée a partir du String de Louis
    public Document stringToXml(String xmlString) throws JDOMException, IOException
    {
        SAXBuilder builder = new SAXBuilder();
        InputStream stream = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
        Document xmlDoc = builder.build(stream);
        
        return xmlDoc;
    }
    
}
