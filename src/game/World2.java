package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.World;

public class World2 extends World{

	public World2(Display display) {
		super(display);
	}
	
	@Override
	protected boolean stillRunning() {
		boolean ret = true;
		int zombie=0;
		int human =0;
		for(Actor actor : actorLocations) {
			if(actor.hasCapability(ZombieCapability.ALIVE)) {
				human+=1;
			}
			else if(actor.hasCapability(ZombieCapability.UNDEAD)){
				zombie+=1;
			}
		}
		if(human==0) {
			ret = false;
		}
		else if(zombie==0) {
			ret = false;
		}
		else if(!actorLocations.contains(player)) {
			ret = false;
		}
		return ret;
	}
	@Override
	protected String endGameMessage() {
		String result="";
		int zombie=0;
		int human =0;
		for(Actor actor : actorLocations) {
			if(actor.hasCapability(ZombieCapability.ALIVE)) {
				human+=1;
			}
			else if(actor.hasCapability(ZombieCapability.UNDEAD)){
				zombie+=1;
			}
		if(human==0 || !actorLocations.contains(player)) {
			result="player loses";
		}
		else if(zombie==0) {
			result="player wins";
		}
		}
		return result;
	}
}
