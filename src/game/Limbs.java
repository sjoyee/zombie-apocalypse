package game;

import edu.monash.fit2099.engine.Item;

public class Limbs extends PortableItem{

	public Limbs(String name, char displayChar) {
		super(name, displayChar);
		this.setLimbs(displayChar);

	}
	private void setLimbs(char displayChar) {
		if(displayChar == 'A') {
			this.addCapability(LimbsCapability.ARM);
		}
		else if(displayChar == 'l') {
			this.addCapability(LimbsCapability.LEG);
		}
	}

}