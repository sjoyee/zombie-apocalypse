package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.AimCapability;
import game.PortableItem;

public class ShootAction extends AttackAction{
	private int damage;
	private Item ammo;
	
	
	public ShootAction(Actor target, int damage, Item ammo) {
		super(target);
		this.damage = damage;
		this.ammo = ammo;
	}

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

		this.removeCapability(target);
		if (ammo != null) {
			actor.removeItemFromInventory(ammo);
			
		}
		if (!target.isConscious() || target.hasCapability(AimCapability.ROUND2)) {
			Item corpse = new PortableItem("dead " + target, '%');
			result += System.lineSeparator() + deadActor(map, corpse);
		}
		return result;

//		target.hurt(damage);
//		actor.removeItemFromInventory(ammo);
//		result = actor + " shoots " + target + " for " + damage + " damage ";
//		if (!target.isConscious()) {
//			Item corpse = new PortableItem("dead " + target, '%');
//			map.locationOf(target).addItem(corpse);
//
//			Actions dropActions = new Actions();
//			for (Item item : target.getInventory())
//				dropActions.add(item.getDropAction());
//			for (Action drop : dropActions)
//				drop.execute(target, map);
//			map.removeActor(target);
//
//			result += System.lineSeparator() + target + " is killed.";
//		}
//		return result;
	}
	private void removeCapability( Actor actor) {
//		actor.removeCapability(AimCapability.CONCENTRATION);
		actor.removeCapability(AimCapability.ROUND1);
		actor.removeCapability(AimCapability.ROUND2);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " shoots " + target;
	}

}