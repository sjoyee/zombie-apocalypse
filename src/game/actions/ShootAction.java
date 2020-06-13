package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.AimCapability;
import game.PortableItem;

/** Special action for shooting at actors
 * @author Lua Shi Liang 
 *
 */
public class ShootAction extends AttackAction{
	/**
	 *  The damage of the weapon
	 */
	private int damage;
	/**
	 *  The ammo of the weapon
	 */
	private Item ammo;
	
	
	/**Constructor
	 * @param target The actor that will be shot
	 * @param damage The damage of the weapon
	 * @param ammo The ammo of the weapon
	 */
	public ShootAction(Actor target, int damage, Item ammo) {
		super(target);
		this.damage = damage;
		this.ammo = ammo;
	}

	/**
	 * If target is aim for one round, damage dealt will be double
	 * if target is aim for second round, it will be kill instantly
	 * Provided that actor has concentration.
	 * 
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * 
     * @return a string of description on this action for display
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = actor + " misses " + target;
		double chances = Math.random();
		if(!target.hasCapability(AimCapability.ROUND1) && !target.hasCapability(AimCapability.ROUND2)) {
			if(chances <= 0.75) {
				target.hurt(damage);
				result = actor + " shoots " + target + " for " + damage + " damage";
			}
		}
		else if(target.hasCapability(AimCapability.ROUND1)  && actor.hasCapability(AimCapability.CONCENTRATION)) {
			if(chances <= 0.9) {
				damage = damage * 2;
				target.hurt(damage);
				result = actor + " shoots " + target + " for " + damage + " damage";
			}
		}

		else if(target.hasCapability(AimCapability.ROUND2)  && actor.hasCapability(AimCapability.CONCENTRATION)) {
			result = actor + " instakill " + target;
		}

		if (ammo != null) {
			actor.removeItemFromInventory(ammo);
			
		}
		if (!target.isConscious()) { // || target.hasCapability(AimCapability.ROUND2)
			Item corpse = new PortableItem("dead " + target, '%');
			result += System.lineSeparator() + deadActor(map, corpse);
		}
		this.removeCapability(target);
		return result;


	}
	/** Remove AimCapability.ROUND1 and AimCapability.ROUND2 capability from an actor 
	 * @param actor The actor's capability that will be removed
	 */
	private void removeCapability( Actor actor) {

		actor.removeCapability(AimCapability.ROUND1);
		actor.removeCapability(AimCapability.ROUND2);
	}

    /**
     * Return a string of description on this action for display.
     *
     * @param actor The actor performing the action.
     * @return a string of description on this action for display
     */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " shoots " + target;
	}

}