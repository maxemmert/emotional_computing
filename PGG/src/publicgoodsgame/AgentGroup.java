/*
 * Created on 26.04.2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package publicgoodsgame;

/**
 * @author dirk
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AgentGroup {
	
	static int groupsize = 3;
	
	int [] members;
	
	int last;
	
	AgentGroup(int size)
	{
		groupsize = size;
		reset();
	}
	
	void reset()
	{
		last = 0;
		
		members = new int [groupsize];
		
		for (int i=0; i < groupsize; i++)
		{
			members[i]=-1;
		}
	}
	
	int size()
	{
		return last;
	}
	
	void add(int i)
	{
		if (last < groupsize)
		{
			members[last++]=i;
		}
	}
	
	boolean filled()
	{
	  if (last == groupsize)
	  	return true;
	  else
	  	return false;
	}

	void remove(int i)
	{
		if (i >= 0 && i < last)
		{
			while (i<last-1)
			{
				members[i]=members[i+1];
				i++;
			}
			last--;
		}
	}

	int get(int i)
	{
		if (i >= 0 && i < last)
		{
			return members[i];
		}
		else
			return -1;
	}
}
