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
	/**
	 * An array of type Behaviour which consists of the behaviours of a Zombie.
	 */
	private Behaviour[] behaviours = {
			new AttackBehaviour(ZombieCapability.ALIVE),
			new HuntBehaviour(Human.class, 10),
			new PickUpWeaponBehaviour(),
			new WanderBehaviour()
	};
	/**
	 * An array of type Behaviour which consists of the behaviours of a Zombie without legs.
	 */
	private Behaviour[] behavioursWithoutLegs = {new AttackBehaviour(ZombieCapability.ALIVE), new PickUpWeaponBehaviour()};
	/**
	 * The number of arms of the zombie
	 */
	private int numOfArms = 2;
	/**
	 * The number of legs of the zombie
	 */
	private int numOfLegs = 2;
	/**
	 * The probaility of the zombie using bite or punch
	 */
	private double probability = 0.5;
	/**
	 * A boolean that indicates whether the zombie can move if it loses a leg
	 */
	private boolean isSecondTurn = false;

	
	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
	}

	
	/**This method changes the probability of biting and punching depending on the number 
	 * of arms it has and drops its weapon if the conditions met
	 * @param map The map where the zombie currently is
	 */
	private void checkArmStatus(GameMap map) {
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
	
	/**
	 * New feature is added to this overriding method
	 * if it's hurt, it has 50% of chances of losing a limb
	 */
	@Override
	public void hurt(int damage) {
		super.hurt(damage);
		double x = Math.random();
		if(x < 0.5) {
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

	/** Returns the action to be perform depending on the array of behaviour it currently has
	 * 
	 * @param behaviourArray The array of behaviours that the zombie currently have
	 * @param map the map where the current Zombie is
	 * @return the Action to be performed
	 */
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
	


	/**
	 *Gets 1 of the possible type of intrinsic weapon which are punch or bite.
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {  
		double chances = Math.random();
		if (chances <= probability) {
			return new IntrinsicWeapon(20,"bites");
		}
		return new IntrinsicWeapon(10, "punches");
	}

	/**
	 * If a Zombie can attack, it will.  If not, it will chase any human within 10 spaces.  
	 * If no humans are close enough, it will pick up any weapons that are on its location
	 * If there is no weapons, it will wander randomly.
	 * 
	 * if it has no legs, it can only attack or pick up weapons.
	 * 
	 * If it drops a limb, a Simpleclub will be drop on its location
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
		this.checkArmStatus(map);
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
