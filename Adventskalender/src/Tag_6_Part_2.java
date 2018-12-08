import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tag_6_Part_2 {

	public static void main(String[] abc)  throws FileNotFoundException, IOException
	{
		 /* Variablen */
		
		 int xKord[];
		 int yKord[];
		 int anzahlFelderMitKleinemAbstandZuAllenPunkten = 0;
		 int feldgroesse = 5000;
		
		 
		 /* Inputstream */
		 
		 FileReader reader;
		 reader = new FileReader("./src/Daten/Tag_6_input.txt");
		 
		 BufferedReader bufferedReader;
		 bufferedReader = new BufferedReader(reader);
		 
		 
		 /* Parsing input */
		 
		 String[] fileText = new String[200];
		 int i = 0;

		 while((fileText[i] = bufferedReader.readLine()) != null)
		 {i++;}
		 
		 xKord = new int[i];
		 yKord = new int[i];
		 
		 for(int j = 0; j < i; j++)
		 {
		  int a = 0;
		  
		  String x = "";
		  String y = "";
		  
		  while(fileText[j].charAt(a) != ' ' && fileText[j].charAt(a) != ',')
		  {
			  x += fileText[j].charAt(a);
			  a++;
		  }
		  
		  a+=2;
		  
		  while(a < fileText[j].length())
		  {
			  y += fileText[j].charAt(a);
			  a++;
		  }
			  
		  xKord[j] = Integer.parseInt(x);
		  yKord[j] = Integer.parseInt(y);
		 }
		 
		 
		 
		 /* Logik */
		 
		 int aktuelleDistanzSumme = 0;
		 int xDis = 0;
		 int yDis = 0;
		 
		 //Rechnet den Abstand jedes Punktes im Feld von allen Koordinaten aus!
		 for(int x = -feldgroesse; x < feldgroesse; x++)
			 for(int y = -feldgroesse; y < feldgroesse; y++)
			 {
				 //Punkt mit dem kleinsten Abstand suchen
				 for(int d = 0; d < xKord.length; d++)
				 {
					 xDis = (x - xKord[d]);
					 yDis = (y - yKord[d]);

					 if(xDis < 0)
						 xDis = (xDis * (-1));
					 if(yDis < 0)
						 yDis = (yDis * (-1));
					 
					 aktuelleDistanzSumme += (xDis+yDis);
				 }
				 
				 if(aktuelleDistanzSumme < 10000)
					 anzahlFelderMitKleinemAbstandZuAllenPunkten++;

				 aktuelleDistanzSumme = 0;
			 }
		 

		System.out.println("Anzahl an Felder Nahe an allen Punkten: " +  anzahlFelderMitKleinemAbstandZuAllenPunkten);
	}
	
}
