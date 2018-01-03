/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.util.HashMap;

/**
 *
 * @author Kaelhanas
 */
public class DataBase {
    
    private HashMap<Integer, Couple> map = new HashMap();
    
    
    public DataBase() {}
    
    public void addUser(Couple usr)
    {
        
    }
    
    // Recherche de l'ID correspondant a username = usr
    // et tu renvoie le couple
    public Couple getUser(String user)
    {
        return null;
    }
    
    // Apres tu rajoute les methodes dont tu as besoins
    
    public String toString()
    {
        return map.toString();
    }
}
