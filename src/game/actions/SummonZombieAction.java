package game.actions;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Zombie;

/** Special Action or summoning Zombie 
 * @author Lua Shi Liang
 *
 */
public class SummonZombieAction extends Action{
	/**
	 * The number of Zombies to be summoned
	 */
	private int numOfZombies;
	
	/** Constructor
	 * @param num The number of Zombies to be summoned
	 */
	public SummonZombieAction(int num) {
		numOfZombies = num;
		
	}
	

	/**
	 * Zombies are summoned randomly all over the map
	 * The names of the zombies are generated randomly.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * 
     * @return a string of description on this action for display
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = numOfZombies + " zombies are summoned";
		int x, y;
		String[] zombies = new String[numOfZombies];
		for(int i = 0; i<numOfZombies;i++) {
			String characters ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String randomString="";
			
			Random rand = new Random();
			
			int length = 6;
			char[] text = new char[length];
			for(int k = 0; k < length; k++) {
				text[k] = characters.charAt(rand.nextInt(characters.length()));
			}
			for(int j = 0; j < text.length; j++) {
				randomString += text[j];
			}
			zombies[i] = randomString;
		
		}
		for (String name: zombies){
			do {
				x = (int) Math.floor(Math.random() *78 + 1);
				y = (int) Math.floor(Math.random() * 23 + 1);
			}
			while (map.at(x, y).containsAnActor());
			map.at(x,  y).addActor(new Zombie(name));
			result += System.lineSeparator() + "Zombie " + name + " is summoned!";
		}
		return result;
	}

    /**
     * Return a string of description on this action for display.
     *
     * @param actor The actor performing the action.
     * @return a string of description on this action for display
     */
	@Override
	public String menuDescription(Actor actor) {
		return actor + "can summon Zombies";
	}

}