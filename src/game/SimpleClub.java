package game;

import edu.monash.fit2099.engine.WeaponItem;
import game.actions.CraftingAction;

public class SimpleClub extends WeaponItem{

	public SimpleClub(char display) {
		super("SimpleClub", display, 20, "SimpleClub whacks");
		allowableActions.add( new CraftingAction());
		//this.addCapability(Craftable.UNPICKABLE);
		this.addCapability(ItemCapability.CRAFTABLE);
//		setLimbsCapability(displayChar);
	}
//	private void setLimbsCapability(char displayChar) {
//		if(displayChar == 'A') {
//			this.addCapability(LimbsCapability.ARM);
//		}
//		else if(displayChar == 'l') {
//			this.addCapability(LimbsCapability.LEG);
//		}
//	}

}
