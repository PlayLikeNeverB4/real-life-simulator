package logic;

import java.util.ArrayList;
import java.util.List;

public class CollisionDetection {

    private GameWorld gameWorld;

    public CollisionDetection(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public List<AbstractObject> detect(AbstractMovableObject movableObject) {
        List<AbstractObject> foundObjects = new ArrayList<AbstractObject>();
        List<AbstractObject> candidateObjects = gameWorld.getObjectList();

        for(AbstractObject object : candidateObjects)
            if(object != movableObject && movableObject.isInCollisionWith(object))
                foundObjects.add(object);

        return foundObjects;
    }
}
