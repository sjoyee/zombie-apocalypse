package game.behaviours;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;
import game.Behaviour;

/**
 * A class that generates a PickUpItemAction if the current Actor is standing on a
 * location that has a weapon.
 * 
 * @author Lua Shi Liang 
 *
 */
public class PickUpWeaponBehaviour implements Behaviour{


	/**Returns a PickUpItemAction if there is an WeaponItem on the location it's standing
	 * 
     * @param actor the actor having this behaviour
     * @param map the map where the current actor is
     * @return a new PickUpIteamAction if conditions are met, else null
     */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		for (Item item : map.locationOf(actor).getItems())
			if (item.asWeapon()!=null) {
				return new PickUpItemAction(item);
			}
		return null;
	}

}
