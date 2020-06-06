package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.NumberRange;
import game.PortableItem;

public class FireShotgunAction extends AttackAction {

    private String direction;
    private int damagePoints;
    private String hotKey;
    private boolean isxPoint1;

    public FireShotgunAction(String direction, String hotKey, int damagePoints, Actor target){
        super(target);
        this.direction = direction;
        this.hotKey = hotKey;
        this.damagePoints = damagePoints;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        int x, y;
        x = here.x();
        y = here.y();

        if (rand.nextDouble() <= 0.25){
            return actor + " fails to fire shotgun ";
        }
        String result = "";
        if (direction.equals("North")){
            isxPoint1 = false;
            result = hurtTargetMainDirection(actor, map, y-3, x, y-1, y-2);
        }
        else if (direction.equals("South")){
            isxPoint1 = false;
            result = hurtTargetMainDirection(actor, map, y+1, x, y+1, y+2);
        }
        else if (direction.equals("West")){
            isxPoint1 = true;
            result = hurtTargetMainDirection(actor, map, x-3, y, x-1, x-2);
        }
        else if (direction.equals("East")){
            isxPoint1 = true;
            result = hurtTargetMainDirection(actor, map, x+1, y, x+1, x-2);
        }
        else if (direction.equals("North-West")){
            result = hurtTargetSubDirection(actor, map, x-3, y-3, x, y);
        }
        else if (direction.equals("North-East")){
            result = hurtTargetSubDirection(actor, map, x, y-3, x, y);
        }
        else if (direction.equals("South-West")){
            result = hurtTargetSubDirection(actor, map, x-3, y, x,y);
        }
        else if (direction.equals("South-East")){
            result = hurtTargetSubDirection(actor, map, x, y, x, y);
        }
        if (result.equals("")){
            return "No actor within area of effect.";
        }

        if (target != null) {
            if (!target.isConscious()) {
                Item corpse = new PortableItem("dead " + target, '%');
                result += System.lineSeparator() + deadActor(map, corpse);
            }
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
       return actor + " fires shotgun towards " + direction;
    }



    private String hurtTargetMainDirection(Actor actor, GameMap map, int point1Start, int point2, int pointToRemoveExtra1, int pointToRemoveExtra2){
        NumberRange points1, points2;
        String result = "";
        points1 = new NumberRange(point1Start, 3);
        points2 = new NumberRange(point2 - 3, 7);
        for (int p1: points1){
            for (int p2: points2){
                if ((p1 == pointToRemoveExtra1) && (p2 == point2 - 2 || p2 == point2 - 3 || p2 == point2 + 2 || p2 == point2 + 3)){
                    continue;
                }
                if ((p1 == pointToRemoveExtra2) && (p2 == point2 - 3 || p2 == point2 + 3)){
                    continue;
                }
                if (isxPoint1) {
                    try {
                        target = map.at(p1, p2).getActor();
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("Area at (" + p1 + "," + p2 + ") is out of range of the map");
                    }
                } else {
                    try {
                        target = map.at(p2, p1).getActor();
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("Area at (" + p2 + "," + p1 + ")is out of range of the map");
                    }
                }
                if (target != null){
                    target.hurt(damagePoints);
                    result += System.lineSeparator() + actor + " shoot " + target + " for " + damagePoints + " damage.";
                }
            }
        }
        return result;
    }


    private String hurtTargetSubDirection(Actor actor, GameMap map, int xStart, int yStart, int x, int y){
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
                    System.out.println("Area at (" + p1 + "," + p2 + ") is out of range of the map");
                }
                if (target != null){
                    target.hurt(damagePoints);
                    result += System.lineSeparator() + actor + " shoot " + target + " for " + damagePoints + " damage.";
                }
            }
        }
        return result;
    }

    @Override
    public String hotkey() {
        return hotKey;
    }
}
