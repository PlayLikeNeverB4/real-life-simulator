package logic;

import java.util.List;

public class PhysicsEngine {

    GameWorld gameWorld;

    CollisionDetection collisionDetection;

    public PhysicsEngine(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        collisionDetection = new CollisionDetection(gameWorld);
    }

    public void checkCollisions() {
        List<AbstractMovableObject> movableObjectList = gameWorld.getMovableObjectList();
        for(AbstractMovableObject movableObject : movableObjectList) {
            List<AbstractObject> collisions = collisionDetection.detect(movableObject);
//            if(collisions.size() > 0)
//                System.out.println("Collision!");
            for(AbstractObject object : collisions) {
                // save the previous speed
                double prevSpeed = movableObject.speed;

                movableObject.collidedWith(object);

                // save the computed speed and restore the previous one
                double computedSpeed = movableObject.speed;
                movableObject.speed = prevSpeed;

                object.collidedWith(movableObject);

                // restore the computed speed
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
