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
    
    public Evenement() 
    {
        this.date = "01/01/2000";
        this.infos = new Triple("unknow","unknow","unknow" );
    }
    
    public Evenement(String str, Triple triple)
    {
        this.date=str;
        this.infos=triple;
    }
    
    public String getDate()
    {
        return this.date;
    }
    
    public void setDate(String str)
    {
        this.date = str;
    }
    
    public Triple getTriple()
    {
        return this.infos;
    }
    
    public void setTriple(Triple trp)
    {
        this.infos = trp;
    }
    
    public void setTriple(String heure, String duree, String desc)
    {
        this.infos.setHeure(heure);
        this.infos.setDuree(duree);
        this.infos.setDescevent(desc);
        
    }
    
    public String toString()
    {
    	return "(date : "+this.getDate()+", "+this.getTriple().toString()+")";
    }
}
