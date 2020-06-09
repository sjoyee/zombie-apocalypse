package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.Random;

public class Bomb extends Item {

    private Random rand = new Random();
    /***
     * Constructor.
     *
     */
    public Bomb() {
        super("bomb", 'b', false);
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        final int DAMAGE = 10;
        ArrayList<Location> locationList = new ArrayList<>();
        locationList.add(currentLocation);
        for (Exit exit: currentLocation.getExits()){
            locationList.add(exit.getDestination());
        }
        if (rand.nextDouble() <= 0.9 && currentLocation.containsAnActor()) {
            System.out.println("BOOM! A bomb explodes! " + currentLocation.getActor() + " steps on the bomb!");
            for (Location location : locationList) {
                location.setGround(new Dirt());
                location.getGround().removeCapability(GroundCapability.CAN_BE_SOWED_ON);
                for (Item item: location.getItems()){
                    if (item != this) {
                        location.removeItem(item);
                    }
                }
                currentLocation.removeItem(this);
                if (location.containsAnActor()) {
                    Actor actor = location.getActor();
                    actor.hurt(DAMAGE);
                    System.out.println("Exploded bomb hurts " + actor + " for " + DAMAGE + " damage");
                }
            }
        }
    }
}
