package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * A sniple rifle which is a type of ranged weapon that rewards patience.
 *
 * @author Lua Shi Liang
 */
public class SniperRifle extends RangedWeapon{

	/**
	 * Create a SnipleRifle object using a String that specifies its name, a char that specifies its display character, an
	 * integer that specifies the amount of damage this sniple rifle does and an item which represents the ammunition
	 * to be loaded into this sniple rifle.
	 *
	 * @param ammunition item representing the ammunition to be loaded into this sniper rifle
	 */
	public SniperRifle(Item ammunition) {
		super("sniper rifle", 'R', 20, ammunition);
	}

	/**
	 * Inform this carried sniple rifle of the passage of time. This method is called once per turn, if this sniple rifle is being
	 * carried. This method calls {@link RangedWeapon#loadAmmunition(Actor, ItemCapability)} method from the parent class to
	 * indicate that it is loaded with ammunition.
	 *
	 * @param currentLocation The location of the actor carrying this sniple rifle.
	 * @param actor The actor carrying this sniple rifle.
	 */
	@Override
	public void tick(Location currentLocation, Actor actor) {
		super.tick(currentLocation, actor);
		loadAmmunition(actor, ItemCapability.LOADED_WITH_RIFLE_AMMO);
	}
}