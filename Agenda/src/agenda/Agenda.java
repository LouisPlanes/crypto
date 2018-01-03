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


import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;



/**
 *
 * @author Khoob (Gabin MICHALET)
 */
public class Agenda extends Canvas{
    private HashMap<Integer, List<Triple>> calendar = new HashMap();
    private ArrayList ls = new ArrayList();
    
    public Agenda() {super.setSize(60,15);}
    
    
    /**
     * Trasnformation d'une date au format JJ/MM/AAAA en AAAAMMJJ
     * @param date : date à transformer
     * @return un int représentant la date
     */
    
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
    
    
    
    //OK
    /**
     * Ajoute un evenement à la base de donnée
     * @param date : date de l'event à ajouter
     * @param info : informations de l'event à ajouter
     */
    public void addEvent(String date, Triple info) {    //addEvent
        int key = transform(date);
        if(!this.calendar.containsKey(key))
        {
            this.calendar.put(key, new ArrayList<>());
        }
        this.calendar.get(key).add(info);
    }
    
    //OK
    /**
     * Ajoute un evenement à la base de donnée
     * @param event : event à ajouter
     */
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
    
    
    // A coder si on a le temps
    public void setEvent(String date, String heure)
    {
        
    }
    
    // Test OK
    /**
     * Permet la recuperation d'un event de la base de donnée en connaissant 
     * sa date et son heure de début
     * @param d : date de l'event à recupérer
     * @param h : heure de l'event a récupérer
     * @return l'event souhaité
     */
    public Evenement getEvent(String d, String h)
    {


            List<Triple> list = this.getTripleList(d);

            
            for(Triple trp:list)
            {

                if(trp.getHeure().equals(h));
                {

                    String descp = trp.getDescevent();
                    String duree = trp.getDuree();
                    Evenement event = new Evenement(d,new Triple(h,duree,descp));
                    return event;
                }
            }
        
        return null; 
                     
    }

    
    //
    //  Methode qui renvoie la liste des Triple associé à la date d
    //

    // Test OK
    /**
     * Permet la récupération de la liste des Triple d'events à un date donnée
     * @param d : date des events
     * @return Une liste de Triple 
     */
    public List<Triple> getTripleList (String d){
        List<Triple> eventList = new ArrayList();
        if (!calendar.isEmpty()){
            for(Map.Entry<Integer, List<Triple>> entry : this.calendar.entrySet())
            {
               
               if(entry.getKey() == (transform(d)))
               {
                    eventList = (entry.getValue());
                    return eventList;
                }
            }
        }
        
        return null;    
    }
    
    public void fireEvent() {
        this.fireDayEvent(new DayEvent(this));
    }

    
    public void paint(Graphics g){
        g.setFont(java.awt.Font.getFont(java.awt.Font.DIALOG));
        g.drawString("Agenda", 10, 10);
    }
    
    /**
     * Récupération de la base de donnée
     * @return la base de donnée contenant les events
     */
    public HashMap<Integer, List<Triple>> getCalendar()
    {
        return calendar;
    }
    
    //  Test OK
    /**
     * affiche le contenu de la base de données dans un toString
     * @return la base de donnée dans un toString
     */
    public String printCalendar()
    {
        return this.calendar.toString();
    }
    
    //  Test OK
    /**
     * Efface le contenu de la base de donnée
     */
    public void clearCalendar()
    {
        this.calendar.clear();
    }
    
    // Test OK

    /**
     * Retourne une string au format xml contenant le contenu du calendrier
     * @return 
     */
    @Override
    public String toString()
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
    /**
     * Génère un calendrier depuis un string au format XML
     * @param xmlString la string au format xml
     * @throws JDOMException
     * @throws IOException
     */
    public void reloadCalendar(String xmlString) throws JDOMException, IOException
    {
        //  Netoyage du Calendar actuel
        this.clearCalendar();
        
        try {
            //  Transformation du String contenant les données de Calendar en Document Xml          
            SAXBuilder sxb = new SAXBuilder();
            String exampleXML = xmlString;
            InputStream stream = new ByteArrayInputStream(exampleXML.getBytes("UTF-8"));
            Document xmlDoc = sxb.build(stream);

            //  Récupération de tous les Element "event" de xmlDoc dans une List
            Element element = xmlDoc.getRootElement();
            List<Element> events = element.getChildren();
                  
            ListIterator<Element> iterator = events.listIterator();

            
            //  Génération d'une Liste conteneant tous les evenements de xmlDoc
            //  depuis la List ci-dessus
            List<Evenement> eventList = new ArrayList<>();
            while (iterator.hasNext()) 
            {
                Element el = (Element) iterator.next();

                Triple infos = new Triple(  el.getAttributeValue("heure"),
                                            el.getAttributeValue("duree"),
                                            el.getChild("details").getText());
                
                Evenement event = new Evenement(el.getAttributeValue("date"), infos);

                

                eventList.add(event);
            }
            
            
            //  Copier-coller de addEvent(Evenement event) avec ajsutement
            //  permettant l'ajout des events dans Calendar sans doublon de date
            //  en parcourant la liste d'event.
            for(Evenement ev:eventList)
            {
                String date = ev.getDate();
                Triple info = ev.getTriple();

                int key = Integer.parseInt(date);
                if(!this.calendar.containsKey(key))
                {
                    this.calendar.put(key, new ArrayList<>());
                }
                this.calendar.get(key).add(info);
            }
		      

        } catch (JDOMException | IOException e) {
          e.printStackTrace(System.out);
        }
    }
    
}