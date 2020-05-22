package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Special Action that allows Actor to eat and recover health points.
 *
 * @author Siang Jo Yee
 */
public class EatAction extends Action {
    /**
     * An Item which is to be eaten and recover health points.
     */
    private Item food;

    /**
     * A constant variable which stores the amount of health points to be recovered if this action is performed
     */
    private final int HEAL_POINTS = 5;

    /**
     * Create a EatAction using an Item that specifies the food which is in the inventory of the Player or the location
     * of current Actor for Human and Farmer.
     *
     * @param food An Item which is to be eaten and able to recover health points
     */
    public EatAction(Item food){
        this.food = food;
    }

    /**
     * Allow the Actor who perform this Action to recover health points and the {@code food} to be removed from inventory
     * for Player and from the location of Actor for Human and Farmer, looking as though Actor eat food to heal the damage.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string of description on this action for display.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.heal(HEAL_POINTS);
        if (actor.getInventory().contains(food)){
            actor.removeItemFromInventory(food);
        }
        map.locationOf(actor).removeItem(food);
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
        return actor + " eat " + food + " (recover " + HEAL_POINTS + " points)";
    }
}
