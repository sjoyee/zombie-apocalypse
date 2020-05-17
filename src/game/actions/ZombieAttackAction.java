package game.actions;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import edu.monash.fit2099.engine.WeaponItem;
import game.PortableItem;

public class ZombieAttackAction extends AttackAction{
	
	public ZombieAttackAction(Actor target) {
		super(target);
	}
	

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
			if(weapon.verb() == "bites" && prob < 0.75) {
				return miss;
			}
			else if(weapon.verb() == "punches" && prob < 0.25) {
				return miss;
			}
		}
		if(weapon.verb()=="bites") {
			actor.heal(5);
			result += actor +" heals for 5 hitpoints" + System.lineSeparator();
		}

		int damage = weapon.damage();
		result += actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		target.hurt(damage);
		if (!target.isConscious()) {
			Item corpse = new PortableItem("dead " + target, '%');
			map.locationOf(target).addItem(corpse);
			
			Actions dropActions = new Actions();
			for (Item item : target.getInventory()) {
				dropActions.add(item.getDropAction());
			}
			for (Action drop : dropActions)	{
				drop.execute(target, map);	
			}
			map.removeActor(target);
			result += System.lineSeparator() + target + " is killed.";
		}
		return result;
	}

	



}
