package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Menu;
import game.actions.CraftingAction;
import game.actions.HarvestAction;

/**
 * Class representing the Player.
 */
public class Player extends Human {

	private Menu menu = new Menu();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		if (map.locationOf(this).getGround().hasCapability(GroundCapability.CAN_BE_HARVESTED)){
			actions.add(new HarvestAction(map.locationOf(this)));
		}
		for(Item item : this.getInventory()) {
			if(item.hasCapability(ItemCapability.CRAFTABLE)) {
				actions.add(new CraftingAction());
			}
		}
		return menu.showMenu(this, actions, display);
	}
}
