package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.NumberRange;

/**
 * Special Action that allows Actor to fire shotgun towards the direction chosen.
 *
 * @author Siang Jo Yee
 */
public class FireShotgunAction extends Action {

    /**
     * A String that specifies the direction where the shotgun is fired towards.
     */
    private String direction;

    /**
     * An integer that specifies the damage points of the target.
     */
    private int damagePoints;

    /**
     * A String that specifies the key used in the menu.
     */
    private String hotKey;

    /**
     * A boolean variable to check if x is point1 to ensure the coordinate is arranged to be (x,y), and not (y,x).
     */
    private boolean isxPoint1;

    /**
     * An Actor which is the target of the firing shotgun.
     */
    private Actor target;

    /**
     * Create a FireShotgunAction object using a String that specifies the direction where the shotgun is fired towards,
     * a String that specifies the key used in the menu and an integer that specifies the damage points of the target.
     *
     * @param direction a String that specifies the direction where the shotgun is fired towards.
     * @param hotKey a String that specifies the key used in the menu.
     * @param damagePoints an integer that specifies the damage points of the target.
     */
    public FireShotgunAction(String direction, String hotKey, int damagePoints){
        this.direction = direction;
        this.hotKey = hotKey;
        this.damagePoints = damagePoints;
    }

    /**
     * Allow the shotgun to hurt the target within its area of effect based on the direction chosen by the Player and
     * return description of the firing action of the shotgun.
     *
     * When firing towards the cardinal directions (N,E,S,W), {@link #fireTowardsCardinalDirection}
     * is called, else, when firing towards the ordinal directions (NW, NE, SW, SE), {@link #fireTowardsOrdinalDirection}
     * is called, with arguments to set the range of the area of effect and to hurt the actors within the range.
     * If the return value is empty, indicating that no actor is within the area of effect.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return The string of description on the firing action of the shotgun.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "", initialResult;
        Location here = map.locationOf(actor);
        int x, y;
        x = here.x();
        y = here.y();

        initialResult = actor + " fires shotgun towards " + direction + System.lineSeparator();

        if (direction.equals("North")){
            isxPoint1 = false;
            result = fireTowardsCardinalDirection(actor, map, y-3, x, y-1, y-2);
        }
        else if (direction.equals("South")){
            isxPoint1 = false;
            result = fireTowardsCardinalDirection(actor, map, y+1, x, y+1, y+2);
        }
        else if (direction.equals("West")){
            isxPoint1 = true;
            result = fireTowardsCardinalDirection(actor, map, x-3, y, x-1, x-2);
        }
        else if (direction.equals("East")){
            isxPoint1 = true;
            result = fireTowardsCardinalDirection(actor, map, x+1, y, x+1, x+2);
        }
        else if (direction.equals("North-West")){
            result = fireTowardsOrdinalDirection(actor, map, x-3, y-3, x, y);
        }
        else if (direction.equals("North-East")){
            result = fireTowardsOrdinalDirection(actor, map, x, y-3, x, y);
        }
        else if (direction.equals("South-West")){
            result = fireTowardsOrdinalDirection(actor, map, x-3, y, x,y);
        }
        else if (direction.equals("South-East")){
            result = fireTowardsOrdinalDirection(actor, map, x, y, x, y);
        }

        if (result.equals("")){
            return "No actor within area of effect.";
        }

        return initialResult + result;
    }

    /**
     * Return a string of description on this action for display and for the Player to choose based on the {@code direction}.
     *
     * @param actor The actor performing the action.
     * @return The string of description on this action for display.
     */
    @Override
    public String menuDescription(Actor actor) {
       return actor + " fires shotgun towards " + direction;
    }


