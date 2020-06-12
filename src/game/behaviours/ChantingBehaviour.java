package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.ZombieCapability;
import game.actions.SummonZombieAction;

/** 
 * A class that generates a SummonZombieAction if the current Actor has
 * a ZombieCapability.CHANT capability
 * 
 * @author Lua Shi Liang 
 *
 */
public class ChantingBehaviour implements Behaviour{

	/**
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return a SummonZombieAction if conditions are met
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if(actor.hasCapability(ZombieCapability.CHANT)) {
			actor.removeCapability(ZombieCapability.CHANT);
			return new SummonZombieAction(5);
		}
		return null;
	}

}