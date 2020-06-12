package game.actions;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.AimCapability;

/** Special action for aiming at other actors
 * @author Lua Shi Liang
 *
 */
public class AimAction extends Action {
	/**
	 *  Target that gets aimed
	 */
	private Actor target;
	
	/**
	 *  Constructor
	 * @param actor The actor that is being aimed
	 */
	public AimAction(Actor actor) {
		target = actor;
	}

	/**
	 * Actor needs concentration to be aim target, if actor performs other action
	 * or get hurt, they will lose their concentration.
	 * Target can be aimed for 2 rounds.
	 * 
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * 
     * @return a string of description on this action for display
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String result="No aim";
		if(!actor.hasCapability(AimCapability.CONCENTRATION)) { 
			actor.addCapability(AimCapability.CONCENTRATION);
			target.removeCapability(AimCapability.ROUND1);
			target.removeCapability(AimCapability.ROUND2);
		}
		if(!target.hasCapability(AimCapability.ROUND1)){
			target.addCapability(AimCapability.ROUND1);
			result = "Aim " + target +" for first round";
		}
		else if(target.hasCapability(AimCapability.ROUND1) && actor.hasCapability(AimCapability.CONCENTRATION)) {
			target.removeCapability(AimCapability.ROUND1);
			target.addCapability(AimCapability.ROUND2);
			result = "Aim "+ target +" for second round";
		}	
		return result;
	}

    /**
     * Return a string of description on this action for display.
     *
     * @param actor The actor performing the action.
     * @return a string of description on this action for display
     */
	@Override
	public String menuDescription(Actor actor) {
		return " Aim " + target;
	}

}