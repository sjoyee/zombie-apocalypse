package game.actions;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.Item;
import game.AimCapability;

/**
 * Special Action for firing sniper rifle
 * @author Lua Shi Liang 
 *
 */
public class FireSniperRifleAction extends Action{
	/**
	 * The target fired at
	 */
	private Actor target;
	/**
	 *  The damage of the sniper rifle
	 */
	private int damage;
	/**
	 *  The ammo of sniper rifle
	 */
	private Item ammo;
    /**
     * A submenu created if this action is executed.
     */
	private Menu menu = new Menu();
	
	/** Constructor 
	 * @param target The target getting fired
	 * @param damage The damage of the sniper rifle
	 * @param ammo The ammo of sniper rifle
	 */
	public FireSniperRifleAction(Actor target, int damage, Item ammo) {
		this.target = target;
		this.damage = damage;
		this.ammo = ammo;
	}

	/** Depending on the actor and the target's AimCapablity, actor will have 2 possible
	 * options after choosing to fire sniper rifle
	 * 
	 *First possible option is AimAction
	 *Second possible option is ShootAction
	 *if target has not been aimed before, they can be aimed or shoot
	 *If target is aimied for the first time, they can be aimed or shoot
	 *if target is aimed for the second time, they can be shoot only
	 *
	 * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * 
     * @return The string of description on the menu action of the sniper rifle.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Actions actions = new Actions();
		Display display = new Display();
		Action menuAction;
		String result="NO action";
		if(!actor.hasCapability(AimCapability.CONCENTRATION)){
			actions.add(new AimAction(target));
			actions.add(new ShootAction(target,damage, ammo));
		}
		else{
			if(target.hasCapability(AimCapability.ROUND1)) {
				actions.add(new AimAction(target));
				actions.add(new ShootAction(target,damage, ammo));
			}
			else if(target.hasCapability(AimCapability.ROUND2)){
				actions.add(new ShootAction(target,damage, ammo));
			}
			else {
				actions.add(new AimAction(target));
				actions.add(new ShootAction(target,damage, ammo));
			}
		}
		menuAction = menu.showMenu(actor, actions, display);
        if (menuAction != null){
            return menuAction.execute(actor, map);
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
		return "Choose " + target + " as target";
	}

}
