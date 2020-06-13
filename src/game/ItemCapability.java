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
	 *  Shotgun ammo
	 */
    LOADED_WITH_SHOTGUN_AMMO,
	/**
	 *  Sniper rifle ammo
	 */
    LOADED_WITH_RIFLE_AMMO,
	/**
	 *  Shotgun
	 */
    LOADED_IN_SHOTGUN,
	/**
	 *  Sniper rifle
	 */
    LOADED_IN_RIFLE
}
