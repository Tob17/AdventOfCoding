import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tag_2_Part2 {
	
	public static void main(String[] abc) throws FileNotFoundException, IOException
	  {		 
		 String zielBox_1 = "keine Box gefunden!";
		 String zielBox_2 =  "keine Box gefunden!";
		 String zielSequenz = "keine Box gefunden!";
		
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
		 
		 
		 /* Werte suchen, die sich um 1 unterscheiden O(n * log(n)) */
		 
		 int unterschiede = 0;
		 
		 for(int a = 0; a < (i-1); a++)
			 for(int b = 1; b < i; b++)
			 {
		        for(int c = 0; c < fileText[a].length(); c++)
		        	if(fileText[a].charAt(c) != fileText[b].charAt(c))
		        		unterschiede++;
		        
		        if(unterschiede == 1)
		          {
		        	zielBox_1 = fileText[a];
		        	zielBox_2 = fileText[b];
		        	a = (i-1);
		        	b = i;
		          }
		        unterschiede = 0;
			 }
		 
		 
		 /* Unterschied löschen */
		 
		 for(int a = 0; a < zielBox_2.length(); a++)
			 if(zielBox_1.charAt(a) != zielBox_2.charAt(a))
				 zielSequenz = zielBox_1.replace(zielBox_1.charAt(a), ' ');
		 
		 System.out.println(zielBox_1);
		 System.out.println(zielBox_2);
		 System.out.println(zielSequenz);

	 } 
}