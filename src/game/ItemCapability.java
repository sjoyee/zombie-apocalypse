package game;

/**
 * An enum representing a collection of constants which indicate the capabilities of an Item.
 *
 * @author Siang Jo Yee
 * @author Lua Shi Liang
 */
public enum ItemCapability {
    /**
     * An Item is capable of being edible.
     */
    EDIBLE,

    /**
     * An Item is capable of being craftable.
     */
    CRAFTABLE,
	/**
	 * RangedWeapon item is loaded with shotgun ammo
	 */
    LOADED_WITH_SHOTGUN_AMMO,
	/**
	 * RangedWeapon item is loaded with sniper rifle ammo
	 */
    LOADED_WITH_RIFLE_AMMO,
	/**
	 * Ammo is loaded shotgun
	 */
    LOADED_IN_SHOTGUN,
	/**
	 * Ammo is loaded in Sniper rifle
	 */
    LOADED_IN_RIFLE
}
