package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * A shotgun which is a type of ranged weapon that has a short range.
 *
 * @author Siang Jo Yee
 */
public class Shotgun extends RangedWeapon {

    /**
     * Create a Shotgun object using a String that specifies its name, a char that specifies its display character, an
     * integer that specifies the amount of damage this shotgun does and an item which represents the ammunition
     * to be loaded into this shotgun.
     *
     * @param ammunition item representing the ammunition to be loaded into this shotgun
     */
    public Shotgun(Item ammunition) {
        super("shotgun", 'G', 30, ammunition);
    }

    /**
     * Inform this carried shotgun of the passage of time. This method is called once per turn, if this shotgun is being
     * carried. This method calls {@link RangedWeapon#loadAmmunition(Actor, ItemCapability)} method from the parent class to
     * indicate that it is loaded with ammunition.
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        loadAmmunition(actor, ItemCapability.LOADED_WITH_SHOTGUN_AMMO);
    }
}