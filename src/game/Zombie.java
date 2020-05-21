package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import game.behaviours.AttackBehaviour;
import game.behaviours.HuntBehaviour;
import game.behaviours.PickUpWeaponBehaviour;
import game.behaviours.WanderBehaviour;

/**
 * A Zombie.
 * 
 * This Zombie is pretty boring.  It needs to be made more interesting.
 * 
 * @author ram
 *
 */
public class Zombie extends ZombieActor {
	private Behaviour[] behaviours = {
			new AttackBehaviour(ZombieCapability.ALIVE),
			new HuntBehaviour(Human.class, 10),
			new PickUpWeaponBehaviour(),
			new WanderBehaviour()
	};
	private Behaviour[] behavioursWithoutLegs = {new AttackBehaviour(ZombieCapability.ALIVE), new PickUpWeaponBehaviour()};
	private int numOfArms = 2;
	private int numOfLegs = 2;
	private double probability = 0.5;
	private boolean isSecondTurn = false;

	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
	}

	
	private void checkStatus(GameMap map) {
		if (numOfArms == 0){
			probability = 1;
			dropAllWeapons(map);
		}
		if(numOfArms == 1) {
			probability = 0.75;
			Random rand = new Random();
			if (rand.nextBoolean()) {
				dropAllWeapons(map);
			}
		}
	}
	
	@Override
	public void hurt(int damage) {
		super.hurt(damage);
		double x = Math.random();
		if(x < 1) {
			Random rand = new Random();
			if(rand.nextBoolean() && numOfLegs > 0){
				numOfLegs -=1;
				this.addCapability(LimbsCapability.LEG);
				System.out.println(this + " drop Leg");
				}
			else if(numOfArms > 0){
				numOfArms -=1;
				this.addCapability(LimbsCapability.ARM);
				System.out.println(this + " drop Arm ");
				}
			}
	}

	private Action returnAction(Behaviour[] behaviourArray, GameMap map){
		for (Behaviour behaviour : behaviourArray) {
			Action action = behaviour.getAction(this, map);
			if (numOfArms == 0 && behaviour instanceof PickUpWeaponBehaviour){
               action = action.getNextAction();
            }
			if (action != null) {
				return action;
			}
		}
		return new DoNothingAction();
	}
	


	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {  //Changes for Zombie Attacks
		double chances = Math.random();
		if (chances <= probability) {
			return new IntrinsicWeapon(20,"bites");
		}
		return new IntrinsicWeapon(10, "punches");
	}

	/**
	 * If a Zombie can attack, it will.  If not, it will chase any human within 10 spaces.  
	 * If no humans are close enough it will wander randomly.
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		double chances = Math.random();
		final double PROB = 0.1;
		if(chances <= PROB) {
			display.println(name + " SAYS BRAINSSSSS!!!!");
		}
		if(this.hasCapability(LimbsCapability.ARM)){
			this.removeCapability(LimbsCapability.ARM);
			map.locationOf(this).addItem(new SimpleClub('A'));
		}
		else if(this.hasCapability((LimbsCapability.LEG))) {
			this.removeCapability(LimbsCapability.LEG);
			map.locationOf(this).addItem(new SimpleClub('L'));
		}
		this.checkStatus(map);
		if (numOfLegs == 2){
			return returnAction(behaviours, map);
		}
		if (numOfLegs == 1){
			if (!isSecondTurn){
				isSecondTurn = true;
				return returnAction(behavioursWithoutLegs, map);
			}
			else{
				isSecondTurn = false;
				return returnAction(behaviours, map);
			}
		}
		if (numOfLegs == 0) {
			return returnAction(behavioursWithoutLegs, map);
		}
		return new DoNothingAction();
	}
}
