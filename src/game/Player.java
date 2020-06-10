package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Menu;
import game.actions.CraftingAction;
import game.actions.FireRangedWeaponAction;
import game.actions.HarvestAction;
import game.actions.QuitGameAction;

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
	
	public void hurt(int damage) {
		super.hurt(damage);
//		this.removeCapability(AimCapability.CONCENTRATION);
	}

	/**
	 * Select and return an action to perform on the current turn according to the displayed description of Action on the menu.
	 * A new {@link HarvestAction} and a new {@link CraftingAction} is added to {@code actions} for the player to harvest
	 * ripe crop and craft weapon if conditions are met.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn.
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 *
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		if(!(lastAction instanceof FireRangedWeaponAction)) {
			display.println("Player loses concentration");		
			this.removeCapability(AimCapability.CONCENTRATION);
		}
		if (map.locationOf(this).getGround().hasCapability(GroundCapability.CAN_BE_HARVESTED)){
			actions.add(new HarvestAction(map.locationOf(this)));
		}
		for(Item item : this.getInventory()) {
			if(item.hasCapability(ItemCapability.CRAFTABLE)) {
				actions.add(new CraftingAction(item));
			}
			checkRangedWeapon(item, actions, ItemCapability.LOADED_WITH_SHOTGUN_AMMO, ItemCapability.LOADED_IN_SHOTGUN);
			checkRangedWeapon(item, actions, ItemCapability.LOADED_WITH_RIFLE_AMMO, ItemCapability.LOADED_IN_RIFLE);
		}
		actions.add(new QuitGameAction());
		return menu.showMenu(this, actions, display);
	}

	private void checkRangedWeapon(Item item, Actions actions, ItemCapability itemCapability1, ItemCapability itemCapability2){
		if(item.hasCapability(itemCapability1)){
			for(Item item2 : this.getInventory()) {
				if (item2.hasCapability(itemCapability2)) {
					actions.add(new FireRangedWeaponAction(item, item2));
					break;
				}
			}
		}
	}
}
