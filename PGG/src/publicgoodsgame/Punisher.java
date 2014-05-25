/*
 * Created on 11.05.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package publicgoodsgame;

/**
 * moralist Agent
 *
 * Der Moralist zahlt immer brav ein, straft aber jeden, der
 * es nicht auch so tut sofort!
 */
public class Punisher extends PggAgent {

	Punisher(String name, int id) {
		super(name, id);
	}
	
	public int einsetzen()
	{
		return 20;
	}
	
	public int[] strafen()
	{
		int [] strafArray;
		
		strafArray = new int [super.letztesSpiel.length];
				
		for (int i=0;i<super.letztesSpiel.length;i++)
		{			
			if (super.letztesSpiel[i] < 20)
			  strafArray[i] = PggController.STRAFE_MAX;
		}
			
		return strafArray;
	}	

}
