package game;

import edu.monash.fit2099.engine.WeaponItem;

/**A weapon item that is crafted by an actor from a zombie leg
 * @author Lua Shi Liang
 *
 */
public class ZombieMace extends WeaponItem{

	/** Creates a ZombieMace
	 * Name, displayChar, damage and verbs are default values for ZombieMace
	 */
	public ZombieMace() {
		super("ZombieMace", 'M', 60, "ZombieMace whacks");
	}

}