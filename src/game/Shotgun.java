package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class Shotgun extends RangedWeapon {
    /**
     * Constructor.
     */
    public Shotgun(Item ammunition) {
        super("shotgun", 'G', 30, "whacks", ammunition);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        loadAmmunition(actor, ItemCapability.LOADED_WITH_SHOTGUN_AMMO);
    }
}