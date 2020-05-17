package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.GroundCapability;
import game.actions.SowAction;

import java.util.List;

public class SowBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        List<Exit> adjacentLocationList = map.locationOf(actor).getExits();
        for (Exit adjacentLocation: adjacentLocationList){
            if (adjacentLocation.getDestination().getGround().hasCapability(GroundCapability.SOW)){
                if(adjacentLocation.getDestination().getItems().isEmpty()){
                    double chances = Math.random();
                    if (chances <= 0.33){
                        return new SowAction(adjacentLocation.getDestination());
                    }
                }
            }
        }
        return null;
    }
}

