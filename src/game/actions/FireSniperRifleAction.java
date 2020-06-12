package game.actions;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.Item;
import game.AimCapability;

public class FireSniperRifleAction extends Action{
	private Actor target;
	private int damage;
	private Item ammo;
	private Menu menu = new Menu();
	
	public FireSniperRifleAction(Actor target, int damage, Item ammo) {
		this.target = target;
		this.damage = damage;
		this.ammo = ammo;
	}

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
//		if(target.hasCapability(AimCapability.ROUND1)) {
//			actions.add(new AimAction(target));
//			actions.add(new ShootAction(target,damage, ammo));
//		}
//		else if(target.hasCapability(AimCapability.ROUND2) && actor.hasCapability(AimCapability.CONCENTRATION)) {
//			actions.add( new ShootAction(target,damage, ammo));
//		}
//		else if(!target.hasCapability(AimCapability.ROUND1) && !target.hasCapability(AimCapability.ROUND2 )){
//			actions.add(new AimAction(target));
//			actions.add(new ShootAction(target,damage, ammo));
//		}
		menuAction = menu.showMenu(actor, actions, display);
        if (menuAction != null){
            return menuAction.execute(actor, map);
        }

		return result;
	}
	@Override
	public String menuDescription(Actor actor) {
		return "Choose " + target + " as target";
	}

}
