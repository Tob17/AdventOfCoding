import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class Tag_1_Part2 {
	
	static int wertSchonErhaltenListe[] = new int[1000000];
	
	static int operationAusfuehren(String operation, int a)
	{
		if(operation.charAt(0) == '+')
			return (a + Integer.parseInt(operation.substring(1, operation.length())));
		else
			return (a - Integer.parseInt(operation.substring(1, operation.length())));
	}
	
	static void initListe()
	{
		for(int i = 0; i < wertSchonErhaltenListe.length; i++)
			wertSchonErhaltenListe[i] = 0;
	}
	
	static boolean aufDoppeltenWertTesten(int testWert)
	{
		if(testWert < 0)
			testWert = 100000 + testWert + -2*testWert;
		if(wertSchonErhaltenListe[testWert] == 1)
				return true;
		
		return false;
	}
	
	public static void main(String[] abc) throws FileNotFoundException, IOException
	  {			 
		 int ergebnis = 0;
		 wertSchonErhaltenListe[0] = 1;	 
		 initListe();

		 
		 boolean doppeltesErgebniserhalten = false;
		 
		 
		 /* Inputstream */
		 
		 FileReader reader;
		 reader = new FileReader("./src/Daten/tag1_input.txt");
		 
		 BufferedReader bufferedReader;
		 bufferedReader = new BufferedReader(reader);
		 
		 
		 /* Parsing input */
		 
		 String[] fileText = new String[9999];
		 int i = 0;

		 while((fileText[i] = bufferedReader.readLine()) != null)
		 {i++;}
		 
		 String operands[] = new String[i];
		 
		 for(int j = 0; j < i; j++)
			 operands[j] = fileText[j];
		 
		 
		 /* Execution */
		 
		 while(!doppeltesErgebniserhalten)
		 {
			 for(int j = 0; j < operands.length; j++)
			 {
				 ergebnis = operationAusfuehren(operands[j], ergebnis);
				 doppeltesErgebniserhalten = aufDoppeltenWertTesten(ergebnis);
				 
				 if(doppeltesErgebniserhalten)
					 j = operands.length;
				 
				 if(ergebnis < 0)
				 wertSchonErhaltenListe[(100000 + ergebnis + -2*ergebnis)] = 1;
				 else
					 wertSchonErhaltenListe[ergebnis] = 1;
			 }
		 }
		 

		 System.out.println(ergebnis);
		 bufferedReader.close();
	 } 
}

