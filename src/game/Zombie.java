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
	private int numOfArms = 0;
	private int numOfLegs = 0;
	private double PROB = 0.5;

	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
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
			Actions dropActions = new Actions();
			for (Item item : this.getInventory()) {
				if(item.asWeapon()!=null) {
					dropActions.add(item.getDropAction());
				}
			}
			for (Action drop : dropActions) {	
				drop.execute(this, map);
			System.out.println( this.name + " has drop all weapons");
		}
		}
		if(numOfArms == 1) {
			PROB = 0.75;
			Random rand = new Random();
			if (rand.nextBoolean()) {
				Actions dropActions = new Actions();
				for (Item item : this.getInventory()) {
					if(item.asWeapon()!=null) {
						dropActions.add(item.getDropAction());
					}
				}
				for (Action drop : dropActions) {	
					drop.execute(this, map);
				System.out.println( this.name + " has drop all weapons");
				}
			}
		}
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
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();	
	}
}
