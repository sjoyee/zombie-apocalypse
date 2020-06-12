package game.actions;

import edu.monash.fit2099.engine.*;
import game.AimCapability;
import game.ItemCapability;

/**
 * Special Action that allows Actor to fire ranged weapon.
 *
 * @author Siang Jo Yee
 * @author Lua Shi Liang
 */
public class FireRangedWeaponAction extends Action {

    /**
     * An item which represents the ranged weapon.
     */
    private Item rangedWeapon;

    /**
     * An item which represents the ammunition.
     */
    private Item ammo;

    /**
     * A submenu created if this action is executed.
     */
    private Menu menu = new Menu();

    /**
     * Create a FireRangedWeaponAction object using an item that specifies the ranged weapon and an item which specifies
     * the ammunition.
     *
     * @param rangedWeapon An item which represents the ranged weapon
     * @param ammo An item which represents the ammunition
     */
    public FireRangedWeaponAction(Item rangedWeapon, Item ammo){
        this.rangedWeapon = rangedWeapon;
        this.ammo = ammo;
    }

    /**
     * Display the submenu for the chosen ranged weapon if it is loaded with ammunition and fulfil the criteria to be
     * fired if there is any. For shotgun, it could be fired towards eight directions, which are the cardinal and
     * ordinal directions from the coordinate of the {@code actor}. For sniple rifle, # pls continue thanks
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return The string of description on the action of the ranged weapon after chosen to be fired.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Actions actions = new Actions();
        Display display = new Display();
        Action action, menuAction;
        if (rangedWeapon.hasCapability(ItemCapability.LOADED_WITH_SHOTGUN_AMMO)){
            display.println(actor + " choose to fire " + rangedWeapon);
            for (Exit exit: map.locationOf(actor).getExits()){
                action = new FireShotgunAction(exit.getName(), exit.getHotKey(), rangedWeapon.asWeapon().damage() * 2);
                actions.add(action);
            }
            actor.removeItemFromInventory(ammo);
            if(actor.hasCapability(AimCapability.CONCENTRATION)){
                actor.removeCapability(AimCapability.CONCENTRATION);
            }
        }
        else if (rangedWeapon.hasCapability(ItemCapability.LOADED_WITH_RIFLE_AMMO)){
            display.println(actor + " choose to fire " + rangedWeapon);
            int range = 5;
            int x = map.locationOf(actor).x();
    		int y = map.locationOf(actor).y();
    		int startx = x - range;
    		int starty = y - range - 1;
    		boolean noActorWithinRange = true;
    		for(int i = startx ; i < startx + (2 * range) +1; i++) {
    			for(int j = starty ; j < starty + (2 * range) +1 ; j++) {
    				boolean ret = false;
    				try {
    					ret = map.at(i, j).containsAnActor();
    				}
    				catch (ArrayIndexOutOfBoundsException e){
    				    // Ignore the location which is outside the range of the map
                    }
    				if(ret && map.at(i, j)!= map.at(x, y)) {
    					actions.add(new FireSniperRifleAction(map.at(i, j).getActor(), rangedWeapon.asWeapon().damage(), ammo));
    					noActorWithinRange = false;
    				}
    			}
    		}
    		if (noActorWithinRange){
    		    return "No actor within target range";
            }
        }
        menuAction = menu.showMenu(actor, actions, display);
        return menuAction.execute(actor, map);
    }

    /**
     * Return a string of description on this action for display and for the Player to choose the ranged weapon to be fired.
     *
     * @param actor The actor performing the action.
     * @return A string of description on this action for display
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " fires " + rangedWeapon;
    }
}
