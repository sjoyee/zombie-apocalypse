package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Display;
import game.behaviours.*;

public class Farmer extends Human{
    private Behaviour[] behaviours =
            {new EatBehaviour(), new HarvestBehaviour(), new FertilizeBehaviour(), new SowBehaviour(), new WanderBehaviour()};

    public Farmer(String name) {
        super(name, 'F', 50);
        addCapability(ZombieCapability.FARM);
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour: behaviours){
            Action action = behaviour.getAction(this, map);
            if (action != null){
                return action;
            }
        }
        return new DoNothingAction();
    }
}
