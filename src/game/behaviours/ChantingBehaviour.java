package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.ZombieCapability;
import game.actions.SummonZombieAction;

public class ChantingBehaviour implements Behaviour{
//	private int turn;
//	
//	public ChantingBehaviour(int chantingTurn) {
//		turn = chantingTurn;
//	}

	@Override
	public Action getAction(Actor actor, GameMap map) {
		if(actor.hasCapability(ZombieCapability.CHANT)) {
			actor.removeCapability(ZombieCapability.CHANT);
			return new SummonZombieAction(5);
		}
		return null;
	}

}