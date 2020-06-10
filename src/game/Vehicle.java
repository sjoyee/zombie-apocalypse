package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;

/**
 * An item which represents a vehicle that moves actor to other map.
 *
 * @author Siang Jo Yee
 */

public class Vehicle extends Item {
    /***
     * Constructor. Create a Vehicle object which is not portable using a String that specifies its name and a char that specifies its display character.
     *
     * @param name the name of this Vehicle
     */
    public Vehicle(String name) {
        super(name, 'V', false);
    }

    /**
     * Add action into {@code allowableActions}.
     *
     * @param action the Action to be added into {@code allowableActions}
     */
    public void addAction(Action action){
        this.allowableActions.add(action);
    }
}
