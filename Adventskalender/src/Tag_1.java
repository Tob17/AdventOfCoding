import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tag_1 {
	
	static int operationAusfuehren(String operation, int a)
	{
		if(operation.charAt(0) == '+')
			return (a + Integer.parseInt(operation.substring(1, operation.length())));
		else
			return (a - Integer.parseInt(operation.substring(1, operation.length())));
	}
	
	public static void main(String[] abc) throws FileNotFoundException, IOException
	  {	
		 int ergebnis = 0;
		 
		 FileReader reader;
		 reader = new FileReader("./src/Daten/tag1_input.txt");
		 
		 BufferedReader bufferedReader;
		 bufferedReader = new BufferedReader(reader);
		 
		 String[] fileText = new String[9999];
		 int i = 0;

		 while((fileText[i] = bufferedReader.readLine()) != null)
		 {
			ergebnis = operationAusfuehren(fileText[i], ergebnis);
			i++;
		 }
		 
		 System.out.println(ergebnis);
		 
		 bufferedReader.close();
	 } 
}
