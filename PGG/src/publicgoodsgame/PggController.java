/*
 * PUBLIC GOODS GAME
 * ------------------------------------------------------
 * Created on 13.04.2006
 *
 * Die hier definierte Klasse soll die Wechselwirkung von
 * Agentenstrategien demonstrieren. 
 * 
 * Version: Experimentalversion ...
 * 
 */


package publicgoodsgame;


public class PggController {
	
	static final int EINSATZ_MAX  = 20;
	static final int STRAFE_MAX   = 10;
	static final int ZINS         = 60;
	static final int RUNDEN       = 12;
	static final int STRAF_FAKTOR =  3;
	
	/* Das Spiel wird mit 4 Agenten gespielt */
	
	static int AGENTS = 4;

	/* Eine Kontenliste wird im Controller 
	 * geführt damit sich keiner Geld 
	 * "andichten" kann ...
	 */ 
	
	private static int [] kontenListe   = new int[AGENTS];
	private static int [] einnahmen     = new int[AGENTS];
	
	static int gesamteinsatz;

	static PggAgent [] AgentList = new PggAgent [AGENTS];

	
	public static void play (AgentAccount [] account) 
	{
		
		int tmp;
		int tmpList[];
		
		AGENTS = account.length;

			
		int [] einsatz = new int [AGENTS];
		
		int [][] strafe = new int [AGENTS][AGENTS];
		
		/* init agents */
		
		for (int i = 0; i< AGENTS; i++)
		{
			AgentList[i] = account[i].agent;
			AgentList[i].setKontostand(0);
		}
	
		for (int i = 0; i< kontenListe.length;i++)
		{
			kontenListe[i] = 0;
		}
		
		int [] w = new int [AGENTS];		
		
		for (int k=0;k<AGENTS;k++)
		 w[k] = AgentList[k].id;
		
		for (int i = 0; i < AGENTS; i++)
		{	
			AgentList[i].setIds(w);
		}
		
		// Spiele 12 Runden zum Test
		
		for (int i = 0;i< PggController.RUNDEN ;i++)
		{
			System.out.println("----- Runde "+(i+1)+" ------");
			
			for (int j=0;j<AGENTS;j++)
			{
				 tmp = AgentList[j].einsetzen();
				 if (tmp > EINSATZ_MAX) tmp = EINSATZ_MAX;
				 if (tmp < 0) tmp = 0;
				 
				 einsatz[j] = tmp;
				 				
			}
		
			// Einsätze der Runde den Agenten bekanntgeben
			
			for (int j=0;j<AGENTS;j++)
			{
				AgentList[j].setResultat(einsatz);
			}
			
			// Strafaktionen abfragen
			
			for (int j=0;j<AGENTS;j++)
			{
				tmpList = AgentList[j].strafen();
											
				for (int k=0;k<tmpList.length;k++)
				{
				  if (tmpList[k] > STRAFE_MAX) tmpList[k] = STRAFE_MAX;
				  if (tmpList[k] < 0) tmpList[k] = 0;  
				}
				
				strafe[j] = tmpList;
				
			}
			
			// Strafen der Runde den Agenten bekanntgeben
			
			for (int j=0;j<AGENTS;j++)
			{
				AgentList[j].setStrafen(strafe);
			}
			
			
			// Gewinne berechnen
			
			gesamteinsatz = 0;
			for (int j = 0;j<AGENTS;j++)
			{
				gesamteinsatz += einsatz[j];
			}
			/*
			for (int j = 0;j<agents;j++)
			{
			   einnahmen[j] = EINSATZ_MAX;  
			   einnahmen[j] -= einsatz[j];
			   einnahmen[j] += (gesamteinsatz*(100+ZINS)/100.0f)/agents;
			   for (int k=0;k<agents;k++)
			   	einnahmen[j] -= (strafe[k][j]*STRAF_FAKTOR);
			}
			*/
			
			for (int j = 0;j<AGENTS;j++)
			{
			   einnahmen[j] = (int)(gesamteinsatz * (1+ZINS/100.0f))/AGENTS;  
			   einnahmen[j] += (EINSATZ_MAX-einsatz[j]);
			   for (int k=0;k<AGENTS;k++)
			   	einnahmen[j] -= (strafe[k][j]*STRAF_FAKTOR);
			}
			
					
			// Strafgebühr abziehen
			
			for (int k = 0;k<AGENTS;k++)
			{
			  for (int j = 0;j<AGENTS;j++)
			  {
			    einnahmen[k] -= strafe[k][j];			 
			  }
			}
			
			for (int j=0;j<AGENTS;j++)
			{
				kontenListe[j] += einnahmen[j];
			}
			
			// Agent Rundeninfo übergeben
			
			for (int j=0;j<AGENTS;j++)
			{
			  AgentList[j].setEinnahme(einnahmen[j]);
			  AgentList[j].setKontostand(kontenListe[j]);
			}
			
			
			for (int j=0;j<AGENTS;j++)
			{
				AgentList[j].print();
			}
					
		}
	
		/* enter result in agent account */
		
		for (int j = 0; j < AGENTS; j++)
		{
		   account[j].add(AgentList[j].getKontostand());	
		}
	   }
	
	}
