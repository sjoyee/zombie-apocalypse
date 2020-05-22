package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Crop;

/**
 * Special Action for sowing a Crop.
 *
 * @author Siang Jo Yee
 */
public class SowAction extends Action {
    /**
     * The Location where an Actor sows a Crop on.
     */
    private Location sowLocation;

    /**
     * Create a SowAction object using a Location that specifies the location where the current Actor sows a Crop on.
     *
     * @param sowLocation A Location where the Actor sows a Crop on
     */
    public SowAction(Location sowLocation){
        this.sowLocation = sowLocation;
    }

    /**
     * Set the ground of {@code sowLocation} to be a Crop.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string of description on this action for display
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        sowLocation.setGround(new Crop());
        return actor + " successfully sows a crop";
    }

    /**
     * Return a string of description on this action.
     *
     * @param actor The actor performing the action.
     * @return a string of description on this action for display
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " sows crop ";
    }
}
