package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class EatAction extends Action {
    private Item food;
    private final int HEAL_POINTS = 5;

    public EatAction(Item food){
        this.food = food;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.heal(HEAL_POINTS);
        actor.removeItemFromInventory(food);
        map.locationOf(actor).removeItem(food);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " eat " + food + " (recover " + HEAL_POINTS + " points)";
    }
}
