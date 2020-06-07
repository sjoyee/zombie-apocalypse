package game.actions;

import edu.monash.fit2099.engine.*;
import game.ItemCapability;

public class FireRangedWeaponAction extends Action {

    private Item rangedWeapon;
    private Item ammo;
    private Menu menu = new Menu();

    public FireRangedWeaponAction(Item rangedWeapon, Item ammo){
        this.rangedWeapon = rangedWeapon;
        this.ammo = ammo;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        Actions actions = new Actions();
        Display display = new Display();
        Action action, menuAction;
        if (rangedWeapon.hasCapability(ItemCapability.LOADED_WITH_SHOTGUN_AMMO)){
            display.println("Ranged Weapon available: " + rangedWeapon);
            for (Exit exit: map.locationOf(actor).getExits()){
                action = new FireShotgunAction(exit.getName(), exit.getHotKey(), rangedWeapon.asWeapon().damage(), null);
                actions.add(action);
            }
            actor.removeItemFromInventory(ammo);
        }
//        else if (rangedWeapon.hasCapability(ItemCapability.LOADED_WITH_RIFLE_AMMO)){
//            display.println("Ranged Weapon available: " + rangedWeapon);
//            int range = 5;
//            int x=map.locationOf(actor).x();
//    		int y=map.locationOf(actor).y();
//    		int startx = x-range;
//    		int starty = y-range;
//    		for(int i = startx ; i<startx+(2*range)+1; i++) {
//    			for(int j = starty ; j<starty+(2*range)+1; j++) {
//    				boolean ret = false;
//    				try {
//    					ret = map.at(i, j).containsAnActor();
//    				}
//    				catch(ArrayIndexOutOfBoundsException e){
//    					}
//    				if(ret && map.at(i,j)!=map.at(x, y)) {
//    					action = new FireSniperRifleAction(map.at(i, j).getActor(), rangedWeapon.asWeapon().damage());
//    					actions.add(action);
//    				}
//    			}
//    		}
//        }
        menuAction = menu.showMenu(actor, actions, display);
        if (menuAction != null){
            return menuAction.execute(actor, map);
        }
        return "Ranged weapon/s is not loaded with ammunition";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " fire " + rangedWeapon;
    }
}
