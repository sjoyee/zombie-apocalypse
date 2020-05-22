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
	/**
	 * An item to be crafted
	 */
	private Item craftedItem;
	
	/**Creates a crafting action by using the item to be crafted
	 * @param newItem Item to be crafted
	 */
	public CraftingAction(Item newItem) {
		craftedItem = newItem;
	}

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
		String result = "";
			if(craftedItem.getDisplayChar() == 'L') {
				actor.removeItemFromInventory(craftedItem);
				ZombieMace newMace = new ZombieMace();
				actor.addItemToInventory(newMace);
				result += "A Mace ";
			}
			else if(craftedItem.getDisplayChar() == 'A') {
					actor.removeItemFromInventory(craftedItem);
					ZombieClub newClub = new ZombieClub();
					actor.addItemToInventory(newClub);
					result += "A Club ";
					}
			result += "is crafted";
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