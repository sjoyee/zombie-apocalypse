package game.behaviours;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.ZombieCapability;
import game.actions.AttackAction;
import game.actions.ZombieAttackAction;

/**
 * A class that generates an AttackAction if the current Actor is standing
 * next to an Actor that they can attack.
 * 
 * @author ram
 *
 */
public class AttackBehaviour implements Behaviour {
	private ZombieCapability attackableTeam;
	
	/**
	 * Constructor.
	 * 
	 * Sets the team (i.e. ZombieCapability) that the owner of this
	 * Behaviour is allowed to attack.
	 * 
	 * @param attackableTeam Team descriptor for ZombieActors that can be attacked
	 */
	public AttackBehaviour(ZombieCapability attackableTeam) {
		this.attackableTeam = attackableTeam;
	}

	/**
	 * Returns a ZombieAttackAction if the actor is a Zombie that attacks an adjacent attackable Actor
	 * Returns an AttackAction that attacks an adjacent attackable Actor.
	 * 
	 * Actors are attackable if their ZombieCapability matches the 
	 * "undeadness status" set 
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// Is there an attackable Actor next to me?
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
		Collections.shuffle(exits);
		
		for (Exit e: exits) {
			if (!(e.getDestination().containsAnActor()))
				continue;
			if (e.getDestination().getActor().hasCapability(attackableTeam) && actor.hasCapability(ZombieCapability.UNDEAD)) {
				return new ZombieAttackAction(e.getDestination().getActor());
			}
			if (e.getDestination().getActor().hasCapability(attackableTeam)) {
				return new AttackAction(e.getDestination().getActor());
			}
		}
		return null;
	}

}
