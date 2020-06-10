package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Special Action that allows Player to quit the game.
 */
public class QuitGameAction extends Action {

    /**
     * Allow the Player to quit the game by removing the Player from the map and return a string of description
     * indicating that Player quits the game.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string of description on this action for display
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return menuDescription(actor);
    }

    /**
     * Return a string of description on this action for display and for the Player to choose if Player decides to quit
     * this game.
     *
     * @param  actor The actor performing the action.
     * @return A string of description on this action for display
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " quits game";
    }
}
