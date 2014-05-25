/*
 * Ein simler "emotionaler" Agent
 * 
 * Der Agent handelt nicht auf Basis des OCC Modells sondern ist 
 * stark vereinfacht. Er nutzt jedoch die Basisinformation.
 * 
*/

package publicgoodsgame;


public class EmotionalAgent extends PggAgent {

	
	/*
	 * Ein Muster für Ihren Agenten - bitte füllen!
	 * 
	 * AgentenName :
	 * Autoren     :
	 * 
	 * Kurzbeschreibung :
	 * 
	*/

	public EmotionalAgent(String name, int id) {
			super(name, id);
			
			this.setName("EmoAgent");
		}
		
	public int einsetzen()
		{
			/* Hier fehlt noch Ihr Code für die Verhaltenssteuerung
			 * des Agenten. Z.Z. behält er einfach immer alles!
			 */
			return 20;
		}
		
	public int[] strafen()
		{
			int [] strafArray;
			
			/* Hier fehlt noch Ihr Code für die Verhaltenssteuerung
			 * des Agenten. Z.Z. straft er keinen. Vorsicht bei der
			 * Bestrafung: Agent "this.id" sind Sie selbst! :-)
			 * Die Zahlung des Agenten i steht in letztesSpiel[i].
			 */
			
			strafArray = new int [super.letztesSpiel.length];
			
			for (int i=0;i<super.letztesSpiel.length;i++)
			{			
				strafArray[i] = 0;
			}
				
			return strafArray;
		}
		
		

	public void emotionalReactionToPayments()
	{
		/* ... */
		
		int payment;
		
		for (int i=0;i<super.letztesSpiel.length;i++)
		{			
			payment = super.letztesSpiel[i];
			
			/* do something with "payment" -> react on agent i */
		}
	}
		
		
	public void emotionalReactionToPunishments()
	{	
		/* ... */
		int punishment;
		
		for (int i=0;i<super.letzteStrafen.length;i++)
			for (int j=0; j<super.letzteStrafen[i].length;j++)
			{			
				punishment = super.letzteStrafen[i][j];
			
				/* do something with "punishment" of agent i to agent j */
		}
		
	}
	
	

}
