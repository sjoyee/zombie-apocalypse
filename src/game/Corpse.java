package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import java.util.Random;

public class Corpse extends Item {

    private int turn;
    private Actor initialActor;
    private Random random = new Random();

    public Corpse(String name, Actor initialActor) {
        super(name, '%', false);
        this.initialActor = initialActor;
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);

        turn ++;

        if(!initialActor.hasCapability(ZombieCapability.UNDEAD)){
            int lo = 5;
            int hi = 11;
            int range = random.nextInt(hi-lo) + lo;
            if (turn == range && !currentLocation.containsAnActor()){
                Zombie turnedZombie = new Zombie(name);
                currentLocation.addActor(turnedZombie);
                currentLocation.removeItem(this);
                System.out.println("Oh no! " + name + " becomes a zombie!");
            }
        }
    }
}
