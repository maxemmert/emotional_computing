package publicgoodsgame;

/* 
 * Game Control
 * 
 * Hier wird eine Teilnehmerkonstellation gesetzt und durchgespielt.
 * Dazu werden Agentenkonten angelegt, welche die aktuelle Geldmenge
 * speichern und den Agenten selbst beinhalten.
 * 
 */
public class GameControl {

	final static int GROUP_SIZE = 4;
	
	public static void main(String[] args) {
		
		AgentAccount [] a = new AgentAccount[GROUP_SIZE];		
		
		a[0] = new AgentAccount(0,new EmotionalAgent("Emotional_1",0));
	
	 	a[1] = new AgentAccount(0,new Defector("Defector__1",1));
	 
		a[2] = new AgentAccount(0,new Punisher("Punisher_1",2));

		a[3] = new AgentAccount(0,new Punisher("Cooperator_1",3));
		
		PggController.play(a);
						
	}
}

