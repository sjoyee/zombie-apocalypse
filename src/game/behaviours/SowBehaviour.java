package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.GroundCapability;
import game.actions.SowAction;

/**
 * A class that generates an SowAction if the current Actor is standing beside a Dirt to sow a crop on it.
 *
 * @author Siang Jo Yee
 */
public class SowBehaviour implements Behaviour {

    /**
     * Returns a SowAction that allows an Actor whose adjacent Ground is a Dirt to have 33% probability of sowing a crop on it.
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return a new SowAction object if conditions are met, else null
     */

    @Override
    public Action getAction(Actor actor, GameMap map) {
        double chances = Math.random();
        if (chances <= 0.33){
            for (Exit adjacentLocation: map.locationOf(actor).getExits()){
                if (adjacentLocation.getDestination().getGround().hasCapability(GroundCapability.CAN_BE_SOWED_ON)){
                    return new SowAction(adjacentLocation.getDestination());
                }
            }
        }
        return null;
    }
}

