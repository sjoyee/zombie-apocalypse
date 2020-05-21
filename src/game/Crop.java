package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actions.HarvestAction;

/**
 *
 */
public class Crop extends Ground {

    private int age;
    private boolean hasRipen;
    private final int RIPE_AGE = 20;

    /**
     *
     */
    public Crop() {
        super('c');
        age = 0;
        hasRipen = false;
        addCapability(GroundCapability.CAN_BE_FERTILIZED);
    }

    private void setAge(int age) {
        this.age = age;
    }

    /**
     *
     * @param location The location of the Ground
     */

    @Override
    public void tick(Location location) {
        super.tick(location);
        age ++;
        if (age == RIPE_AGE){
            displayChar = 'C';
            removeCapability(GroundCapability.CAN_BE_FERTILIZED);
            addCapability(GroundCapability.CAN_BE_HARVESTED);
            hasRipen = true;
        }
    }

    /**
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        if(hasRipen) {
            Actions actions = new Actions();
            actions.add(new HarvestAction(location));
            return actions;
        }
        return new Actions();
    }

    @Override
    public void speedUpAge() {
        int increase;
        increase = 10;
        if (age < (RIPE_AGE - increase)){
            setAge(age + increase);
        }
        else{
            setAge(RIPE_AGE - 1);
        }
    }
}

