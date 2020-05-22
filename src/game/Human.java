package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.DoNothingAction;
import game.behaviours.EatBehaviour;
import game.behaviours.WanderBehaviour;

/**
 * Class representing an ordinary human.
 * 
 * 
 * @author ram
 *
 */
public class Human extends ZombieActor {
	/**
	 * An array of type Behaviour which consists of the behaviours of a Human.
	 */
	private Behaviour[] behaviours = {new EatBehaviour(), new WanderBehaviour()};

	/**
	 * The default constructor creates default Humans
	 * 
	 * @param name the human's display name
	 */
	public Human(String name) {
		super(name, 'H', 50, ZombieCapability.ALIVE);
	}
	
	/**
	 * The protected constructor can be used to create subtypes
	 * of Human, such as the Player
	 * 
	 * @param name the human's display name
	 * @param displayChar character that will represent the Human in the map 
	 * @param hitPoints amount of damage that the Human can take before it dies
	 */
	protected Human(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints, ZombieCapability.ALIVE);
	}

	/**
	 * Return an action get from the behaviour in {@code behaviours} array to perform on the current turn if the action
	 * is not null, else return a {@link DoNothingAction}.
	 *
	 * @param map the map containing the Actor
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// FIXME humans are pretty dumb, maybe they should at least run away from zombies?
		for (Behaviour behaviour: behaviours){
			Action action = behaviour.getAction(this, map);
			if (action != null){
				return action;
			}
		}
		return new DoNothingAction();
	}

}
