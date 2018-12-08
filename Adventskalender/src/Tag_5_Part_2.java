import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tag_5_Part_2 {
	
	
	static String[] fileText = new String[26];

	
	static void eraseAllUnits(int probe, char buchstabe, char buchstabe_gross)
	{
		String newText = "";
		
		fileText[probe] = fileText[probe].replace(buchstabe, ' ');
		fileText[probe] = fileText[probe].replace(buchstabe_gross, ' ');
		
		for(int i = 0; i < fileText[probe].length(); i++)
			if(fileText[probe].charAt(i) != ' ')
				newText += fileText[probe].charAt(i);
		
		fileText[probe] = newText;
	}
	
	
	static boolean destroy(int probe)
	{
		boolean destroyed = false;
		String newText = "";
		
		for(int i = 0; i < fileText[probe].length()-1;)
		{
			
			if(((byte)fileText[probe].charAt(i) - (byte)fileText[probe].charAt(i+1)) == 32 || (byte)(fileText[probe].charAt(i) - (byte)fileText[probe].charAt(i+1)) == -32)
			{	
				i+=2;
				destroyed = true;
			}
			else 
			{
				newText += fileText[probe].charAt(i);
				i++;
			}
			
			
			if(i == fileText[probe].length()-1)
				newText += fileText[probe].charAt(i);

		}

		fileText[probe] = newText;
		return destroyed;
	}
	
	public static void main(String[] abc)  throws FileNotFoundException, IOException
	{
		
		 /* Input-Stream */

		 FileReader reader;
		 reader = new FileReader("./src/Daten/Tag_5_input.txt");
		 
		 BufferedReader bufferedReader;
		 bufferedReader = new BufferedReader(reader);
		 
		 /* Input-Speicher */
		 
		 fileText[0] = bufferedReader.readLine();
		 
		 //Strings kopieren, je eine Probe für jeden Buchstaben
		 for(int i = 1; i < 26; i++)
		 {
			 fileText[i] = "" + fileText[0];
		 }
		 
		 //Löschen von Buchstaben in jedem der 26 Strings
		 for(int i = 0; i < 26; i++)
		 {
			 System.out.println("Lösche alle " + (char)(i+65));
			 eraseAllUnits(i, (char)(i+65), (char)(i+97));
		 }
		 
		 //Jetzt das gleiche wie bei Aufgabe 1
		 for(int i = 0; i < 26; i++)
		 {
		  System.out.println("Starte mit Buchstabe " + (char)(i+65));
		  System.out.println("Warten...");
		  while(destroy(i)) {}
		  System.out.println();
		  System.out.println("Text: " + fileText[i]);
		  System.out.println("Länge: " + fileText[i].length());	
		  }
	 

	}
}
