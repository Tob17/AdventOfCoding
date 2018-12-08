import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tag_8_Part_2 {
	
	
	/*  Variablen (global) */
	
	static int inputWerte[] = new int[100000];
	static int inputZaehler = 0;
	
	
	/* Summenzähler */
	
	static int summe = 0;
	
	
	
	
	
	/* >>Element-Einträge<< */ 
	
	int anzahlKinder;
	int anzahlMetaWerte;
	
	Tag_8_Part_2 kinder[];
	
	int metaWerte[];
	
	
	
	
	/* Werte in die Elemente eintragen */
	
	static int werteEintragen(Tag_8_Part_2 aktuellerKnoten, int werteZaehler)
	{
		aktuellerKnoten.anzahlKinder = inputWerte[werteZaehler];
		
		if(aktuellerKnoten.anzahlKinder > 0)
			aktuellerKnoten.kinder = new Tag_8_Part_2[aktuellerKnoten.anzahlKinder];
		
		werteZaehler++;
		
		aktuellerKnoten.anzahlMetaWerte = inputWerte[werteZaehler];
		
		if(aktuellerKnoten.anzahlMetaWerte > 0)
			aktuellerKnoten.metaWerte = new int[aktuellerKnoten.anzahlMetaWerte];
			
		werteZaehler++;
		
		return werteZaehler;
	}
	
	
	
	/* Knoten rekursiv durchgehen */
	
	static int trageWerteInBaumEin(Tag_8_Part_2  aktuellerKnoten, int werteZaehler)
	{
		if(werteZaehler <= inputZaehler)
		  {
		   werteZaehler = werteEintragen(aktuellerKnoten, werteZaehler);
		   
		   if(aktuellerKnoten.kinder != null)
			   {		 
				for(int k = 0; k < aktuellerKnoten.kinder.length; k++)
				  {aktuellerKnoten.kinder[k] = new Tag_8_Part_2();
				   werteZaehler = trageWerteInBaumEin(aktuellerKnoten.kinder[k], werteZaehler);}
			   }
		   if(aktuellerKnoten.metaWerte != null)
				  {
				   for(int m = 0; m < aktuellerKnoten.anzahlMetaWerte; m++)
				     {aktuellerKnoten.metaWerte[m] = inputWerte[werteZaehler];
			          werteZaehler++;}
				   return werteZaehler;
			      }   		   
		  }
		return werteZaehler;
	}
	
	
	
	/* Baum ausgeben */
	
	static void gebeBaumAus(Tag_8_Part_2 aktuellerKnoten)
	{
		if(aktuellerKnoten.kinder == null)
		  for(int i = 0; i < aktuellerKnoten.metaWerte.length; i++)
	        summe += aktuellerKnoten.metaWerte[i];
		
		else if(aktuellerKnoten.kinder != null && aktuellerKnoten.metaWerte != null)
		  for(int i = 0; i < aktuellerKnoten.metaWerte.length; i++)
		    {
		     if((aktuellerKnoten.metaWerte[i]-1) < aktuellerKnoten.kinder.length)
		    	gebeBaumAus(aktuellerKnoten.kinder[aktuellerKnoten.metaWerte[i]-1]);
		     else
		       summe += 0;
		    }
	}
	
	
	
	public static void main(String[] abc) throws FileNotFoundException, IOException
	{
		
		    /* Baum */
		
		Tag_8_Part_2 aktuellerKnoten = new Tag_8_Part_2();
		
		
		 /* Inputstream */
		 
		 FileReader reader;
		 reader = new FileReader("./src/Daten/Tag_8_input.txt");
		 
		 BufferedReader bufferedReader;
		 bufferedReader = new BufferedReader(reader);
		 
		 
		 /* Parsing input */
		 
		 String[] fileText = new String[1];

		 fileText[0] = bufferedReader.readLine();
		 
		 for(int a = 0; a < fileText[0].length(); /*LEER*/)
		    {
			 String wert = "";
			 
			 if(fileText[0].charAt(a) == ' ')
				 a++;
			 else
				 {
				  while(a < fileText[0].length() && fileText[0].charAt(a) != ' ')
				    {wert += fileText[0].charAt(a);
					 a++;}
				  inputWerte[inputZaehler] = Integer.parseInt(wert);
				  inputZaehler++;
		 		 }
		   }
		 
		 
		 /* Baum aufbauen */

		 System.out.println("Vorhandene Werte:" + inputZaehler);	 
		 System.out.println("Eingetragene Werte:" + trageWerteInBaumEin(aktuellerKnoten, 0));
	 
		 /* Baum ausgeben */
		 
		 gebeBaumAus(aktuellerKnoten);
		 
		 /* Summe ausgeben */
		 
		 System.out.println(summe);
	}

}
