import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tag_10_Part_1 {
	
	
	 /* Zeichen mit Geschwindigkeiten */
	
	 static int xPos[];
	 static int yPos[];
	 static double xVel[];
	 static double yVel[];
	 
	 
	 /* Zeichen weiterziehen lassen */
	
	static void zeichenWeiterziehenLassen()
	{
		for(int a = 0; a < xPos.length; a++)
		{
			xPos[a] += xVel[a];
			yPos[a] += yVel[a];
		}
	}
	
	
	/* Scannen eines ganzen Koordinatenblocks (liegt ein Zeichen drin?) */
	
	static boolean blockScannen(int blockStartX, int blockStartY, int blockBreite, int blockHoehe)
	{	
		for(int a = 0; a < xPos.length; a++)
			if(xPos[a] >= blockStartX && xPos[a] < (blockStartX+blockBreite) && yPos[a] >= blockStartY && yPos[a] < (blockStartY+blockHoehe))
				return true;
		
		return false;
	}
	
	public static void main(String[] abc) throws FileNotFoundException, IOException
	  {	
		 /* Input lesen */
		 
		 FileReader reader;
		 reader = new FileReader("./src/Daten/Tag_10_input.txt");
		 
		 BufferedReader bufferedReader;
		 bufferedReader = new BufferedReader(reader);
		 
		 String[] fileText = new String[500];
		 int i = 0;

		 while((fileText[i] = bufferedReader.readLine()) != null)
		 {i++;}
		 
		 
		 /* Input bearbeiten */

		 xPos = new int[i];
		 yPos = new int[i];
		 xVel = new double[i];
		 yVel = new double[i];
		 
		 for(int a = 0; a < i; a++)
			 {
			     if(fileText[a].substring(10, 11).equals(" "))
				   xPos[a] = Integer.parseInt(fileText[a].substring(11, 16));
			     else
			      xPos[a] = (-1) * Integer.parseInt(fileText[a].substring(11, 16));
			     
			     if(fileText[a].substring(18, 19).equals(" "))
				   yPos[a] = Integer.parseInt(fileText[a].substring(19, 24));
			     else
			       yPos[a] = (-1) * Integer.parseInt(fileText[a].substring(19, 24));
			     
			     if(fileText[a].substring(36, 37).equals(" "))
				   xVel[a] = Integer.parseInt(fileText[a].substring(37, 38));
			     else
			       xVel[a] = (-1) * Integer.parseInt(fileText[a].substring(37, 38));
			     
			     if(fileText[a].substring(40, 41).equals(" "))
				   yVel[a] = Integer.parseInt(fileText[a].substring(41, 42));
			     else
			       yVel[a] = (-1) * Integer.parseInt(fileText[a].substring(41, 42));
			 }


		 /* Himmel ausgeben und weiterziehen lassen */
		 
		 int blockbreite = 1;
		 int gescannterSektorStartX;
		 int gescannterSektorStartY;
		 int gescannterSektorBreite = 200;
		 char sektorAbbild[][] = new char[gescannterSektorBreite][gescannterSektorBreite];
		 
		 //Zeit Vorspulen
		 for(int a = 0; a < 10500; a++)
			   zeichenWeiterziehenLassen();
			 
		 for(int sekunden = 0; sekunden < 1000; sekunden++)
		 	{
			 //Punkt verfolgen (zentriert)
			 gescannterSektorStartX = xPos[0]-gescannterSektorBreite/2;
			 gescannterSektorStartY = yPos[0]-gescannterSektorBreite/2;
			 
			 System.out.println("=================================================================================");
			 System.out.println("=================================================================================");
			 System.out.println("=================================================================================");
			 for(int a = gescannterSektorStartX; a < (gescannterSektorStartX+gescannterSektorBreite); a+=blockbreite)
			 	{
				 for(int b = gescannterSektorStartY; b < (gescannterSektorStartY+gescannterSektorBreite); b+=blockbreite)
				   {			 
					 if(blockScannen(b,a,blockbreite,blockbreite))
					   {
						System.out.print("#");
						sektorAbbild[a - gescannterSektorStartX][b-gescannterSektorStartY] = '#';
					   }
					 else
					   {
						System.out.print(".");
						sektorAbbild[a - gescannterSektorStartX][b-gescannterSektorStartY] = '.';
					   }
				   }
				 System.out.println();
			 	}

			 zeichenWeiterziehenLassen();
			 System.out.println("Sekunden: " + (sekunden+10500));
			 System.out.println("=================================================================================");
			 System.out.println("=================================================================================");
			 System.out.println("=================================================================================");
		 	}
		 
	 } 

}
