package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actions.HarvestAction;

public class Crop extends Ground {
    private int age;
    private boolean hasRipen;

    public Crop() {
        super('c');
        hasRipen = false;
        addCapability(GroundCapability.CAN_BE_FERTILIZED);
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        age ++;
        if (age == 20){
            displayChar = 'C';
            removeCapability(GroundCapability.CAN_BE_FERTILIZED);
            addCapability(GroundCapability.CAN_BE_HARVESTED);
            hasRipen = true;
        }
    }
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        if(hasRipen) {
            Actions actions = new Actions();
            actions.add(new HarvestAction(location));
            return actions;
        }
        return new Actions();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int newAge) {
        age = newAge;
    }
}

