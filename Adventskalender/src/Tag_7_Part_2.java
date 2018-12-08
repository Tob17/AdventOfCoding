

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tag_7_Part_2 {
	
	/* variablen */

	static char schritteMitNachfolgern[][] = new char[26][10];
	//[26] = Schritte A,B,C...
	//[5] = Nachfolger von A,B,C...
	
	static String reihenfolge = "";
	
	static char welcheSchritteBereit[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	static char bearbeiteterSchritt[] = {' ', ' ', ' ', ' ', ' '};
	static boolean arbeiterBereit[] = {true,true,true,true,true};	
	static int arbeitsZeiten[] = {0,0,0,0,0};
	
	static int sekundenZaehler = 0;
	
	
	static void pruefeNachfolger(int abgearbeiteterSchritt)
	{
		  //alle abhängigen Schritte auf Freischaltung prüfen und aus der liste löschen
		  for(int b = 1; b < 10; b++)
		  {
		   if(schritteMitNachfolgern[abgearbeiteterSchritt][b] != ' ')
		     {	  
			  char freigeschalteterSchritt = schritteMitNachfolgern[abgearbeiteterSchritt][b];
		      schritteMitNachfolgern[abgearbeiteterSchritt][b] = ' ';
		      
		      //Hat der Schritt keine Vorgänger mehr?
		      boolean freigeschaltet = true;
		    
			  for(int c = 0; c < 26; c++)
			    for(int d = 1; d < 10; d++)
			      if(schritteMitNachfolgern[c][d] == freigeschalteterSchritt)
			    	  freigeschaltet = false;
			  
			  if(freigeschaltet)
			    {welcheSchritteBereit[(byte)(freigeschalteterSchritt-65)] = freigeschalteterSchritt;}
		     }
		  } 
	}
	
	static boolean weiseArbeiterZu(char zuBearbeitenderSchritt)
	{
		for(int a = 0; a < 5; a++)
			if(arbeiterBereit[a])
			{
				arbeiterBereit[a] = false;
				arbeitsZeiten[a] = 60 + (byte)(zuBearbeitenderSchritt - 64);
				bearbeiteterSchritt[a] = zuBearbeitenderSchritt;
				return true;
			}
		return false;			
	}
	
	static boolean arbeiterFrei()
	{
		for(int a = 0; a < 5; a++)
			if(arbeiterBereit[a])
			   return true;
		
		return false;
	}
	
	static boolean alleFertig()
	{
		for(int a = 0; a < 5; a++)
			if(!arbeiterBereit[a])
			   return false;
		
		return true;
	}
	
	static boolean mindestensEinerBeschaefftigt()
	{
		for(int a = 0; a < 5; a++)
			if(!arbeiterBereit[a])
				return true;
		return false;
	}
	
	static void arbeiten()
	{
		for(int a = 0; a < 5; a++)
			if(!arbeiterBereit[a])
			   arbeitsZeiten[a]--;
		
		sekundenZaehler++;

		for(int a = 0; a < 5; a++)
			if(arbeitsZeiten[a] <= 0 && !arbeiterBereit[a])
			{
				arbeiterBereit[a] = true;
				arbeitsZeiten[a] = 0;
				pruefeNachfolger((int)(bearbeiteterSchritt[a]-65));
				reihenfolge += bearbeiteterSchritt[a];
				bearbeiteterSchritt[a] = ' ';
			}

		
	}
	
	static boolean schritteListeLeer()
	{
		for(int a = 0; a < 26; a++)
			if(welcheSchritteBereit[a] != ' ')
				return false;
		
		return true;
	}
	
	
	public static void main(String[] abc)  throws FileNotFoundException, IOException
	{
		
		for(int a = 0; a < 26; a++)
			for(int b = 0; b < 10; b++)
				schritteMitNachfolgern[a][b] = ' ';
		
		for(int a = 0; a < 26; a++)
			schritteMitNachfolgern[a][0] = (char) ('A' + a);
		
		 /* Inputstream */
		 
		 FileReader reader;
		 reader = new FileReader("./src/Daten/Tag_7_input.txt");
		 
		 BufferedReader bufferedReader;
		 bufferedReader = new BufferedReader(reader);
		 
		 
		 /* Parsing input */
		 
		 String[] fileText = new String[200];
		 int i = 0;

		 //Schreibe die Nachfolger an die Stelle im Vektor, an der sich deren Vorgänger befindet!
		 while((fileText[i] = bufferedReader.readLine()) != null)
		 {
			 boolean wertGeschrieben = false;
			 for(int a = 1; a < 10; a++)
				 if(schritteMitNachfolgern[(byte)(fileText[i].charAt(5)-65)][a] == ' ' && !wertGeschrieben)
				 {
					 schritteMitNachfolgern[(byte)(fileText[i].charAt(5)-65)][a] = fileText[i].charAt(36);
					 wertGeschrieben = true;
				 }
			 i++;
	     }
		 
		 /* Reihenfolge bestimmen (ohne umständliche liste) */
		 
		 //Erste schritte finden
			 for(int a = 0; a < 26; a++)
				for(int b = 1; b < 10; b++)
					if(schritteMitNachfolgern[a][b] != ' ')
						welcheSchritteBereit[(byte)(schritteMitNachfolgern[a][b]-65)] = ' ';
			 
		//Schritte nach und nach duchgehen
	    boolean hatGearbeitet = false;
	    
		for(int a = 0; a < 26; a++)
		  {
			while(!arbeiterFrei()) 
			  {
				arbeiten();
				hatGearbeitet = true;
			  }
			
			while(schritteListeLeer() && mindestensEinerBeschaefftigt())
			  {
				arbeiten();
				hatGearbeitet = true;
			  }
			
		   //falls Schritt bereit ist
		   if(welcheSchritteBereit[a] != ' ' && !hatGearbeitet)
		     {
              if(!weiseArbeiterZu(welcheSchritteBereit[a])) {System.out.println("Fehler bei der Zuweisung!");}
			   
			  welcheSchritteBereit[a] = ' ';
  
			  a= -1; 
		     }
		   
		   if(hatGearbeitet)
		   {
			   a = -1;
			   hatGearbeitet = false;
		   }

		 }
		  

		System.out.println(reihenfolge);
		//LAPFCRGHVZOTKWENBXIMSUDJQY (ohne arbeiter)
		//LRVAGPZHFOTCKWENBXIMSUDJQY (mit arbeiter)
		System.out.println(sekundenZaehler);
		

	}
	
}
