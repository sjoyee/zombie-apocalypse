package game.actions;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.ZombieCapability;

public class VanishAction extends Action{

	@Override
	public String execute(Actor actor, GameMap map) {
		String result = actor + " has vanish!";
		actor.addCapability(ZombieCapability.VANISH);
		map.removeActor(actor);
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " can vanish. ";
	}
	

}
