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
class Triple {
    private String heure;
    private String duree;
    private String descevent;
            
    public Triple() {
        
    }
    
    public Triple(String h, String d, String desc){
        this.heure = h;
        this.duree = d;
        this.descevent = desc;
    }
    
    public void setHeure(String txt) {
        heure = txt;
    }
    
    public String getHeure() { 
        return heure;
    }
    
    public void setDuree(String txt) {
        duree = txt;
    }
    
    public String getDuree() { 
        return duree;
    }
    
    public void setDescevent(String txt) {
        descevent = txt;
    }
    
    public String getDescevent() { 
        return descevent;
    }
}
