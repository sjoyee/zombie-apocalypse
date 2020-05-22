package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import game.Behaviour;
import game.GroundCapability;
import game.actions.FertilizeAction;

/**
 * A class that generates a FertilizeAction if the current Actor is standing on an unripe Crop to fertilize it.
 *
 * @author Siang Jo Yee
 */
public class FertilizeBehaviour implements Behaviour {

    /**
     * Returns a FertilizeAction that allows an Actor who is standing on an unripe Crop to fertilize it.
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return a new FertilizeAction object if requirement fulfilled, else null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Ground ground = map.locationOf(actor).getGround();
        if (ground.hasCapability(GroundCapability.CAN_BE_FERTILIZED)){
            return new FertilizeAction(ground);
        }
        return null;
    }
}
