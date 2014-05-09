package logic.utils;

import logic.AbstractObject;
import logic.GameWorld;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Helper for checking and handle the collision events
 */
public class CollisionDetectionUtils {

    /**
     * Detects and constructs a list of all the object from a collection of objects that are in collision
     * with the object send as parameter
     * @param object                The object for which it is tested if it is in collision with another objects
     * @param objectCollection      The collection of candidate objects
     * @return                      A list of all the objects that are in the collision with the tested object
     */
    public static List<AbstractObject> detect(AbstractObject object, Collection<AbstractObject> objectCollection) {
        List<AbstractObject> foundObjects = new ArrayList<AbstractObject>();

        for(AbstractObject candidateObject : objectCollection)
            if(candidateObject != object && object.isInCollisionWith(candidateObject))
                foundObjects.add(candidateObject);

        return foundObjects;
    }

    /**
     * Detects and constructs a list of all the object from the {@link GameWorld} that are in collision
     * with the {@link AbstractObject} send as parameter
     * @param object        The object for which it is tested if it is in collision with another objects
     * @param gameWorld     The {@link GameWorld}
     * @return              A list of all the objects that are in the collision with the tested object
     */
    public static List<AbstractObject> detect(AbstractObject object, GameWorld gameWorld) {
        return detect(object, gameWorld.getObjectList());
    }

}
