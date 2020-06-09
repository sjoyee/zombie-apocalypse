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
		String result = "";
		if(target.hasCapability(AimCapability.ROUND1)) {
			damage = damage * 2;
		}

		if(target.hasCapability(AimCapability.ROUND2)) {
			result = actor + " instakill " + target;
//			damage = 1000;
		}
		else{
			target.hurt(damage);
			result += actor + " shoots " + target + " for " + damage + " damage";
		}

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

	@Override
	public String menuDescription(Actor actor) {
		return actor + " shoots " + target;
	}

}