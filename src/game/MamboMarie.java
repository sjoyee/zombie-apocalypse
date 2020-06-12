package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.actions.VanishAction;
import game.behaviours.ChantingBehaviour;
import game.behaviours.WanderBehaviour;

/** Class representing Mambo Marie
 * @author Lua Shi Liang
 *
 */
public class MamboMarie extends ZombieActor{
	/**
	 *  Number of Mambo Marie's turns in the game
	 */
	private int turn = 0;
	/**
	 * An array of behaviour which consist of Mambo Marie's behaviour
	 */
	private Behaviour[] behaviours = {new ChantingBehaviour(), new WanderBehaviour()};

	/**
	 * Constructor
	 * @param name Name of the Mambo Marie
	 */
	public MamboMarie(String name) {
		super(name,'Q', 100, ZombieCapability.MAMBO);
		
	}

	/**
	 * Every 30th turn, Mambo Marie will have vanish action.
	 * Every 10th turn, Mambo Marie will have chanting behaviour
	 * During other turns, she will just wander around
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 * 
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		turn++;
		if(turn%30 == 0 && turn > 0){
			return new VanishAction();

		}
		if (turn%10 == 0 && turn > 0) {
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