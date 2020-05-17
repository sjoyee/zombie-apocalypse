package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (rand.nextBoolean()) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		target.hurt(damage);
		if (!target.isConscious()) {
			Corpse corpse = new Corpse("dead" + target, target);
			map.locationOf(target).addItem(corpse);
			
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);	
			
			result += System.lineSeparator() + target + " is killed.";
		}
		else if (target.isConscious() && target.hasCapability(ZombieCapability.UNDEAD)){   //for dropping limbs
			double chances = Math.random();
			if(chances <= 1) {
				ArrayList<Item> myLimbs = new ArrayList<>();
				for(Item item : target.getInventory()) {
					if (item.hasCapability(LimbsCapability.ARM) || item.hasCapability(LimbsCapability.LEG)) {
						myLimbs.add(item);
					}
				}
//				Collections.shuffle(myLimbs);
				if(myLimbs.size()>0) {
					int index= rand.nextInt(myLimbs.size());
					char x = myLimbs.get(index).getDisplayChar();
					char limbs = myLimbs.get(index).getDisplayChar();
					target.removeItemFromInventory(myLimbs.get(index));
					SimpleClub s = new SimpleClub(limbs);
					s.getDropAction().execute(target, map);
					result += System.lineSeparator() + "drop a limb = " + x;
					}
				}
			}

		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
