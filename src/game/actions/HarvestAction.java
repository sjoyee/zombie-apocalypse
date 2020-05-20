package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Food;
import game.Dirt;
import game.ZombieCapability;

public class HarvestAction extends Action {
    private Location location;

    public HarvestAction(Location location){
        this.location = location;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Food food = new Food();
        location.setGround(new Dirt());
        if(actor.hasCapability(ZombieCapability.FARM)){
            location.addItem(food);
        }
        else{
            actor.addItemToInventory(food);
        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " harvests ripe crop";
    }
}
