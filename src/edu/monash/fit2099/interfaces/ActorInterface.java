package edu.monash.fit2099.interfaces;

/**
 * This interface provides the ability to add methods to Actor, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface ActorInterface {
    /**
     * Check whether a type actor is damaged.
     * @return the boolean value that whether the actor is damaged
     */
    boolean isDamaged();
}
