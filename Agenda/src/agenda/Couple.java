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
public class Couple {
    private String user;
    private String password;
            
    public Couple() {
        
    }
    
    public Couple(String usr, String psw){
        this.user = usr;
        this.password = psw;

    }
    
    public void setUser(String str) {
        user = str;
    }
    
    public String getUser() { 
        return user;
    }
    
    public void setPsw(String str) {
        password= str;
    }
    
    public String getPsw() { 
        return password;
    }
    
    
    public String toString()
    {
    	return "(User : "+this.getUser()+", Password : "+this.getPsw()+")";
    }
}
