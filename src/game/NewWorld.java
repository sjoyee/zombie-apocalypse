package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.World;

import java.util.ArrayList;
import java.util.Random;

public class NewWorld extends World{

    /**
     * The compound map.
     */
    private GameMap compoundMap;

	/**
	 *  Mambo Marie
	 */
	private Actor marie;
	/**
	 * Mambo Marie previous turn location
	 */
	private Location mamboLocation;

	public NewWorld(Display display) {
		super(display);
	}
	
	/**
	 * Runs the world as parent class
	 * If mambo marie is not in the game, add her to the game 
	 */
	@Override
	public void run() {
		if (player == null)
			throw new IllegalStateException();

		// initialize the last action map to nothing actions;
		for (Actor actor : actorLocations) {
			lastActionMap.put(actor, new DoNothingAction());
		}

		// This loop is basically the whole game
		while (stillRunning()) {
			GameMap playersMap = actorLocations.locationOf(player).map();
			playersMap.draw(display);

			// Process all the actors.
			getMambo();
			for (Actor actor : actorLocations) {
				if (stillRunning())
					processActorTurn(actor);
			}

			// Tick over all the maps. For the map stuff.
			for (GameMap gameMap : gameMaps) {
				gameMap.tick();
			}

		}
		display.println(endGameMessage());
	}
	/**
	 *  Mambo marie returns the game if it has vanished before
	 */
	private void getMambo() {
		boolean ret = false;
		for(Actor actor : actorLocations) {
			if(actor.hasCapability(ZombieCapability.MAMBO)) {
				marie = actor;
				mamboLocation = actorLocations.locationOf(actor);
				ret = true;
			}

		}
		if(!ret && marie.hasCapability(ZombieCapability.VANISH)) {
			double chances = Math.random();
			if(chances <=0.05) {
				try {
					actorLocations.add(marie,mamboLocation);
				}
				catch(IllegalArgumentException e) {
					System.out.println(" Mambo marie can't return if an actor is on its location. ");
				}
				if(mamboLocation.getActor().equals(marie)) {
					marie.removeCapability(ZombieCapability.VANISH);
					System.out.println("Mambo Marie has reappear!!!!!!!!!!");
				}
				
			}

		}
		
	}

    /**
     * Set the compound map.
     *
     * @param map the compound map.
     */
	public void setCompoundMap(GameMap map){
		this.compoundMap = map;
	}

    /**
     * Returns true if the game is still running.
     *
     * The game is considered to be still running when the player has not won, lost or removed from the map.
     *
     * @return true if the player has not won, lost or removed from the map.
     */
	@Override
	protected boolean stillRunning() {
		return !win() && !lose() && super.stillRunning();
	}

    /**
     * Execute the same functionality as {@link World#processActorTurn} to give an Actor its turn.
     *
     * Inherits the method to catch {@code IllegalArgumentException} if player is moving from map to map and the location
     * where the actor is moving to already contains an Actor.
     *
     * @param actor the Actor whose turn it is.
     */
	@Override
	protected void processActorTurn(Actor actor) {
		try {
			super.processActorTurn(actor);
		}
		catch (IllegalArgumentException e){
			System.out.println("Player cannot move to the other map in this turn, please try in the next turn");
		}
	}

    /**
     * Return a string that can be displayed when the game ends.
     *
     * @return the string based on the status of the game.
     */
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

    /**
     * Get all actors located in the compound map and return a collection of those actors.
     *
     * @return the collection of actors located in the compound map.
     */
	private ArrayList<Actor> getAllActors(){
		ArrayList<Actor> actorArrayList = new ArrayList<>();
		for (int x: compoundMap.getXRange()){
			for (int y: compoundMap.getYRange()) {
				if (compoundMap.at(x,y).containsAnActor()){
					actorArrayList.add(compoundMap.at(x, y).getActor());
				}
			}
		}
		return actorArrayList;
	}

    /**
     * Check whether the player wins the game.
     * Player wins the game when all zombies and the Mambo Marie are killed.
     *
     * @return true if player wins, else false.
     */
	private boolean win(){
		for (Actor actor: getAllActors()){
			if (actor.hasCapability(ZombieCapability.UNDEAD) || actor.hasCapability(ZombieCapability.MAMBO)){
				return false;
			}
			else if (!actor.hasCapability(ZombieCapability.MAMBO) && actor.hasCapability(ZombieCapability.VANISH)){
				return false;
			}
		}
		return true;
	}

    /**
     * Check whether the player loses the game.
     * Player loses the game when all humans and its subclass are killed.
     * @return true if player loses, else false.
     */
	private boolean lose(){
		for (Actor actor: getAllActors()){
			if (actor.hasCapability(ZombieCapability.ALIVE) && actor != player){
				return false;
			}
		}
		return true;
	}
}
