

public class Tag_9_Part_1 {
	
	
	/* Konstruktor */
	
	Tag_9_Part_1(int murmel)
	{murmelNummer = murmel;}
	
	
	/* Elemente */
	
	int murmelNummer;	
	Tag_9_Part_1 naechste;
	Tag_9_Part_1 vorherige;
	
	
	
	/* Murmel platzieren */
	
	static Tag_9_Part_1 murmelPlatzieren(Tag_9_Part_1 derzeitigeMurmel, int murmelNummer, long spielerPunkte[], int spielerZaehler)
	{
	 //Nur 1 Element
	 if(derzeitigeMurmel.naechste == derzeitigeMurmel)
	   {
		derzeitigeMurmel.naechste = new Tag_9_Part_1(murmelNummer);
		derzeitigeMurmel.vorherige = derzeitigeMurmel.naechste;
		derzeitigeMurmel.naechste.vorherige = derzeitigeMurmel;
		derzeitigeMurmel.naechste.naechste = derzeitigeMurmel;
		return derzeitigeMurmel.naechste;
	   }
	 //Mindestens 2 Elemente und kein Vielfaches von 23
	 else if(murmelNummer % 23 != 0)
	        {
	         Tag_9_Part_1 rechteMurmelVonPlatzierStelle =  derzeitigeMurmel.naechste.naechste;
	         derzeitigeMurmel.naechste.naechste = new Tag_9_Part_1(murmelNummer);
	         rechteMurmelVonPlatzierStelle.vorherige = derzeitigeMurmel.naechste.naechste;         
	         derzeitigeMurmel.naechste.naechste.naechste = rechteMurmelVonPlatzierStelle; 
	         derzeitigeMurmel.naechste.naechste.vorherige = derzeitigeMurmel.naechste;
	         return derzeitigeMurmel.naechste.naechste;
	        }
	       //Mindestens 2 Elemente und ein Vielfaches von 23
	       else
	         {
	    	   spielerPunkte[spielerZaehler] += murmelNummer;

	    	   //Stelle 8 vorher finden
	    	   for(int a = 0; a < 8; a++)
	    	     derzeitigeMurmel = derzeitigeMurmel.vorherige;
	    	   
	    	   //Stelle 7 vorher löschen
	    	   Tag_9_Part_1 rechtsVonLoeschStelle = derzeitigeMurmel.naechste.naechste;
	    	   spielerPunkte[spielerZaehler] += derzeitigeMurmel.naechste.murmelNummer;
	    	   derzeitigeMurmel.naechste = null;
	    	   derzeitigeMurmel.naechste = rechtsVonLoeschStelle;  	
	    	   rechtsVonLoeschStelle.vorherige = derzeitigeMurmel;
	    	   return derzeitigeMurmel.naechste;
	         }
	}
	
	
	
	/* Kreis ausgeben */
	
	static void gebeMurmelKreisAus(Tag_9_Part_1 derzeitigeMurmel)
	{
		Tag_9_Part_1 wurzel = derzeitigeMurmel;
		
		do 
		  {
			System.out.print(derzeitigeMurmel.murmelNummer + " ");
			derzeitigeMurmel = derzeitigeMurmel.naechste;
		  }
		while(derzeitigeMurmel != wurzel);
	}
	
	
	
	public static void main(String[] abc)
	{
		//Spiel-Setup
		int anzahlSpieler = 416;
		int anzahlMurmeln = (71975*100);
		
		//Erste Murmel im Kreis platzieren
		Tag_9_Part_1 derzeitigeMurmel = new Tag_9_Part_1(0);
		derzeitigeMurmel.naechste = derzeitigeMurmel;
		derzeitigeMurmel.vorherige = derzeitigeMurmel;
		
		//Erste Murmel merken
		Tag_9_Part_1 ersteMurmel = derzeitigeMurmel;
		
		//Spieler
		long spielerPunkte[] = new long[anzahlSpieler];
		int spielerZaehler = 0;
		
		//Nacheinander Murmeln platzieren
		for(int a = 1; a <= (anzahlMurmeln+1); a++)
		{
			derzeitigeMurmel = murmelPlatzieren(derzeitigeMurmel , a, spielerPunkte, spielerZaehler);
			
			spielerZaehler++;
			
			if(spielerZaehler > (anzahlSpieler-1)) 
		      spielerZaehler = 0;	
		}
		
		//Punkte ausgeben		
		long max = 0;
		
		for(int a = 0; a < spielerPunkte.length; a++)	
		  {
		   if(spielerPunkte[a] > max)
             max = spielerPunkte[a];
		  }
		
		System.out.println("Highscore: " + max);
	}

}
