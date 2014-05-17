package logic.shapes;

import graphics.GraphicsManager;
import graphics.TextureLoader;
import graphics.shapes.BlockRenderer;
import javafx.geometry.BoundingBox;
import logic.*;
import logic.Dimension;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Represents a building with apartments
 */
public class Block extends AbstractStaticObject {

    public static final double PLATFORM_WIDTH = 50;
    public static final double PLATFORM_LENGTH = 200;
    public static final double FLOOR_HEIGHT = 5;

    public static final double STEP_HEIGHT = 6;
    public static final int STEP_NUMBER = 9;

    /**
     * List of objects in the block (apartments)
     */
    private LinkedList<AbstractObject> objectList;

    public Block(Position position, int numberOfFloors, GraphicsManager graphicsManager) {
        super(position);
        renderer = new BlockRenderer(this, graphicsManager);
        objectList = new LinkedList<AbstractObject>();

        addObject(new DoorWall(new Position(position),
                               new Dimension(890, Room.WALL_WIDTH, 133),
                               new Position(470, 0, 0), new Dimension(50, Room.WALL_WIDTH, 80), 0,
                               new Position(470, 0, 0), new Dimension(50, Room.WALL_WIDTH, 80),
                               graphicsManager));
        addObject(new StaticParallelepiped(position.add(new Position(0, Room.WALL_WIDTH, 0)),
                                           new Dimension(Room.WALL_WIDTH, 400, 133),
                                           new ShapeSurfaceType(TextureLoader.wall),
                                           graphicsManager));
        addObject(new StaticParallelepiped(position.add(new Position(890 - Room.WALL_WIDTH, Room.WALL_WIDTH, 0)),
                                           new Dimension(Room.WALL_WIDTH, 400, 133),
                                           new ShapeSurfaceType(TextureLoader.wall),
                                           graphicsManager));
        addObject(new StaticParallelepiped(position.add(new Position(Room.WALL_WIDTH, 400, 0)),
                                           new Dimension(890 - 2 * Room.WALL_WIDTH, Room.WALL_WIDTH, 133),
                                           new ShapeSurfaceType(TextureLoader.wall),
                                           graphicsManager));

        addObject(new StaticParallelepiped(position.add(new Position(340, 400, 133)),
                                           new Dimension(210, Room.WALL_WIDTH, numberOfFloors * (2 * STEP_NUMBER - 1) * STEP_HEIGHT),
                                           new ShapeSurfaceType(TextureLoader.wall),
                                           graphicsManager));

        // top
        addObject(new StaticParallelepiped(position.add(new Position(0, 0, 130 + numberOfFloors * (2 * STEP_NUMBER - 1) * STEP_HEIGHT)),
                                           new Dimension(890, Room.WALL_WIDTH + 400, Room.FLOOR_HEIGHT),
                                           new ShapeSurfaceType(TextureLoader.wall),
                                           graphicsManager));

        position = position.add(new Position(445, 0, 0));

        addObject(new Alley(position.add(new Position(0, 0, 0.3)),
                            new Dimension(100, 50, 0),
                            graphicsManager));

        // left wall
        addObject(new StaticParallelepiped(position.add(new Position(-Room.WALL_WIDTH, Room.WALL_WIDTH, 0)),
                                           new Dimension(Room.WALL_WIDTH, 200, 133),
                                           new ShapeSurfaceType(TextureLoader.wall),
                                           graphicsManager));
        // left wall 2
        addObject(new StaticParallelepiped(position.add(new Position(-Room.WALL_WIDTH - 100, 200, 0)),
                                           new Dimension(105, Room.WALL_WIDTH, 133),
                                           new ShapeSurfaceType(TextureLoader.wall),
                                           graphicsManager));
        // left wall 3
        addObject(new StaticParallelepiped(position.add(new Position(-Room.WALL_WIDTH - 100, 200 + Room.WALL_WIDTH, 0)),
                                           new Dimension(Room.WALL_WIDTH, 195, 135),
                                           new ShapeSurfaceType(TextureLoader.wall),
                                           graphicsManager));

        // right wall
        addObject(new StaticParallelepiped(position.add(new Position(100, Room.WALL_WIDTH, 0)),
                                           new Dimension(Room.WALL_WIDTH, 400, 135),
                                           new ShapeSurfaceType(TextureLoader.wall),
                                           graphicsManager));
        // right wall 2
        addObject(new StaticParallelepiped(position.add(new Position(50, 255, 0)),
                                           new Dimension(50, Room.WALL_WIDTH, 84),
                                           new ShapeSurfaceType(TextureLoader.wall),
                                           graphicsManager));
        // right wall 3
        addObject(new StaticParallelepiped(position.add(new Position(50, 255 + Room.WALL_WIDTH, 0)),
                                           new Dimension(Room.WALL_WIDTH, 140, 84),
                                           new ShapeSurfaceType(TextureLoader.wall),
                                           graphicsManager));

        // aux wall
        addObject(new StaticParallelepiped(position.add(new Position(-100, 345, 0)),
                                           new Dimension(150, Room.WALL_WIDTH, 84),
                                           new ShapeSurfaceType(TextureLoader.wall),
                                           graphicsManager));
        // aux wall 2
        addObject(new StaticParallelepiped(position.add(new Position(-100, 400 - Room.WALL_WIDTH, 0)),
                                           new Dimension(200, Room.WALL_WIDTH, 90),
                                           new ShapeSurfaceType(TextureLoader.wall),
                                           graphicsManager));

        position = position.add(new Position(0, 50, 0));

        addObject(new Stairs(position, 0, 7, new Dimension(100, 12, 5), graphicsManager));

        position = position.add(new Position(0, 0, 7 * 5));
        addObject(new StaticParallelepiped(position.add(new Position(0, 7 * 12, -5)), new Dimension(100, 72, 5),
                                           new ShapeSurfaceType(Color.GRAY), graphicsManager));
        addObject(new StaticParallelepiped(position.add(new Position(-50, 7 * 12 + 71 + PLATFORM_WIDTH, -5)),
                                           new Dimension(100, 90, 5),
                                           new ShapeSurfaceType(Color.GRAY), graphicsManager));

        position = position.add(new Position(-100, 7 * 12 + 71, -FLOOR_HEIGHT));

        for(int i = 0; i < numberOfFloors; i++) {
            // Stairs to climb a floor
            addObject(new StaticParallelepiped(position, new Dimension(PLATFORM_LENGTH, PLATFORM_WIDTH, FLOOR_HEIGHT),
                                               new ShapeSurfaceType(Color.GRAY), graphicsManager));
            addObject(new Stairs(position.add(new Position(0, PLATFORM_WIDTH, 5)), 0, STEP_NUMBER, new Dimension(50, 10, STEP_HEIGHT), graphicsManager));
            addObject(new StaticParallelepiped(position.add(new Position(0, PLATFORM_WIDTH + STEP_NUMBER * 10, STEP_NUMBER * STEP_HEIGHT)),
                                                            new Dimension(PLATFORM_LENGTH, PLATFORM_WIDTH, FLOOR_HEIGHT),
                                                            new ShapeSurfaceType(Color.GRAY), graphicsManager));
            addObject(new Stairs(position.add(new Position(PLATFORM_LENGTH, PLATFORM_WIDTH + STEP_NUMBER * 10, STEP_NUMBER * STEP_HEIGHT)),
                                 2, STEP_NUMBER, new Dimension(50, 10, STEP_HEIGHT), graphicsManager));

            position = position.add(new Position(0, 0, (2 * STEP_NUMBER - 1) * STEP_HEIGHT));
            addObject(new StaticParallelepiped(position, new Dimension(PLATFORM_LENGTH, PLATFORM_WIDTH, FLOOR_HEIGHT),
                                               new ShapeSurfaceType(Color.GRAY), graphicsManager));

            // 2 apartments per floor
            addObject(new Apartment1(position.add(new Position(PLATFORM_LENGTH, 0, 0)), graphicsManager));
            addObject(new Apartment2(position, graphicsManager));
        }
    }

    private void addObject(AbstractObject object) {
        objectList.add(object);
    }

    public LinkedList<AbstractObject> getObjectList() {
        return objectList;
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
