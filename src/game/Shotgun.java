package game;

import edu.monash.fit2099.engine.WeaponItem;

public class Shotgun extends WeaponItem {

    /**
     * Constructor.
     */
    public Shotgun() {
        super("shotgun", 'g', 30, "whacks");
        this.addCapability(ItemCapability.LOADED_WITH_SHOTGUN_AMMO);
    }
}