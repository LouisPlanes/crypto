/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.io.IOException;
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
import org.jdom2.JDOMException;
/**
 *
 * @author Kaelhanas
 */
public class Main {
    
    public static void main(String[] args) throws JDOMException, IOException
    {
        
        Agenda2 agenda = new Agenda2();
        
        Evenement event1 = new Evenement();
        Triple info1 = new Triple("08:00", "02:00", "event numero 1");
        Triple info2 = new Triple("08:00", "02:00", "event numero 2");
        event1.setDate("01/01/2018");
        event1.setTriple(info1);
        System.out.println(event1);
        
        //int i = agenda.transform("01/01/2018");
        //System.out.println("\n"+i);
        
        
        agenda.addEvent(event1);
        agenda.addEvent("02/02/2018", info2);
        List<Triple> tripleList = agenda.getTripleList("01/01/2018");
        System.out.println(tripleList);
        Evenement event1_1 = agenda.getEvent("01/01/2018", "08:00");
        System.out.println(event1_1);
        
        /* Test OK
        agenda.clearCalendar();
        List<Triple> tripleList2 = agenda.getTripleList("01/01/2018");
        System.out.println("\nafter clear : "+tripleList2);
        */
        
        //  Creation d'un nouvel agenda pour rechargement depuis un autre agenda
        
        Agenda2 agenda2 = new Agenda2();
        
        Evenement event11 = new Evenement();
        Triple info11 = new Triple("08:00", "02:00", "event numero 11");
        Triple info22 = new Triple("08:00", "02:00", "event numero 22");
        event11.setDate("01/01/2018");
        event11.setTriple(info11);
        System.out.println(event11);
        agenda2.addEvent(event11);
        agenda2.addEvent("02/02/2018", info22);
        List<Triple> tripleList2 = agenda2.getTripleList("01/01/2018");
        System.out.println(tripleList2);
        Evenement event1_2 = agenda2.getEvent("01/01/2018", "08:00");
        System.out.println(event1_2);
        
        //System.out.println(agenda.printCalendar());
        String str = agenda2.toString();
        System.out.println("\nagenda XML sous string pour encryptage : \n\n"+str);
        
        
        System.out.println("\nAncien Calendar : \n"+agenda.printCalendar());
        agenda.clearCalendar();
        System.out.println("\nNettoyage Calendar");
        agenda.reloadCalendar(str);
        System.out.println("\nChargement du nouveau Calendar");
        System.out.println("\nAffichage du nouveau Calendar");
        System.out.println(agenda.printCalendar());

    }
}
