package game;

public class Food extends PortableItem{

    public Food() {
        super("food", 'o');
        addCapability(ItemCapability.EDIBLE);
//        allowableActions.add(new EatAction(this));
    }
}