    /**
     * Loop through all the x-coordinates and y-coordinates within the range of area of effect to get each actor
     * within the range as the {@code target} of the shotgun, then instantiate a {@link ShootAction} object with each
     * {@code target} as the argument to let them experience damage by the shotgun by {@code damagePoints} damage.
     *
     * This method is called if the shotgun is fired towards a cardinal direction.
     *
     * @param actor The actor performing this action.
     * @param map The map the actor is on.
     * @param point1Start The integer that specifies the starting value of the {@link NumberRange} object, {@code points1},
     *                    points1 is either the collections of points on the x-axis (for West and East) or on the y-axis (for North and South).
     * @param point2 The integer that specifies x-coordinate (for North and South) or the y-coordinate (for West and East) on the map where the {@code actor} is located
     * @param axis1WithExtraPoint The first axis (nearer to the player) which has extra points that are not within the area of effect
     * @param axis2WithExtraPoint The second axis (further from the player) which has extra points that are not within the area of effect
     * @return The string of description on the effects of hurting the target/s within the area of effect
     */
    private String fireTowardsCardinalDirection(Actor actor, GameMap map, int point1Start, int point2, int axis1WithExtraPoint, int axis2WithExtraPoint){
        NumberRange points1, points2;
        String result = "";
        points1 = new NumberRange(point1Start, 3);
        points2 = new NumberRange(point2 - 3, 7);
        for (int p1: points1){
            for (int p2: points2){
                if ((p1 == axis1WithExtraPoint) && (p2 == point2 - 2 || p2 == point2 - 3 || p2 == point2 + 2 || p2 == point2 + 3)){
                    continue;
                }
                if ((p1 == axis2WithExtraPoint) && (p2 == point2 - 3 || p2 == point2 + 3)){
                    continue;
                }
                try {
                    if (isxPoint1) {
                        target = map.at(p1, p2).getActor();
                    } else {
                        target = map.at(p2, p1).getActor();
                    }
                }
                catch (ArrayIndexOutOfBoundsException e){
                    // Ignore the location which is outside the range of the map
                }
                if (target != null){
                    Action shootAction = new ShootAction(target, damagePoints, null);
                    result += shootAction.execute(actor, map) + System.lineSeparator();
                }
            }
        }
        return result;
    }

    /**
     * Loop through all the x-coordinates and y-coordinates within the range of area of effect to get each actor
     * within the range as the {@code target} of the shotgun, then instantiate a {@link ShootAction} object with each
     * {@code target} as the argument to let them experience damage by the shotgun by {@code damagePoints} damage.
     *
     * This method is called if the shotgun is fired towards an ordinal direction.
     *
     * @param actor The actor performing this action.
     * @param map The map the actor is on.
     * @param xStart The integer that specifies the starting value of the {@link NumberRange} object for x-axis.
     * @param yStart The integer that specifies the starting value of the {@link NumberRange} object for y-axis.
     * @param x The integer that specifies x-coordinate on the map where the {@code actor} is located
     * @param y The integer that specifies y-coordinate on the map where the {@code actor} is located
     * @return The string of description on the effects of hurting the target/s within the area of effect
     */
    private String fireTowardsOrdinalDirection(Actor actor, GameMap map, int xStart, int yStart, int x, int y){
        NumberRange xs, ys;
        String result = "";
        xs = new NumberRange(xStart, 4);
        ys = new NumberRange(yStart, 4);
        for (int p1: xs) {
            for (int p2 : ys) {
                if ((p1 == x) && (p2 == y)) {
                    continue;
                }
                try{
                    target = map.at(p1, p2).getActor();
                }
                catch (ArrayIndexOutOfBoundsException e){
                    // Ignore the location which is outside the range of the map
                }
                if (target != null){
                    Action shootAction = new ShootAction(target, damagePoints, null);
                    result += shootAction.execute(actor, map) + System.lineSeparator();
                }
            }
        }
        return result;
    }

    /**
     * Returns the key used in the menu to trigger this Action.
     *
     * @return The key we use for this Action in the menu, or null to have it assigned for you.
     */
    @Override
    public String hotkey() {
        return hotKey;
    }
}
