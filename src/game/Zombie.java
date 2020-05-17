package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;

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
	private int numOfArms;
	private int numOfLegs;
	private double PROB;
	private boolean isSecondTurn = false;

	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
		numOfArms = 0;
		numOfLegs = 0;
		PROB = 0.5;
		this.addItemToInventory(new Limbs("arms",'A'));
		this.addItemToInventory(new Limbs("arms",'A'));
		this.addItemToInventory(new Limbs("legs",'l'));
		this.addItemToInventory(new Limbs("legs",'l'));
	}
	
	public void checkStatus(GameMap map) {
		int newNumOfArms=0;
		int newNumOfLegs=0;
		for(Item item : this.getInventory()) {
			if (item.hasCapability(LimbsCapability.ARM)) {
				 newNumOfArms++;
			}
			else if (item.hasCapability(LimbsCapability.LEG)) {
				newNumOfLegs++;
			}
		numOfArms = newNumOfArms;
		numOfLegs = newNumOfLegs;
		}
		if (numOfArms == 0){
			PROB = 1;
			dropWeapons(name, map);
		}
		if(numOfArms == 1) {
			PROB = 0.75;
			Random rand = new Random();
			if (rand.nextBoolean()) {
				dropWeapons(name, map);
			}
		}
	}

	private void dropWeapons(String nameOfActor, GameMap map){
		Actions dropActions = new Actions();
		for (Item item : this.getInventory()) {
			if(item.asWeapon()!= null) {
				dropActions.add(item.getDropAction());
			}
		}
		for (Action drop : dropActions) {
			drop.execute(this, map);
		}
		System.out.println(nameOfActor + " has drop all weapons");
	}

	private Action returnAction(Behaviour[] behaviourArray, GameMap map){
		for (Behaviour behaviour : behaviourArray) {
			Action action = behaviour.getAction(this, map);

			if (action != null) {
				return action;
			}
		}
		return new DoNothingAction();
	}
	


	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {  //Changes for Zombie Attacks
		double chances = Math.random();
		if (chances <= PROB) {
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
		this.checkStatus(map);
		if (numOfLegs == 2){
			return returnAction(behaviours, map);
		}
		if (numOfLegs == 1){
			if (!isSecondTurn){
				isSecondTurn = true;
				return returnAction(behaviours, map);
			}
			else{
				isSecondTurn = false;
				return returnAction(behavioursWithoutLegs, map);
			}
		}
		if (numOfLegs == 0) {
			return returnAction(behavioursWithoutLegs, map);
		}
		return new DoNothingAction();	
	}
}
