package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.behaviours.ChantingBehaviour;
import game.behaviours.WanderBehaviour;

public class MamboMarie extends ZombieActor{
	private int turn = 0;
	private Behaviour[] behaviours = {new ChantingBehaviour(), new WanderBehaviour()};

	public MamboMarie(String name) {
		super(name,'Q', 100, ZombieCapability.MAMBO);
		
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		turn++;
		if(turn == 10){
			map.removeActor(this);
			System.out.println("Mambo Marie has vanish!");
			return new DoNothingAction();

		}
		if (turn%5 == 0 && turn > 0) {
			this.addCapability(ZombieCapability.CHANT);
		}
		
		for (Behaviour behaviour: behaviours){
			Action action = behaviour.getAction(this, map);
			if (action != null){
				return action;
			}
		}
		return new DoNothingAction();
	}

}