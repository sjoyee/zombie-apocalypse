package game;

import game.actions.EatAction;

/**
 * A food item which is portable and can be eaten.
 *
 * @author Siang Jo Yee
 */
public class Food extends PortableItem{

    /**
     * Create a Food object using a String that specifies the name of the Food, a char that specifies the display
     * character of Food, and adding the capability {@link ItemCapability#EDIBLE}. {@link EatAction} is allowed to be
     * performed on Food by Player.
     */
    public Food() {
        super("food", 'o');
        addCapability(ItemCapability.EDIBLE);
        allowableActions.add(new EatAction(this));
    }
}

