package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import java.util.Random;

public class Corpse extends PortableItem {

    private int turn;
    private Random random = new Random();

    public Corpse(String name) {
        super(name, '%');
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        for (Item item: currentLocation.getItems()) {
            if (item == this) {
                turn++;
            }
        }
        int lo, hi, range;
        lo = 5;
        hi = 11;
        range = random.nextInt(hi-lo) + lo;
        if (turn == range && !currentLocation.containsAnActor()){
            Zombie turnedZombie = new Zombie(name);
            currentLocation.addActor(turnedZombie);
            currentLocation.removeItem(this);
            System.out.println("Oh no! " + name + " becomes a zombie!");
        }
    }
}
