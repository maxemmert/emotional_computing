
package publicgoodsgame;

/**
 * lambAgent
 * Dieser Agent ist wie ein braves "Lamm" und zahlt immer 
 * ein, bestraft niemanden, erträgt also einfach das Schicksal ...
 */

public class Cooperator extends PggAgent {

	Cooperator(String name, int id) {
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
			strafArray[i] = 0;
		}
			
		return strafArray;
	}

}
