package game.actions;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.AimCapability;

public class AimAction extends Action {
	private Actor target;
	
	public AimAction(Actor actor) {
		target = actor;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		String result="";
		if(target.hasCapability(AimCapability.ROUND1)) {
			target.removeCapability(AimCapability.ROUND1);
			target.addCapability(AimCapability.ROUND2);
			result = "Aim for second round";
		}
		else if(!target.hasCapability(AimCapability.ROUND1)) {
			target.addCapability(AimCapability.ROUND1);
			result = "Aim for first round";
		}
		
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return " Aim " + target;
	}

}