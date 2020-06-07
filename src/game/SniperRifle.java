package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class SniperRifle extends RangedWeapon{

	public SniperRifle(Item ammunition) {
		super("Sniper", 'R', 20, "shot", ammunition);
	}

	@Override
	public void tick(Location currentLocation, Actor actor) {
		super.tick(currentLocation, actor);
		loadAmmunition(actor, ItemCapability.LOADED_WITH_RIFLE_AMMO);
	}
}