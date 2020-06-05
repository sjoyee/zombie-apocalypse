package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import java.util.Random;

/**
 * A corpse item which is portable and created when an actor is dead.
 */
public class Corpse extends PortableItem {

    /**
     * An integer that specify the turn of the game which increases in each turn if it lies on the ground, and initialised to be {@code 0}.
     */
    private int turn = 0;

    /**
     * Used to generate random number.
     */
    private Random random = new Random();

    /**
     * Create a Corpse object using a String that specifies the name of the Corpse and a char that specifies the display character of the Corpse.
     *
     * @param name a String that specifies the name of the Corpse
     */
    public Corpse(String name) {
        super(name, '%');
    }

    /**
     * Allow Corpse to experience the passage of time and rise from dead.
     * {@code turn} will not increase if Corpse is not on the ground.
     * A new {@link Zombie} object is added on {@code currentLocation} and the Corpse is removed, looking as though Corpse
     * rises from the dead 5 to 10 {@code turn} later, given that Corpse is on the ground.
     *
     * @param currentLocation The location of the ground on which we lie.
     */
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
        hi = 10;
        range = random.nextInt(hi-lo) + lo;
        if (turn == range || turn == 10) {
            if (!currentLocation.containsAnActor()) {
                Zombie turnedZombie = new Zombie(name);
                currentLocation.addActor(turnedZombie);
                currentLocation.removeItem(this);
                System.out.println("Oh no! " + name + " becomes a zombie!");
            }
            else if (currentLocation.containsAnActor() && turn == 10) {
                turn -= 1;
            }
        }

    }
}
