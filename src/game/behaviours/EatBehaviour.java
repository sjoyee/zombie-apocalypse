package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.Behaviour;
import game.ItemCapability;
import game.actions.EatAction;

/**
 * A class that generates an EatAction if the current Actor is damaged and standing on a Food.
 *
 * @author Siang Jo Yee
 */
public class EatBehaviour implements Behaviour {

    /**
     * Returns an EatAction that allows an damaged Actor who is standing on a Food to eat.
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return a new EatAction object if conditions are met, else null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Item item: map.locationOf(actor).getItems()){
            if(actor.isDamaged() && item.hasCapability(ItemCapability.EDIBLE)){
                return new EatAction(item);
            }
        }
        return null;
    }
}
