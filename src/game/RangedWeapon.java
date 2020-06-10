package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * An ranged weapon which is a type of weapon item which uses ammunition.
 *
 * @author Siang Jo Yee
 */
public class RangedWeapon extends WeaponItem {

    /**
     * An item which represents the ammunition to be loaded into this ranged weapon.
     */
    private Item ammunition;

    /**
     * Create a RangedWeapon object which is not portable using a String that specifies its name, a char
     * that specifies its display character, an integer that specifies the amount of damage this ranged weapon does as a
     * melee weapon and an item which represents the ammunition to be loaded into this ranged weapon.
     *
     * @param name        name of the weapon item
     * @param displayChar character to use for display when this ranged weapon is on the ground
     * @param damage      amount of damage this ranged weapon does as a melee weapon
     * @param ammunition  item representing the ammunition to be loaded into this ranged weapon
     */
    public RangedWeapon(String name, char displayChar, int damage, Item ammunition) {
        super(name, displayChar, damage, "whacks");
        this.ammunition = ammunition;
    }

    /**
     * Add the capability to this ranged weapon which indicates that it is loaded with the respective ammunition if the
     * actor's inventory contains the ammunition.
     *
     * @param actor          the actor carrying this ranged weapon
     * @param itemCapability the capability of the ranged weapon which indicates that it is loaded with the respective ammunition
     */
    public void loadAmmunition(Actor actor, ItemCapability itemCapability){
        if (actor.getInventory().contains(ammunition)){
            this.addCapability(itemCapability);
        }
        else{
            this.removeCapability(itemCapability);
        }
    }
}