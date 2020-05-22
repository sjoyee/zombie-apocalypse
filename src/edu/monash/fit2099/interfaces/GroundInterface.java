package edu.monash.fit2099.interfaces;

/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface GroundInterface {
    /**
     * Allow a type of Ground to speed up its age.
     */
    void speedUpAge();
}
