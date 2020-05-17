package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Food;
import game.ZombieCapability;

public class HarvestAction extends Action {
    private Item item;
    private Location location;

    public HarvestAction(Item item, Location location){
        this.item = item;
        this.location = location;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Food food = new Food();
        if(actor.hasCapability(ZombieCapability.FARM)){
            location.addItem(food);
            location.removeItem(item);
        }
        else{
            location.removeItem(item);
            actor.addItemToInventory(food);
        }
        return actor + " harvests " + item;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " harvests " + item;
    }
}
