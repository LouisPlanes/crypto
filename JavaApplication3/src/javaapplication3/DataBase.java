/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.util.HashMap;

/**
 *
 * @author Thomas
 */
public class DataBase {
    
    private HashMap<Integer, userInfo> map;


    public DataBase() {
        this.map = new HashMap();
    }

    public void addUser(userInfo usr)
    {
        map.put(map.size(), usr);
    }

    // Recherche de l'ID correspondant a username = usr
    // et renvoit le couple
    public userInfo getUser(String user)
    {
        for(int i=0;i<map.size();i++){
            if(map.get(i).getID().equals(user)){
                return map.get(i);
            }
        }
        return null;
    }
        
    public void setData(HashMap<Integer,userInfo> map){
        this.map=map;
    }
        
    public HashMap<Integer,userInfo> getData(){
        return this.map;
    }

    @Override
    public String toString()
    {
        return map.toString();
    }
}
