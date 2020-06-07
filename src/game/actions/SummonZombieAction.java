package game.actions;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Zombie;

public class SummonZombieAction extends Action{
	private int numOfZombies;
	
	public SummonZombieAction(int num) {
		numOfZombies = num;
		
	}
	
	

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
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			}
			while (map.at(x, y).containsAnActor());
			map.at(x,  y).addActor(new Zombie(name));
			result += System.lineSeparator() + "Zombie" + name + " is summoned!";
		}
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + "can summon Zombies";
	}

}