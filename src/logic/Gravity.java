package logic;

import java.util.Collection;

/**
 * Manages the gravity
 */
public class Gravity {

    /**
     * The speed of a falling object
     */
    private static final double SPEED = 0.3;

    /**
     * The game world for which it simulates the gravity
     */
    GameWorld gameWorld;

    public Gravity(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    /**
     * Simulates gravity over a period of time
     * @param time The time amount that needs to be simulated
     */
    public void apply(double time) {
        double distance = time * SPEED;
        Collection<AbstractMovableObject> movableObjectList = gameWorld.getMovableObjectList();

        for(AbstractMovableObject movableObject : movableObjectList)
            movableObject.move(0, 0, -distance);
    }

}
