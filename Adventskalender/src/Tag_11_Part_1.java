import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tag_11_Part_1 {
	
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
        int besterWert = 0;
        
        for(int a = 0; a < (300-3); a++)
        		for(int b = 0; b < (300-3); b++)
        			if((zellen[a][b] + zellen[a][b+1] + zellen[a][b+2] + zellen[a+1][b] + zellen[a+1][b+1] + zellen[a+1][b+2] + zellen[a+2][b] + zellen[a+2][b+1] + zellen[a+2][b+2]) > besterWert)
        			{
        				besterWert = zellen[a][b] + zellen[a][b+1] + zellen[a][b+2] + zellen[a+1][b] + zellen[a+1][b+1] + zellen[a+1][b+2] + zellen[a+2][b] + zellen[a+2][b+1] + zellen[a+2][b+2];
        				xBeste = a;
        				yBeste = b;
        			}
        
        System.out.println("Bester wert: " + besterWert + " an der Stelle " + xBeste + "," + yBeste);

	 } 
}
