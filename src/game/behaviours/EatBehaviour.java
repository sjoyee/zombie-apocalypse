package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.Behaviour;
import game.ItemCapability;
import game.actions.EatAction;

public class EatBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Item item: map.locationOf(actor).getItems()){
            if(item.hasCapability(ItemCapability.EDIBLE)){
                return new EatAction(item);
            }
        }
        return null;
    }
}
