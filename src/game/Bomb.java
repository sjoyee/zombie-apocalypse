package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import java.util.ArrayList;

/**
 * A bomb which is a type of Item and hurt Actors when it explodes.
 *
 * @author Siang Jo Yee
 */
public class Bomb extends Item {

    /***
     * Create a Bomb object which is not portable using a String that specifies its name and a char that specifies it display character.
     */
    public Bomb() {
        super("bomb", 'b', false);
    }

    /**
     * Allow Bomb to experience the passage of time and has 20% probability to explode when an actor steps on it.
     * Once the bomb explodes, any type of items and grounds on the locations that are included in {@code locationList}
     * will vanish and turn to {@code Dirt} respectively. These {@code Dirt} would lose the capability to allow crop to
     * be sowed on it anymore due to the explosion of bomb. The bomb will also hurt all the actors located on those locations.
     *
     * The locations in {@code locationList} are the current location and the adjacent locations where the bomb is located.
     *
     * @param currentLocation The location of the ground on which this bomb is located.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        final int DAMAGE = 10;
        ArrayList<Location> locationList = new ArrayList<>();
        locationList.add(currentLocation);
        for (Exit exit: currentLocation.getExits()){
            locationList.add(exit.getDestination());
        }
        if (Math.random() <= 0.2 && currentLocation.containsAnActor()) {
            System.out.println("BOOM! A bomb explodes! " + currentLocation.getActor() + " steps on the bomb!");
            for (Location location : locationList) {
                location.setGround(new Dirt());
                location.getGround().removeCapability(GroundCapability.CAN_BE_SOWED_ON);
                // iterate backwards to avoid ConcurrentModificationException
                for (int i = location.getItems().size()-1; i >= 0; i--){
                    location.removeItem(location.getItems().get(i));
                }
                if (location.containsAnActor()) {
                    Actor actor = location.getActor();
                    actor.hurt(DAMAGE);
                    System.out.println("Exploded bomb hurts " + actor + " for " + DAMAGE + " damage");
                }
            }
        }
    }
}
