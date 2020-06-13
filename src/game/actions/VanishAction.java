package game.actions;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.ZombieCapability;

/** Special action for an actor to vanish
 * @author Lua Shi Liang
 *
 */
public class VanishAction extends Action{

	/**
	 * Remove actor from the map and 
	 * add ZombieCapability.VANISH to the actor
	 * 
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * 
     * @return a string of description on this action for display
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = actor + " has vanish!";
		actor.addCapability(ZombieCapability.VANISH);
		map.removeActor(actor);
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
		return actor + " can vanish. ";
	}
	

}
