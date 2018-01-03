/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.KeySpec;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
/**
 *
 * @author Kaelhanas
 */
public class Main {
    
    public static void main(String[] args)
    {
        
        Agenda2 agenda = new Agenda2();
        
        Evenement event1 = new Evenement();
        Triple info1 = new Triple("08:00", "02:00", "event numero 1");
        event1.setDate("01/01/2018");
        event1.setTriple(info1);
        System.out.println(event1);
        
        //int i = agenda.transform("01/01/2018");
        //System.out.println("\n"+i);
        
        agenda.addEvent(event1);
        agenda.addEvent("01/01/2018", info1);
        List<Triple> tripleList = agenda.getTripleList("01/01/2018");
        System.out.println(tripleList);
        Evenement event1_1 = agenda.getEvent("01/01/2018", "08:00");
        System.out.println(event1_1);
        
        agenda.clearCalendar();
        List<Triple> tripleList2 = agenda.getTripleList("01/01/2018");
        System.out.println("\nafter clear : "+tripleList2);
        
    }
}
