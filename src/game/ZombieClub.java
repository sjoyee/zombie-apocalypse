package game;

import edu.monash.fit2099.engine.WeaponItem;

/** A weapon item that is crafted by an actor from a zombie arm
 * @author Lua Shi Liang
 *
 */
public class ZombieClub extends WeaponItem{

	/**
	 * Creates a ZombieClub 
	 * Name, displayChar, damage and verbs are default values for ZombieClub
	 */
	public ZombieClub() {
		super("ZombieClub", 'T', 40, "ZombieClub whacks");
	}

}
