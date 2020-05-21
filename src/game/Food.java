package game;

import game.actions.EatAction;

/**
 * A food item which is portable and can be eaten.
 *
 * @author Siang Jo Yee
 */
public class Food extends PortableItem{

    /**
     * Constructor.
     * Create a Food object which has the capability of being edible and allow Player to eat it.
     */
    public Food() {
        super("food", 'o');
        addCapability(ItemCapability.EDIBLE);
        allowableActions.add(new EatAction(this));
    }
}

