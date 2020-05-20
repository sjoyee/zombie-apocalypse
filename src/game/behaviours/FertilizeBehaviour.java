package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import game.Behaviour;
import game.GroundCapability;
import game.actions.FertilizeAction;

public class FertilizeBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Ground ground = map.locationOf(actor).getGround();
        if (ground.hasCapability(GroundCapability.CAN_BE_FERTILIZED)){
            return new FertilizeAction(ground);
        }
        return null;
    }
}
