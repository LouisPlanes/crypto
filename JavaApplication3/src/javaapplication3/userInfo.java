/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

/**
 *
 * @author Thomas
 */
public class userInfo{
    private String userID;
    private String userPassword;
        
    public userInfo(String id,String password){
        this.userID=id;
        this.userPassword=password;
    }
        
    public void setID(String id){
        this.userID=id;
    }
        
    public String getID(){
        return this.userID;
    }
        
    public void setPassword(String password){
        this.userPassword=password;
    }
        
    public String getPassword(){
        return this.userPassword;
    }
        
    @Override
    public String toString()
    {/*   public String toString()
        */
        return "(User : "+this.getID()+", Password : "+this.getPassword()+")";
    }
}
