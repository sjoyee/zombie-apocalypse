package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.Behaviour;
import game.Crop;
import game.ItemCapability;
import game.actions.FertilizeAction;

public class FertilizeBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Item item: map.locationOf(actor).getItems()){
            if(item.hasCapability(ItemCapability.CAN_BE_FERTILIZED)){
                return new FertilizeAction((Crop) item);
            }
        }
        return null;
    }
}
