package agenda;

/**
 *
 * @author Kaelhanas
 */

import org.jdom2.output.*;
import org.jdom2.*;
import java.io.*;
 

public abstract class JDom1 {
 

	static void affiche(Document document)
	{
	   try
	   {
	      //affichage classic avec pretty format
	      XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	      sortie.output(document, System.out);
	   }
	   catch (java.io.IOException e){}
	}

	static void enregistre(Document document, String fichier)
	{
	   try
	   {
	      //Affichage classique avec prettyformat
	      XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	      
	      sortie.output(document, new FileOutputStream(fichier));
	   }
	   catch (java.io.IOException e){}
	}
	
	
}
