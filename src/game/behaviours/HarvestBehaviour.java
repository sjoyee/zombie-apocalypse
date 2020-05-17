package game.behaviours;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Item;
import game.Behaviour;
import game.ItemCapability;
import game.actions.HarvestAction;

import java.util.ArrayList;
import java.util.List;

public class HarvestBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        List<Location> locationList = new ArrayList<>();    // collection of current and adjacent locations
        locationList.add(map.locationOf(actor));
        List<Exit> adjacentLocationList = map.locationOf(actor).getExits();
        for (Exit adjacentLocation: adjacentLocationList){
            locationList.add(adjacentLocation.getDestination());
        }
        for (Location location: locationList){
            for (Item item: location.getItems()){
                if (item.hasCapability(ItemCapability.CAN_BE_HARVESTED)){
                    return new HarvestAction(item, location);
                }
            }
        }
        return null;
    }
}