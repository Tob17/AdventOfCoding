import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tag_5_Part_1 {
	
	
	static String[] fileText = new String[1];

	
	static boolean destroy()
	{
		boolean destroyed = false;
		String newText = "";
		
		for(int i = 0; i < fileText[0].length()-1;)
		{
			
			if(((byte)fileText[0].charAt(i) - (byte)fileText[0].charAt(i+1)) == 32 || (byte)(fileText[0].charAt(i) - (byte)fileText[0].charAt(i+1)) == -32)
			{	
				i+=2;
				destroyed = true;
			}
			else 
			{
				newText += fileText[0].charAt(i);
				i++;
			}
			
			
			if(i == fileText[0].length()-1)
				newText += fileText[0].charAt(i);

		}

		fileText[0] = newText;
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
		 
		 while(destroy()) {System.out.println("Nächste Runde!");}
		 
		 System.out.println(fileText[0]);
		 System.out.println(fileText[0].length());
	}
}
