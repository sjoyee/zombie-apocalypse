package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.WeaponItem;

public class Shotgun extends WeaponItem {

    private Item ammunition;
    /**
     * Constructor.
     */
    public Shotgun(Item ammunition) {
        super("shotgun", 'g', 30, "whacks");
        this.ammunition = ammunition;
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        if (actor.getInventory().contains(ammunition)){
            this.addCapability(ItemCapability.LOADED_WITH_SHOTGUN_AMMO);
        }
        else{
            this.removeCapability(ItemCapability.LOADED_WITH_SHOTGUN_AMMO);
        }
    }
}