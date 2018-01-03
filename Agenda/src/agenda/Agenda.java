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

/**
 *
 * @author Khoob (Gabin MICHALET)
 */
public class Agenda extends Canvas{
    private HashMap<Integer, Triple> calendar = new HashMap();
    private ArrayList ls = new ArrayList();
    private Triple aFaireJour = new Triple();
    
    public Agenda() {super.setSize(100,20);}
  
    public int transform(String date) {
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
    
    public void setEvent(String date, Triple info) {
        //int temp = transform(date);
        this.calendar.put(transform(date), info);
    }
    
    public Triple getEvent (String date) {
        Triple result =  this.calendar.get(transform(date));
        return(result==null?new Triple():result);
    }
    public void setAFaireJour(String heure,String duree,String descevent) {
        aFaireJour.setHeure(heure);
        aFaireJour.setDuree(duree);
        aFaireJour.setDescevent(descevent);
    } 
    public Triple getAFaireJour() {
        return aFaireJour;
    }
    public void setDay(String date, Triple txt) {
        setEvent(date,txt);
    }
    
    public void getDay(String date) { 
        aFaireJour=getEvent(date);
        this.fireDayEvent(new DayEvent(this));
    }
    
    public void paint(Graphics g){
        g.setFont(java.awt.Font.getFont(java.awt.Font.DIALOG));
        g.drawString("Agenda", 10, 10);
    }
    
    public HashMap<Integer, Triple> getCalendar()
    {
        return calendar;
    }
    
}
