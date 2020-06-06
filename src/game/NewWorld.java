package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;

import java.util.ArrayList;

public class NewWorld extends World{
	private GameMap compoundMap;

	public NewWorld(Display display) {
		super(display);
	}

	public void setCompoundMap(GameMap map){
		this.compoundMap = map;
	}
	@Override
	protected boolean stillRunning() {
		return !win() && !lose() && super.stillRunning();
	}

	@Override
	protected void processActorTurn(Actor actor) {
		try {
			super.processActorTurn(actor);
		}
		catch (IllegalArgumentException e){
			System.out.println("Player cannot move to the other map in this turn, please try in the next turn");
		}
	}

	@Override
	protected String endGameMessage() {
		String result = "";
		if (win()){
			result = "Player wins.";
		}
		else if (lose() || !player.isConscious()){
			result = "Player loses. Game Over.";
		}
		else if (!super.stillRunning()){
			result = "Game ends";
		}
		return result;
	}

	private ArrayList<Actor> getAllActors(){
		ArrayList<Actor> actorArrayList = new ArrayList<>();
		for (int x: compoundMap.getXRange()){
			for (int y: compoundMap.getYRange()) {
				if (compoundMap.at(x,y).getActor()!= null){
					Actor actor = compoundMap.at(x, y).getActor();
					actorArrayList.add(actor);
				}
			}
		}
		return actorArrayList;
	}

	private boolean win(){
		for (Actor actor: getAllActors()){
			if (actor.hasCapability(ZombieCapability.UNDEAD)){
				return false;
			}
		}
		return true;
	}

	private boolean lose(){
		for (Actor actor: getAllActors()){
			if (actor.hasCapability(ZombieCapability.ALIVE) && actor != player){
				return false;
			}
		}
		return true;
	}
}
