/*
 * PUBLIC GOODS GAME
 * ------------------------------------------------------
 * Created on 13.04.2006
 *
 * Die hier definierte Rahmenklasse soll von Ihnen mit 
 * der entsprechenden "Strategie" Ihres Agenten gefüllt
 * werden.
 * 
 * Ändern Sie dazu ausschließlich die Methoden
 * einsatz() und  strafen() !
 * Alle übrigen Methoden werden ererbt.
 * 
 */


package publicgoodsgame;


abstract class PggAgent {
	
	private String name;	           // Name Ihres Agenten
	
	protected int [][] einsatzHistorie;  // kann verwendet werden ...
	
	protected  int [] letztesSpiel;	   // das Ergebnis der letzten
									   // Spielrunde (Einsätze)
	
									   // Achtung: in der ersten 
		                               // Spielrunde noch "null"
	
	protected  int [][] letzteStrafen; // das Ergebnis der letzten
									   // Spielrunde (Strafen)

	protected int letzteEinnahme;      // Die Einnahmen aus der letzten
									   // Spielrunde
	
	protected int kontostand;		   // aktuelle Finanzmittel
	
	protected int id;				   // kann sich während des Spiels
									   // ändern !
	
	protected int [] playerIds;        // Ids of the participating players
	                                   // in the used order 
	
	protected int groupsize = 3;
	
		
	PggAgent(String name, int id)
	{
		this.name = name;
		this.id   = id;
		
		// Initialisierung der Felder vor der ersten Runde
		
	    letztesSpiel    = null;
	    einsatzHistorie = null;
	    letzteEinnahme  = 0;
	    kontostand      = 0;	    
	    
	    playerIds = new int [PggController.AGENTS];
	    for (int i= 0;i<PggController.AGENTS;i++)
	    	playerIds[i]=0;
	}
	
	public int ownPos()
	{
	  int pos = 0;
	
	  for(int i = 0;i<groupsize;i++)
	  {
	     if (playerIds[i] == id) pos = i;	
	  }
	  
	  return pos;
	}
	
	public void setIds (int [] ids)
	{
	  for (int i = 0;i<ids.length;i++)
	  {
	  	playerIds[i] = ids[i];
	  }
	}
	
	public int getPlayerIdOnPosition(int i)
	{
		return playerIds[i];
	}
	
	public void setId (int id)
	{
		this.id = id;
	}
	
	public int getId ()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setKontostand(int stand)
	{
		kontostand = stand;
	}
	
	public int getKontostand()
	{
		return kontostand;
	}
		
	public void setEinnahme(int einnahme)
	{
		letzteEinnahme = einnahme;
	}
	
	public int getEinnahme()
	{
		return letzteEinnahme;
	}
	
	public int getEinnahmeVorStrafe()
	{
		int index = 0;
		int sum   = 0;
		
		for (index=0;index < letztesSpiel.length;index++)
		{
			sum+=letztesSpiel[index];
		}
		
		return (int)((sum*PggController.ZINS+sum*100)/(letztesSpiel.length*100))
		       + (PggController.EINSATZ_MAX-letztesSpiel[id]);
	}
	
	
	public void setResultat(int [] einsaetze)
	{
		letztesSpiel = new int [einsaetze.length];
		
		// vom Spielmanager wird die Information über
		// die letzten Einsätze übergeben
		groupsize = einsaetze.length;
		letztesSpiel = einsaetze;
	}
	
	public void setStrafen (int [][] strafe)
	{
		letzteStrafen = new int [strafe.length][strafe[0].length];
		
		// vom Spielmanager wird die Information über
		// die letzten Strafen übergeben
		
		letzteStrafen = strafe;
	}
	
	public int einsetzen()
	{
		// Hier definieren Sie was eingesetzt werden soll
		// d.h. die Ausgabe kann zwischen "20 Euro" und "Nichts"
		// sein 
		
		if (letztesSpiel == null)
		{
			// Erste Runde - kein vorhergehendes Spiel
			
			// ...
		}
		else
		{
			// spätere Runde - es liegen "Erfahrungen" vor
			
			// ...
		}
		
		return 0;
	}
	
