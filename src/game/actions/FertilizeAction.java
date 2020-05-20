package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Crop;

public class FertilizeAction extends Action {
    private Crop unripeCrop;

    public FertilizeAction(Crop unripeCrop){
        this.unripeCrop = unripeCrop;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        int currentTurn, increase, ripeAge;
        currentTurn = unripeCrop.getAge();
        increase = 10;
        ripeAge = 20;

        if(currentTurn < (ripeAge - increase)){
            unripeCrop.setAge(currentTurn + increase);
        }
        else{
            unripeCrop.setAge(ripeAge - 1);
        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " fertilizes unripe crop";
    }
}
