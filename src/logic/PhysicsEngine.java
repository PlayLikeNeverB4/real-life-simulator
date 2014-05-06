package logic;

import java.util.List;

/**
 * An engine that handles the physics
 */
public class PhysicsEngine {

    /**
     * The game world for which this engine handles the physics
     */
    GameWorld gameWorld;

    /**
     * The collision detection manager
     */
    CollisionDetection collisionDetection;

    public PhysicsEngine(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        collisionDetection = new CollisionDetection(gameWorld);
    }

    /**
     * Updates the game world by detecting and resolving the collisions between objects
     */
    public void checkCollisions() {
        List<AbstractMovableObject> movableObjectList = gameWorld.getMovableObjectList();
        for(AbstractMovableObject movableObject : movableObjectList) {
            List<AbstractObject> collisions = collisionDetection.detect(movableObject);
//            if(collisions.size() > 0)
//                System.out.println("Collision!");
//            if(movableObject instanceof MainCharacter)
//                System.out.println("Checking MC: " + movableObject.getPosition());
            for(AbstractObject object : collisions) {
                // save the previous data
                double prevSpeed = movableObject.speed;

                movableObject.collidedWith(object);

                // save the computed data and restore the previous one
                double computedSpeed = movableObject.speed;
                movableObject.speed = prevSpeed;

                object.collidedWith(movableObject);

                // restore the computed data
                movableObject.speed = computedSpeed;
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
