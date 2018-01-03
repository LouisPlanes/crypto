/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

/**
 *
 * @author Khoob
 */
public class Triple {
    private String heure;
    private String duree;
    private String descevent;
            
    /**
     * Constructeur pas defaut
     */
    public Triple() {
        
    }
    
    /**
     * constructeur valué
     * @param h : event's starting time
     * @param d : event's duration
     * @param desc : event's description
     */
    public Triple(String h, String d, String desc){
        this.heure = h;
        this.duree = d;
        this.descevent = desc;
    }
    
    /**
     *
     * @param txt : new event's starting time
     */
    public void setHeure(String txt) {
        heure = txt;
    }
    
    /**
     *
     * @return the starting time of the event
     */
    public String getHeure() { 
        return heure;
    }
    
    /**
     *
     * @param txt : new event's duration
     */
    public void setDuree(String txt) {
        duree = txt;
    }
    
    /**
     *
     * @return the event's duration
     */
    public String getDuree() { 
        return duree;
    }
    
    /**
     *
     * @param txt : new event's description
     */
    public void setDescevent(String txt) {
        descevent = txt;
    }
    
    /**
     *
     * @return the event's description
     */
    public String getDescevent() { 
        return descevent;
    }
    
    public String toString()
    {
    	return "heure : "+this.getHeure()+", duree : "+this.getDuree()+", description : "+this.getDescevent();
    }
}