/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Khoob (Gabin MICHALET)
 */
public class Agenda2 extends Canvas{
    private HashMap<Integer, List<Triple>> calendar = new HashMap();
    private ArrayList ls = new ArrayList();
    private Triple event = new Triple();
    
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
            this.calendar.put(key, new ArrayList<Triple>());
        }
        this.calendar.get(key).add(info);
    }
    
    public List<Triple> getEventList (String date) {

        List<Triple> result = new ArrayList();
        for(Map.Entry<Integer, List<Triple>> entry : this.calendar.entrySet())
        {
            if(entry.getKey() == (transform(date)))
            {
                result = (entry.getValue());
            }
        }
        
        return result;
    }
    public void setAFaireJour(String heure,String duree,String descevent) {
        event.setHeure(heure);
        event.setDuree(duree);
        event.setDescevent(descevent);
    } 
    public Triple getAFaireJour() {
        return event;
    }
    public void setDay(String date, Triple txt) {
        addEvent(date,txt);
    }
    
    public void getDay(String date) { 
        event=getEventList(date);              //Need gabin pour comprendre
        this.fireDayEvent(new DayEvent(this));
    }
    
    public void paint(Graphics g){
        g.setFont(java.awt.Font.getFont(java.awt.Font.DIALOG));
        g.drawString("Agenda", 10, 10);
    }
    
    public HashMap<Integer, List<Triple>> getCalendar()
    {
        return calendar;
    }
    
}
