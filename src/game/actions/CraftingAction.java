package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.ItemCapability;
import game.ZombieClub;
import game.ZombieMace;

public class CraftingAction extends Action{
	
	public CraftingAction() {
	}

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
			for(Item item  : actor.getInventory()) {
				if(item.getDisplayChar() == 'L') {
				actor.removeItemFromInventory(item);
				ZombieMace newMace = new ZombieMace();
				actor.addItemToInventory(newMace);
				result += "A mace is crafted";
			}
				else if(item.getDisplayChar() == 'A') {
					actor.removeItemFromInventory(item);
					ZombieClub newClub = new ZombieClub();
					actor.addItemToInventory(newClub);
					result += "A club is crafted";
					}
				}
		}
		else if(!executable) {
			result+="No item is crafted";
		}	
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		
		return actor + " can craft limb when holding it. " ;
	}

}