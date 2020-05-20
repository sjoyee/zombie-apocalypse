package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.GroundCapability;
import game.actions.SowAction;


public class SowBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        double chances = Math.random();
        if (chances <= 0.33){
            for (Exit adjacentLocation: map.locationOf(actor).getExits()){
                if (adjacentLocation.getDestination().getGround().hasCapability(GroundCapability.CAN_BE_SOWED)){
                    if(adjacentLocation.getDestination().getItems().isEmpty()){
                        return new SowAction(adjacentLocation.getDestination());
                    }
                }
            }
        }
        return null;
    }
}

