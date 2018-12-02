/*      By Tob17          *
 * Identifiers in german! */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tag_2_Part1 {
	
	static int buchstabenHashVektor[] = new int[26];
	//Ascii/UTF-8: a = 97
    //Ascii/UTF-8: z = 122
	
	static void initHashVektor()
	  {
	   for(int i = 0; i < buchstabenHashVektor.length; i++)
		   buchstabenHashVektor[i] = 0;
	  }
	
	static void schreibeInHashVektor(char buchstabe)
	  {buchstabenHashVektor[(buchstabe-97)]++;}
	
	static int zaehleDoppelte()
	  {
		int ergebnis = 0;
		for(int i = 0; i < buchstabenHashVektor.length; i++)
		   if(buchstabenHashVektor[i] == 2)
		     ergebnis++;
		return ergebnis;
	  }
	
	static int zaehleDreifache()
	  {
		int ergebnis = 0;
		for(int i = 0; i < buchstabenHashVektor.length; i++)
		   if(buchstabenHashVektor[i] == 3)
		     ergebnis++;
		return ergebnis;
	  }
	

	public static void main(String[] abc) throws FileNotFoundException, IOException
	  {	
		 int zaehlerDoppelte = 0;
		 int zaehlerDreifache = 0;
		 initHashVektor();
		 
		 /* Input-Stream */

		 FileReader reader;
		 reader = new FileReader("./src/Daten/input_tag2.txt");
		 
		 BufferedReader bufferedReader;
		 bufferedReader = new BufferedReader(reader);
		 
		 
		 /* Input-Speicher */
		 
		 String[] fileText = new String[9999];
		 int i = 0;

		 while((fileText[i] = bufferedReader.readLine()) != null)
		 {i++;}
		 
		 
		 /* Logik */
		 
		 i = 0;
		 
		 while(fileText[i] != null)
		   {
			for(int j = 0; j < fileText[i].length(); j++)
				schreibeInHashVektor(fileText[i].charAt(j));
			
			if(zaehleDoppelte() > 0)
			  zaehlerDoppelte++;
			if(zaehleDreifache() > 0)
			  zaehlerDreifache++;

			initHashVektor();
		    i++;
		   }
		 
		 System.out.println(zaehlerDoppelte * zaehlerDreifache); 
	 } 
}