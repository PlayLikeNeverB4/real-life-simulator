package logic;

import logic.utils.CollisionDetectionUtils;

import java.util.List;

/**
 * An engine that handles the physics
 */
public class PhysicsEngine {

    /**
     * The game world for which this engine handles the physics
     */
    GameWorld gameWorld;

    public PhysicsEngine(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    /**
     * Updates the game world by detecting and resolving the collisions between objects
     */
    public void checkCollisions() {
        List<AbstractMovableObject> movableObjectList = gameWorld.getMovableObjectList();
        for(AbstractMovableObject movableObject : movableObjectList) {
            List<AbstractObject> collisions = CollisionDetectionUtils.detect(movableObject, gameWorld);
//            if(collisions.size() > 0)
//                System.out.println("Collision!");
//            if(movableObject instanceof MainCharacter)
//                System.out.println("Checking MC: " + movableObject.getPosition());
            for(AbstractObject object : collisions) {
                // save the previous data
                double prevSpeed = movableObject.speed;
                double prevDirection = movableObject.direction;

                movableObject.collidedWith(object);

                // save the computed data and restore the previous one
                double computedSpeed = movableObject.speed;
                double computedDirection = movableObject.direction;
                movableObject.speed = prevSpeed;
                movableObject.direction = prevDirection;

                object.collidedWith(movableObject);

                // restore the computed data
                movableObject.speed = computedSpeed;
                movableObject.direction = computedDirection;
            }
        }
    }

    /**
     * Simulates gravity over a period of time
     * @param time The time amount that needs to be simulated
     */
    public void applyGravity(double time) {
        Gravity gravity = new Gravity(gameWorld);
        gravity.apply(time);
    }
}
