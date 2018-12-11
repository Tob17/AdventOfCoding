import java.io.FileNotFoundException;
import java.io.IOException;

public class Tag_11_Part_2 {
		
		static int zellen[][] = new int[300][300];
		static int seriennummer = 5468;
		
		static void zellenKraftMessen(int x, int y, int zelle[][])
		{
			int kraftwert = 0;
			int zellenID = x + 10;
			
			kraftwert += zellenID;
			kraftwert = kraftwert * y;
			kraftwert += seriennummer;
			kraftwert = kraftwert * zellenID;
	    	kraftwert = ((kraftwert % 1000)/100);
			kraftwert -= 5;

			zelle[x][y] = kraftwert;
		}

		public static void main(String[] abc) throws FileNotFoundException, IOException
		  {	
	        for(int a = 0; a < 300; a++)
	        	for(int b = 0; b < 300; b++)
	        		zellenKraftMessen(a,b,zellen);
	        
	        int xBeste = 0;
	        int yBeste = 0;
	        int besteBlockBreite = 0;
	        int besterWert = 0;
	        
	       //Für alle Blockgroeßen
     	   for(int blockBreite = 1; blockBreite <= 300; blockBreite++)
   	        {
	         //Für alle Koordinaten
	         for(int a = 0; a < (300-(blockBreite-1)); a++)
	        	for(int b = 0; b < (300-(blockBreite-1)); b++)
	        	  {
	        	   int summe = 0;
	        	   
	        	   //Block zusammenzählen
	        	   for(int br = a; br < (a+blockBreite); br++)
	        		 for(int hoe = b; hoe < (b+blockBreite); hoe++)
	        		   summe += zellen[br][hoe];
	        	   
	        	   if(summe > besterWert)
	        	     {
	        		  besterWert = summe;
	        		  besteBlockBreite = blockBreite;
	        		  xBeste = a;
	        		  yBeste = b;
	        	     }   
	        	 }
   	         }
	        
	        System.out.println("Bester wert: " + besterWert + " an der Stelle " + xBeste + "," + yBeste + "," + besteBlockBreite);

		 }
}
