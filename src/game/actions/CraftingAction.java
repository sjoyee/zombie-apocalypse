package game.actions;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.ItemCapability;
import game.ZombieClub;
import game.ZombieMace;

/** Special Action that allows actor to craft a weapon
 * 
 * @author Lua Shi Liang
 *
 */
public class CraftingAction extends Action{

	/** Craft Weapons
	 * 
	 * if it is an arm, craft it into a ZombieClub
	 * if it is a leg, craft it into a ZombieMace
	 * 
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * 
     * @return a string of description on this action for display
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		boolean executable=false;
		for(Item item  : actor.getInventory()) {
			if(item.hasCapability(ItemCapability.CRAFTABLE)){
				executable = true;
			}
		}
		String result = "";
		if(executable) {
			ArrayList<Item> limbs = new ArrayList<>();
			for(Item item  : actor.getInventory()) {
				if(item.getDisplayChar() == 'L' || item.getDisplayChar() == 'A' ) {
					limbs.add(item);
				}
			}
			for(Item item  : limbs) {
				if(item.getDisplayChar() == 'L') {
					actor.removeItemFromInventory(item);
					ZombieMace newMace = new ZombieMace();
					actor.addItemToInventory(newMace);
					result += "A Mace ";
			}
				else if(item.getDisplayChar() == 'A') {
					actor.removeItemFromInventory(item);
					ZombieClub newClub = new ZombieClub();
					actor.addItemToInventory(newClub);
					result += "A Club ";
					}
				}
			result += "is crafted";
		}
		else if(!executable) {
			result+="No item is crafted";
		}	
		return result;
	}

    /**
     * Return a string of description on this action for display.
     *
     * @param actor The actor performing the action.
     * @return a string of description on this action for display
     */
	@Override
	public String menuDescription(Actor actor) {
		
		return actor + " can craft item. " ;
	}

}