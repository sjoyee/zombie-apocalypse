package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.actions.HarvestAction;

public class Crop extends Item {
    private int age;

    public Crop() {
        super("crop", 'c',false);
        addCapability(ItemCapability.CAN_BE_FERTILIZED);
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        age ++;
        if (age == 20){
            displayChar = 'C';
            removeCapability(ItemCapability.CAN_BE_FERTILIZED);
            addCapability(ItemCapability.CAN_BE_HARVESTED);
            allowableActions.add(new HarvestAction(this, location));
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int newAge) {
        age = newAge;
    }
}

