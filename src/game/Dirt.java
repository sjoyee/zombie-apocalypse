package game;

import edu.monash.fit2099.engine.Ground;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	/**
	 * Create a Dirt object using a char that specifies the display character of Dirt, and adding the capability
	 * {@link GroundCapability#CAN_BE_SOWED_ON}.
	 */
	public Dirt() {
		super('.');
		addCapability(GroundCapability.CAN_BE_SOWED_ON);
	}

	/**
	 * Override method for future extensibility.
	 */
	@Override
	public void speedUpAge() {

	}
}
