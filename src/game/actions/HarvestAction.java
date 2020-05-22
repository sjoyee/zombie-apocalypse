package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Food;
import game.Dirt;
import game.ZombieCapability;

/**
 * Special Action that allows Actor to harvest a ripe crop for food.
 *
 * @author Siang Jo Yee
 */
public class HarvestAction extends Action {

    /**
     * A Location that specifies the location where a ripe crop is to be harvested for food.
     */
    private Location harvestLocation;

    /**
     * Create a HarvestAction object using a Location that specifies the location where a ripe crop is to be harvested.
     * @param harvestLocation a Location that specifies the location where a ripe crop is to be harvested for food.
     */
    public HarvestAction(Location harvestLocation){
        this.harvestLocation = harvestLocation;
    }

    /**
     * Allow the Actor to harvest a ripe crop and drop a Food on {@code harvestLocation} for Farmer and add the Food
     * into the inventory for Player. The Ground will be reset as a Dirt.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string of description on this action for display
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Food food = new Food();
        harvestLocation.setGround(new Dirt());
        if(actor.hasCapability(ZombieCapability.FARM)){
            harvestLocation.addItem(food);
        }
        else{
            actor.addItemToInventory(food);
        }
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
        return actor + " harvests ripe crop";
    }
}
