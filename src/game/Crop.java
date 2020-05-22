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
     * An integer that specify the age of the Crop which increases with turn, and initialised to be {@code 0}.
     */
    private int age = 0;

    /**
     * A boolean variable to check and determine whether the Crop is ripe, and initialised to be {@code false},
     * indicating the Crop is initially unripe.
     */
    private boolean isRipe = false;

    /**
     * A constant variable which stores the age when the Crop ripes.
     */
    private final int RIPE_AGE = 20;

    /**
     * Create a Crop object using a char that specifies its display character for Crop, and adding the capability
     * {@link GroundCapability#CAN_BE_FERTILIZED}.
     *
     */
    public Crop() {
        super('c');
        addCapability(GroundCapability.CAN_BE_FERTILIZED);
    }

    /**
     * Set the age of the Crop.
     *
     * @param age an integer that specifies the age of the Crop.
     * @throws Exception if age is negative.
     */

    private void setAge(int age) throws Exception {
        if (age < 0){
            throw new Exception("age must not be negative");
        }
        this.age = age;
    }

    /**
     * Allow Crop to experience the passage of time and ripe when {@code age} reaches the {@code RIPE_AGE}.
     * When Crop ripes, a different char will be assigned to {@code displayChar} to represent the ripe Crop,
     * {@code isRipe} is assigned to be true, and has the capability {@link GroundCapability#CAN_BE_HARVESTED}
     * instead of {@link GroundCapability#CAN_BE_FERTILIZED}.
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

        if (age < (RIPE_AGE - increase)) {
            try {
                setAge(age + increase);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                setAge(RIPE_AGE - 1);

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

