package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Crop;

public class SowAction extends Action {
    private Location sowLocation;

    public SowAction(Location sowLocation){
        this.sowLocation = sowLocation;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Crop crop = new Crop();
        sowLocation.setGround(crop);
        return actor + " successfully sows a crop";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " sows crop ";
    }
}
