package game;

import edu.monash.fit2099.engine.WeaponItem;
import game.actions.CraftingAction;

/** A type of Weapon Item that only appears when a Zombie dropped its limb
 * 
 * @author Lua Shi Liang
 *
 */
public class SimpleClub extends WeaponItem{

	/** Create a SimpleClub using a char that specifies its display character for SimpleClub, and adding a capability craftable
	 * Name, damage and verb of SimpleClub are default values
	 * @param display the character to use to represent this item if it is on the ground
	 */
	public SimpleClub(char display){
		super("SimpleClub", display, 20, "SimpleClub whacks");
//		if(display != 'A' || display != 'L') {
//			throw new Exception("display must be either A or L");
//		}
		this.addCapability(ItemCapability.CRAFTABLE);
	}


}
