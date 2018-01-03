/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

/**
 *
 * @author Kaelhanas
 */
public class Evenement {
    
    private String date;
    private Triple infos;
    
    /**
     * Constructeur par défault
     */
    public Evenement() 
    {
        this.date = "01/01/2000";
        this.infos = new Triple("unknow","unknow","unknow" );
    }
    
    /**
     * Constructeur valué
     * @param str : event date
     * @param triple : Triple containing event informations
     */
    public Evenement(String str, Triple triple)
    {
        this.date=str;
        this.infos=triple;
    }
    
    /**
     *
     * @return the event's date
     */
    public String getDate()
    {
        return this.date;
    }
    
    /**
     *
     * @param str : set the date with the vallue str
     */
    public void setDate(String str)
    {
        this.date = str;
    }
    
    /**
     *
     * @return event's Triple
     */
    public Triple getTriple()
    {
        return this.infos;
    }
    
    /**
     *
     * @param trp set the event's Triple to str
     */
    public void setTriple(Triple trp)
    {
        this.infos = trp;
    }
    
    /**
     * Overloading of setTriple
     * @param heure
     * @param duree
     * @param desc
     */
    public void setTriple(String heure, String duree, String desc)
    {
        this.infos.setHeure(heure);
        this.infos.setDuree(duree);
        this.infos.setDescevent(desc);
        
    }
    
    public String toString()
    {
    	return "(date : "+this.getDate()+", "+this.getTriple().toString()+")\n";
    }
}