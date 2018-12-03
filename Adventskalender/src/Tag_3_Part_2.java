import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tag_3_Part_2 {
	
	public static void main(String[] abc)  throws FileNotFoundException, IOException
	{
		/* Variables */
		
		int inputNummer = 0;
		int xKord[] = new int[9999];
		int yKord[] = new int[9999];
		int xSize[] = new int[9999];
		int ySize[] = new int[9999];
		
		 /* Input-Stream */

		 FileReader reader;
		 reader = new FileReader("./src/Daten/input_Tag_3.txt");
		 
		 BufferedReader bufferedReader;
		 bufferedReader = new BufferedReader(reader);
		 
		 /* Input-Speicher */
		 
		 String[] fileText = new String[9999];
		 int i = 0;

		 
		 /* String-Parsing */		 
		 
		 while((fileText[i] = bufferedReader.readLine()) != null)
		 {
		  String line = fileText[i];
		  
		  line = line.replace('#', ' ');
		  line = line.replace('@', ' ');
		  line = line.replace(',', ' ');
		  line = line.replace(':', ' ');
		  line = line.replace('x', ' ');
		  	 
		  String xKordi = "";
		  String yKordi = "";
		  String xPos = "";
		  String yPos = "";
		  
		  int spalte = 0;
		  
		 for(int j = 1; j < line.length();)
		  {
			 if(spalte == 0 && line.charAt(j) != ' ')
				 j++;
			 else
			   {
				if(spalte == 0)
				  {
				  j += 3;
				  spalte = 1;
				  }
				
			    if(spalte == 1 && line.charAt(j) != ' ')
			      {
			       xKordi += line.charAt(j);
			       j++;
			      }      
			    else
			      {
			       if(spalte == 1)
			       {
			         j += 1;
			         spalte = 2;
			       }
			      
			       if(spalte == 2 && line.charAt(j) != ' ')
			         {
			    	  yKordi += line.charAt(j);
			    	  j++;
			         }
			       else
			        {
			    	 if(spalte == 2)
			    	  {
			    	   j += 2;
			    	   spalte = 3;
			    	  }
			    	 
			    	 if(spalte == 3 && line.charAt(j) != ' ')
			    	   {
			    		 xPos += line.charAt(j);
			    		 j++;
			    	   }
			    	 else
			    	   {
			    		if(spalte == 3)
			    		 {
			    		  j += 1;	
			    		  spalte = 4;
			    		 }
			    		
				    	 if(spalte == 4 && line.charAt(j) != ' ')
				    	   {
				    		 yPos += line.charAt(j);
				    		 j++;
				    	   }
				    	 else
				    		 j = line.length();
			    	   }
			        }
			      }
			   }
			 
		  }
		  xKord[inputNummer] = Integer.parseInt(xKordi);
		  yKord[inputNummer] = Integer.parseInt(yKordi);
		  xSize[inputNummer] = Integer.parseInt(xPos);
		  ySize[inputNummer] = Integer.parseInt(yPos);
		  inputNummer++;
		  i++;
		 }
		 
		 
		 
		/* Logik */
		 
		 char feld[][] = new char [1000][1000];
		 
		 boolean ueberlappt = false;
		 
		 //Init Feld
		 for(int a = 0; a < 1000; a++)
			 for(int b = 0; b < 1000; b++)
				 feld[a][b] = '.';
		 
		 
		 //Markiere felder und zaehle doppelte	 
		 for(int j = 0; j < inputNummer; j++)
		 {
			 for(int a = xKord[j]; a < xKord[j]+xSize[j]; a++)
				 for(int b = yKord[j]; b < yKord[j]+ySize[j]; b++)
				 {
					 if(feld[a][b] == 'x' || feld[a][b] == '#')
					   {
						feld[a][b] = '#';
					   }
					 else
						 feld[a][b] = 'x';
				 } 
		 }
		 
		 //Suche feld ohne ueberlappung	
		 for(int j = 0; j < inputNummer; j++)
		 {
			 ueberlappt = false;
			 for(int a = xKord[j]; a < xKord[j]+xSize[j]; a++)
				 for(int b = yKord[j]; b < yKord[j]+ySize[j]; b++)
					 if(feld[a][b] == '#')
						 ueberlappt = true;
			 
			 if(!ueberlappt)
				 System.out.println("Feld das nicht uberlappt: " + (j+1));
		 }
		 
		 
		 
	/*	 for(int a = 0; a < 1000; a++)
		 {
			 for(int b = 0; b < 1000; b++)
					 System.out.print(feld[a][b]);
			 System.out.println();
		 }*/
	 
		
	}
}