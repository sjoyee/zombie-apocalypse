package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.actions.AttackAction;

/**
 * Base class for Actors in the Zombie World
 * @author ram
 *
 */
public abstract class ZombieActor extends Actor {
	
	public ZombieActor(String name, char displayChar, int hitPoints, ZombieCapability team) {
		super(name, displayChar, hitPoints);
		
		addCapability(team);
	}
	
	protected void dropAllWeapons( GameMap map){  // can be added to zombieactor class for extendability and prevent downcasting
		Actions dropActions = new Actions();
		for (Item item : this.getInventory()) {
			if(item.asWeapon()!= null) {
				dropActions.add(item.getDropAction());
			}
		}
		for (Action drop : dropActions) {
			drop.execute(this, map);
		}
		if (dropActions.size() != 0) {
			System.out.println(name + " has drop all weapons");
		}
	}

	@Override
	public boolean isDamaged() {
		return hitPoints < maxHitPoints;
	}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);
		if (otherActor.hasCapability(ZombieCapability.UNDEAD) != this.hasCapability(ZombieCapability.UNDEAD))
			list.add(new AttackAction(this));
		return list;
	}
}
