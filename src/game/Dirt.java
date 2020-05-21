package game;

import edu.monash.fit2099.engine.Ground;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	/**
	 * Constructor.
	 * Create a Dirt object in which a crop can be sowed on it.
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
