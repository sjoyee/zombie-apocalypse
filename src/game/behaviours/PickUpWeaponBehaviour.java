package game.behaviours;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;
import game.Behaviour;

public class PickUpWeaponBehaviour implements Behaviour{


	@Override
	public Action getAction(Actor actor, GameMap map) {
		for (Item item : map.locationOf(actor).getItems())
			if (item.asWeapon()!=null) {
				return new PickUpItemAction(item);
			}
		return null;
	}

}
