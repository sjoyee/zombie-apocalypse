package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;

public class Vehicle extends Item {
    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     */
    public Vehicle(String name, char displayChar) {
        super(name, displayChar, false);
    }

    public void addAction(Action action){
        this.allowableActions.add(action);
    }
}
