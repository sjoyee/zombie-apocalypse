package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;

/**
 * Special Action for fertilizing an unripe Crop to decrease the time left to ripe.
 *
 * @author Siang Jo Yee
 */
public class FertilizeAction extends Action {
    /**
     * A Ground that specifies an unripe crop to be fertilized.
     */
    private Ground unripeCrop;

    /**
     * Create a FertilizeAction object using a Ground which specifies an unripe Crop that is to be fertilized.
     *
     * @param unripeCrop a Ground that specifies an unripe crop to be fertilized.
     */
    public FertilizeAction(Ground unripeCrop){
        this.unripeCrop = unripeCrop;
    }

    /**
     * Speed up the age of the unripe Crop by calling the overridden method {@code speedUpAge()}.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string of description on this action for display
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        unripeCrop.speedUpAge();
        return menuDescription(actor);
    }

    /**
     * Return a string of description on this action for display.
     *
     * @param actor The actor performing the action.
     * @return a string of description on this action for display
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " fertilizes unripe crop";
    }
}
