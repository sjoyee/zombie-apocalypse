package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;

public class FertilizeAction extends Action {
    private Ground unripeCrop;

    public FertilizeAction(Ground unripeCrop){
        this.unripeCrop = unripeCrop;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        unripeCrop.speedUpAge();
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " fertilizes unripe crop";
    }
}
