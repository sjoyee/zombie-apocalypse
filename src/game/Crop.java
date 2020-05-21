package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actions.HarvestAction;

/**
 * A crop that ripes over time, can be sowed, fertilized when it is unripe and harvested when it is ripe.
 *
 * @author Siang Jo Yee
 */
public class Crop extends Ground {
    /**
     * The age of the Crop which increases with turn.
     */
    private int age;

    /**
     * A boolean variable to check and determine whether the Crop is ripe.
     */
    private boolean isRipe;

    /**
     * A constant variable which stores the age when the Crop ripes.
     */
    private final int RIPE_AGE = 20;

    /**
     * Constructor.
     * Create a Crop object in which its age is 0, is not ripe initially and has the capability of being fertilized.
     *
     */
    public Crop() {
        super('c');
        age = 0;
        isRipe = false;
        addCapability(GroundCapability.CAN_BE_FERTILIZED);
    }

    /**
     * Sets the age of the Crop.
     * @param age an integer that specifies the age of the Crop.
     */
    private void setAge(int age) {
        this.age = age;
    }

    /**
     * Allow Crop to experience the passage of time. When it reaches the age to ripe, the character which represents the
     * Crop and its capability to be fertilized will be changed to be displayed as ripe crop and have the capability to
     * be harvested.
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
            isRipe = true;
        }
    }

    /**
     * Allow Crop to be harvested by actor at the specific location if the Crop is ripe.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a collection of Actions which consists of a HarvestAction object if the Crop is ripe.
     *
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        if(isRipe) {
            Actions actions = new Actions();
            actions.add(new HarvestAction(location));
            return actions;
        }
        return new Actions();
    }

    /**
     * Speed up the age of the Crop if it is fertilized.
     */
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

