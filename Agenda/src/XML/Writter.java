package XML;


/**
 *
 * @author Kaelhanas
 */

import org.jdom2.output.*;
import org.jdom2.*;
import java.io.*;
import java.io.StringReader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Writter extends JDom1
{
    //Racine du XML
		static Element racine = new Element("data");
		
		//Création d'un nouveau document JDOM basé sur la racine précédement creée
		static org.jdom2.Document document = new Document(racine);
		
		 static String events[][] = {   { "01/01/2018", "08h00", "04h00", "Reunion"},
						{ "01/01/2018", "12h00", "02h00", "Repas"},
                                                { "01/01/2018", "14h00", "04h00", "Reunion2"}
                                            };
		

	
	public static void main(String[] args) throws JDOMException, IOException
	{
		Element info = new Element("Info");
		racine.addContent(info);
		
		Element entrees = new Element("Entrees");
		racine.addContent(entrees);
		
		
		//
		// Ecriture dans le XML de chaque event contenu dans l'Array 
		//		
		for(int i=0; i<events.length; i++)
		{
			entrees.addContent(new Element("event")
						.setAttribute(new Attribute("duree", events[i][2]))
						.setAttribute(new Attribute("heure",events[i][1]))
						.setAttribute(new Attribute("date",events[i][0]))
						.addContent(new Element("details")
								.setText(events[i][3]))

					);
			
		}
		
		SAXBuilder builder = new SAXBuilder();
                XMLOutputter sortie = new XMLOutputter(Format.getCompactFormat());
		String docXML = sortie.outputString(document);
                         
		System.out.println("Génération du Document XML\n");
		affiche(document);
		//enregistre(document, "String.xml");
		System.out.println("\nDoc XML transformé en String pour encryptage\n");
                System.out.println(docXML);
                
                System.out.println("\n\n Veuillez patienter, Louis Crypte et Decrypte...\n\n");
        
                InputStream stream = new ByteArrayInputStream(docXML.getBytes("UTF-8"));
                Document anotherDocument = builder.build(stream);
                System.out.println("Réécriture du XML après décryptage\n");
                affiche(anotherDocument);        
                
	}
	
}
