package game;

import edu.monash.fit2099.engine.WeaponItem;

public class SniperRifle extends WeaponItem{

	public SniperRifle() {
		super("Sniper", 'R', 20, "shot");
		this.addCapability(ItemCapability.LOADED_WITH_RIFLE_AMMO);
	}

}