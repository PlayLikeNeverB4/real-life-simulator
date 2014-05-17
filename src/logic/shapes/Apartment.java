package logic.shapes;

import graphics.GraphicsManager;
import graphics.shapes.ApartmentRenderer;
import javafx.geometry.BoundingBox;
import logic.AbstractMovableObject;
import logic.AbstractObject;
import logic.AbstractStaticObject;
import logic.Position;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Represents an apartment from a block
 */
public abstract class Apartment extends AbstractStaticObject {

    public static double HALL_WIDTH = 50;
    public static double HALL_LENGTH = 150;
    public static double FLOOR_HEIGHT = 5;
    public static double WALL_HEIGHT = 102;
    public static double WALL_WIDTH = 5;
    public static double MINI_HALL_LENGTH = 70;
    public static double MINI_HALL_B_LENGTH = 50;

    /**
     * The objects that are in the apartment (rooms)
     */
    protected LinkedList<AbstractObject> objectList;

    public Apartment(Position position, GraphicsManager graphicsManager) {
        super(position);
        objectList = new LinkedList<AbstractObject>();
        renderer = new ApartmentRenderer(this, graphicsManager);
    }

    public LinkedList<AbstractObject> getObjectList() {
        return objectList;
    }

    public void addObject(AbstractObject object) {
        objectList.add(object);
    }

    /**
     * Updates something unique.
     *
     * @param time The time passed since the last update
     */
    @Override
    public void specialUpdate(double time) {
        super.specialUpdate(time);
        for(AbstractObject object : objectList)
            object.specialUpdate(time);
    }

    /**
     * Notifies this object that it collided with an object
     * It updates this object's state depending on whether it bounces or not
     *
     * @param abstractObject The other object that this object collided with
     */
    @Override
    public void collisionBounceHandler(AbstractObject abstractObject) {
        super.collisionBounceHandler(abstractObject);
        for(AbstractObject object : objectList)
            object.collisionBounceHandler(object);
    }

    /**
     * Notifies this object that it collided with a movable object
     * It updates this object's state depending on the movable object's speed and direction
     *
     * @param movableObject The other object that this object collided with
     */
    @Override
    public void collidedWithMovableObject(AbstractMovableObject movableObject) {
        super.collidedWithMovableObject(movableObject);
        for(AbstractObject object : objectList)
            object.collidedWithMovableObject(movableObject);
    }

    /**
     * Notifies this object that it collided with another object
     * It updates the other object's state depending on this object's special effects
     *
     * @param abstractObject The other object that this object collided with
     */
    @Override
    public void collisionSpecialEffects(AbstractObject abstractObject) {
        super.collisionSpecialEffects(abstractObject);
        for(AbstractObject object : objectList)
            object.collisionSpecialEffects(abstractObject);
    }

    @Override
    public BoundingBox[] getBoundingBoxes() {
        ArrayList<BoundingBox> ret = new ArrayList<BoundingBox>();
        for(AbstractObject object : objectList) {
            BoundingBox[] crtBoundingBoxes = object.getBoundingBoxes();
            for(BoundingBox boundingBox : crtBoundingBoxes)
                ret.add(boundingBox);
        }
        return ret.toArray(new BoundingBox[ret.size()]);
    }
}
