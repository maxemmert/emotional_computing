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
public class AgentAccount {
	
	private float money;
	
	public PggAgent agent;
	
	AgentAccount(float startvalue, PggAgent a)
	{
		money = startvalue;
		agent = a;
	}
	
	void add(int amount)
	{
		money += amount;
	}
	
	float getMoney()
	{
		return money;
	}
	
	void reset()
	{
		money = 0.0f;
	}
	
	AgentAccount copy()
	{
		AgentAccount ret = new AgentAccount(0,this.agent);
		
		ret.agent.reset();
		
		return ret;		
	}
}
