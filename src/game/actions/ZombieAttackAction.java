package game.actions;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import edu.monash.fit2099.engine.WeaponItem;
import game.Corpse;

/**Special Action that allows only Zombie to attack
 * 
 * @author Lua Shi Liang
 *
 */
public class ZombieAttackAction extends AttackAction{
	
	/** Creates a ZombieAttackaction using a target where it is the actor 
	 * that it is going to attack
	 * @param target The actor that is being attack
	 */
	public ZombieAttackAction(Actor target) {
		super(target);
	}
	

	/**Attacks the target
	 * 
	 * Zombie has higher missing rate for bite attack than punch attack
	 * if the Zombie lands a bite attack, it heals for 5 hitpoints
	 * 
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * 
     * @return a string of description on this action for display
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String result="";

		Weapon weapon = actor.getWeapon();

		double prob = Math.random();
		String miss = actor + " misses " + target + ".";
		if (rand.nextBoolean() && weapon instanceof WeaponItem) {
			return miss;
		}
		if(weapon instanceof IntrinsicWeapon) {
			if(weapon.verb().equals("bites") && prob < 0.75) {
				return miss;
			}
			else if(weapon.verb().equals("punches") && prob < 0.25) {
				return miss;
			}
		}
		if(weapon.verb().equals("bites")) {
			actor.heal(5);
			result += actor +" heals for 5 hitpoints" + System.lineSeparator();
		}

		int damage = weapon.damage();
		result += actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		target.hurt(damage);
		if (!target.isConscious()) {
			Corpse corpse = new Corpse("dead " + target);
			result += System.lineSeparator() + deadActor(map, corpse);
		}
		return result;
	}
}