	public int[] strafen()
	{
		int [] strafArray;
		
		strafArray = new int [letztesSpiel.length];
		
		for (int i=0;i<letztesSpiel.length;i++)
		{
			// Hier definieren Sie, ob der Gegner i 
			// (bzw. Mitspieler) bestraft werden soll.
			// Dessen letzter Einsatz ist im Feld an
			// Position i zu finden.
			// Beachten Sie, dass der eigene (!)
			// Betrag an Position "id" geführt wird
			
			// Mögliche Rückgabe: Integer zwischen 0 und STRAFE_MAX
			
			strafArray[i] = 0;
		}
			
		return strafArray;
	}
	
	public int averagePayments()
	{
		/* todo */
		return 0;
	}
	
	public int minPayments()
	{
		return 0;
	}
	
	public int maxPayments()
	{
		return PggController.EINSATZ_MAX;
	}
	
	public int lastPayments()
	{
		int sum = 0;
		
	    if (letztesSpiel == null)
	    {
	    	return PggController.EINSATZ_MAX;
	    }
	    else
	    {
	    	for (int i=0;i<letztesSpiel.length;i++)
	    		sum += letztesSpiel[i];
	    	
	    	sum -= letztesSpiel[ownPos()];
	    	
	    	return sum;
	    }
	}
	
	public int lastAveragePayment()
	{
		int sum = 0;
		
	    if (letztesSpiel == null)
	    {
	    	return PggController.EINSATZ_MAX;
	    }
	    else
	    {
			for (int i=0;i<letztesSpiel.length;i++)
	    		sum += letztesSpiel[i];
	    	
	    	sum -= letztesSpiel[ownPos()];
	    	
	    	return (int)(sum/(letztesSpiel.length-1));
	    }
	}
	
	public int lastPunishments()
	{
		/* Strafen die der Agent in der letzten Runde erhalten hat. */
		
		int sum = 0;
		
	    if (letztesSpiel == null)
	    {
	    	return 0;
	    }
	    else
	    {
	    	for (int i=0;i<groupsize;i++)
	    		for (int j=0;j<groupsize;j++)
	    		{
	    			if (j==ownPos())
	    				sum += letzteStrafen[i][j];
	    		}
	    	
	    	return sum;
	    }
	}
	
	
	public int myLastPunishments()
	{
		/* Strafen die der Agent in der letzten Runde vergeben hat. */
		
		int sum = 0;
		
	    if (letztesSpiel == null)
	    {
	    	return 0;
	    }
	    else
	    {
	    		for (int j=0;j<groupsize;j++)
	    		{
	    				sum += letzteStrafen[ownPos()][j];
	    		}
	    	
	    	return sum;
	    }
	}
	
	public int lastAveragePunishment()
	{
		/* Strafen die von den Agenten in der letzten Runde vergeben wurden. */
		
		int sum = 0;
		
	    if (letztesSpiel == null)
	    {
	    	return 0;
	    }
	    else
	    {
	    	for (int i=0;i<groupsize;i++)
	    		for (int j=i;j<groupsize;j++)
	    		{
	    			sum += letzteStrafen[i][j];
	    		}
	    	
	    	return (int)(sum/groupsize);
	    }
	}
	public int myLastPayment()
	{
		return letztesSpiel[ownPos()];
	}
	
	public void reset()
	{
		letztesSpiel = null;
		einsatzHistorie = null;
		letzteEinnahme  = 0;
	    kontostand      = 0;
	}
	
	public void print()
	{
		System.out.println("[" + getName() + "] payment: " + myLastPayment() + " punish others: " + myLastPunishments() + " punished by: " + lastPunishments() + " income: " + getEinnahme() + " Euro.");
	}
	
}
