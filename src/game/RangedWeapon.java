package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;

public class RangedWeapon extends WeaponItem {

    private Item ammunition;
    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     */
    public RangedWeapon(String name, char displayChar, int damage, String verb, Item ammunition) {
        super(name, displayChar, damage, verb);
        this.ammunition = ammunition;
    }

    public void loadAmmunition(Actor actor, ItemCapability itemCapability){
        if (actor.getInventory().contains(ammunition)){
            this.addCapability(itemCapability);
        }
        else{
            this.removeCapability(itemCapability);
        }
    }
}