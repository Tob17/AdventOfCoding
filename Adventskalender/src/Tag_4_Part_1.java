import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tag_4_Part_1 {
	
	
	static void doubleBubbleSort(long[] zeiten, String[] aktionen)
	{
		for(int i = 0; i < zeiten.length-1; i++)
		{
			if(zeiten[i] > zeiten[i+1])
			{
				long temp = zeiten[i];
				zeiten[i] = zeiten[i+1];
				zeiten[i+1] = temp;
				
				String temp2 = aktionen[i];
				aktionen[i] = aktionen[i+1];
				aktionen[i+1] = temp2;
				
				i = -1;
			}
		}
	}
	
	static int[][] schlafZeitEintragen(int start, int ende, int wachenID, int[][] wachenUndMinuten)
	{
		for(int i = start; i < ende; i++)
			wachenUndMinuten[wachenID][i]++;
		
		return wachenUndMinuten;
	}
	
	public static void main(String[] abc)  throws FileNotFoundException, IOException
	{
		/* Variablen */
		
		long zeitInMinuten[];
		String aktionen[];
		
		 /* Input-Stream */

		 FileReader reader;
		 reader = new FileReader("./src/Daten/Tag_4_input.txt");
		 
		 BufferedReader bufferedReader;
		 bufferedReader = new BufferedReader(reader);
		 
		 /* Input-Speicher */
		 
		 String[] fileText = new String[9999];
		 int i = 0;

		 
		 /* String-Parsing */		 
		 
		 while((fileText[i] = bufferedReader.readLine()) != null )
		 {i++;}
		 
		 zeitInMinuten = new long[i];
		 aktionen = new String[i];
		 
		 for(int j = 0; j < i; j++)
		 {
			  zeitInMinuten[j] = Integer.parseInt(fileText[j].substring(6, 8)) * 43200 + Integer.parseInt(fileText[j].substring(9, 11)) * 1440 + Integer.parseInt(fileText[j].substring(12, 14)) * 60 + Integer.parseInt(fileText[j].substring(15, 17));
			  aktionen[j] = fileText[j];
		 }
		 
		 
		 /* Werte und aktionen sortieren */
		 
		 doubleBubbleSort(zeitInMinuten, aktionen);
		 
		 
		 /* Wachen-Variablen */
		 
		 int wachenIDMitWachMinuten[][] = new int[9999][60];
		 
		 
		 /* Schlafeminuten eintragen */
		 
		 int wachenID = -1;
		 int schlafStartZeit = -1;
		 int schlafEndeZeit = -1;
		 
		 for(int j = 0; j < zeitInMinuten.length; j++)
		 {
			 System.out.println(aktionen[j]);
			 
			 //Neue Wache faengt Schicht an
			 if(aktionen[j].substring(19, 20).equals("G"))
			 {
				 if(!aktionen[j].substring(29, 30).equals(" "))
					wachenID = Integer.parseInt(aktionen[j].substring(26, 30));
				 else
					 wachenID = Integer.parseInt(aktionen[j].substring(26, 29));
			 }

			 //Wache schlaeft ein
			 if(aktionen[j].substring(19, 20).equals("f"))
				schlafStartZeit = Integer.parseInt(aktionen[j].substring(15, 17));
			 
			 //Wache wacht auf
			 if(aktionen[j].substring(19, 20).equals("w"))
				 //falls Wache vorher eingeschlafen war
				 if(schlafStartZeit != -1)
				 {
					 schlafEndeZeit = Integer.parseInt(aktionen[j].substring(15, 17));
					 System.out.println("Wache "+wachenID+ " schlaeft von " +schlafStartZeit+ " bis "+schlafEndeZeit);
					 wachenIDMitWachMinuten = schlafZeitEintragen(schlafStartZeit, schlafEndeZeit, wachenID, wachenIDMitWachMinuten);
					 schlafStartZeit = -1;
					 schlafEndeZeit = -1;
				 } 
		 }
		
		 
		 /* Wache mit laengsten Schlafminuten finden */
		 	 
		 int laengsteSchlafMinuten = -1;
		 int minutenZaehler = 0;
		 int schlafMuetzenWache = -1;

		 
		 for(int a = 0; a < 9999; a++)
		 {
			 for(int b = 0; b < 60; b++)
              minutenZaehler += wachenIDMitWachMinuten[a][b];

			 if(laengsteSchlafMinuten < minutenZaehler)
			 {
				 schlafMuetzenWache = a;
				 laengsteSchlafMinuten = minutenZaehler;
			 }

			 minutenZaehler = 0;
		 }

		 
		 /* Laengste Schlafminute der Schlafmuetze suchen */
		 
		 int laengstePennzeit = -1;
		 int laengstePennMinute = -1;
		 
		 for(int b = 0; b < 60; b++)
		 {
			if(wachenIDMitWachMinuten[schlafMuetzenWache][b] >= laengstePennzeit) 
			{
				laengstePennzeit = wachenIDMitWachMinuten[schlafMuetzenWache][b];
				laengstePennMinute = b;
				
			}
			System.out.println(wachenIDMitWachMinuten[schlafMuetzenWache][b]);
		 }
		 
		 
		 System.out.println("======================================================================");
		 System.out.println("Wache " + schlafMuetzenWache + " hat zur Minute " + laengstePennMinute + " " + laengstePennzeit + " Minuten geschlafen!");
		 System.out.println("======================================================================");	
		 
		 System.out.println("Lösung: " + (schlafMuetzenWache*laengstePennMinute));

	}
}


