import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tag_7_Part_1 {
	
    
    
	public static void main(String[] abc)  throws FileNotFoundException, IOException
	{
		/* Variablen */
		
		char schritteMitNachfolgern[][] = new char[26][10];
		//[26] = Schritte A,B,C...
		//[5] = Nachfolger von A,B,C...
		
		String reihenfolge = "";
		
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
		 
		 char welcheSchritteBereit[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		 
		 //Erste schritte finden
			 for(int a = 0; a < 26; a++)
				for(int b = 1; b < 10; b++)
					if(schritteMitNachfolgern[a][b] != ' ')
						welcheSchritteBereit[(byte)(schritteMitNachfolgern[a][b]-65)] = ' ';
			 
		//Schritte nach und nach duchgehen
		for(int a = 0; a < 26; a++)
		  {
		   //falls Schritt bereit ist
		   if(welcheSchritteBereit[a] != ' ')
		     {
			  reihenfolge += welcheSchritteBereit[a];
			  welcheSchritteBereit[a] = ' ';
			  
			  //alle abhängigen Schritte auf Freischaltung prüfen und aus der liste löschen
			  for(int b = 1; b < 10; b++)
			  {
			   if(schritteMitNachfolgern[a][b] != ' ')
			     {	  
				  char freigeschalteterSchritt = schritteMitNachfolgern[a][b];
			      schritteMitNachfolgern[a][b] = ' ';
			      
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
			  a= -1;  
		     }
		  }

		System.out.println(reihenfolge);	
	}
}
