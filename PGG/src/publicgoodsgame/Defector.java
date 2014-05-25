/*
 * Created on 11.05.2005
 *
 *  */
package publicgoodsgame;

/**
 * bad Agent
 * Der Agent ist ein Schmarotzer - er setzt nie etwas selbst ein
 * straft auch nie (das kostet ja nur).
 */
public class Defector extends PggAgent {


	Defector(String name, int id) {
		super(name, id);
	}
	
	public int einsetzen()
	{
		return 0;
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
