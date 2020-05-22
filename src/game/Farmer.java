package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Display;
import game.behaviours.*;

/**
 * Class representing a farmer which is a kind of human and also can sow, fertilize and harvest.
 *
 * @author Siang Jo Yee
 */
public class Farmer extends Human{
    /**
     * An array of type Behaviour which consists of the behaviours of a Farmer.
     */
    private Behaviour[] behaviours =
            {new EatBehaviour(), new HarvestBehaviour(), new FertilizeBehaviour(), new SowBehaviour(), new WanderBehaviour()};

    /**
     * Create a Farmer object using a String that specifies the name of the actor, a char that specifies the display
     * character of Farmer, an integer that specifies the hit points of the actor, and adding the capability
     * {@link ZombieCapability#FARM}.
     *
     * @param name the Farmer's display name
     */
    protected Farmer(String name) {
        super(name, 'F', 50);
        addCapability(ZombieCapability.FARM);
    }

    /**
     * Return an action get from the behaviour in {@code behaviours} array to perform on the current turn if the action
     * is not null, else return a {@link DoNothingAction}.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn.
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     *
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour: behaviours){
            Action action = behaviour.getAction(this, map);
            if (action != null){
                return action;
            }
        }
        return new DoNothingAction();
    }
}
