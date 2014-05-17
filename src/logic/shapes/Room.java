package logic.shapes;

import graphics.GraphicsManager;
import graphics.TextureLoader;
import graphics.shapes.RoomRenderer;
import javafx.geometry.BoundingBox;
import logic.AbstractObject;
import logic.AbstractStaticObject;
import logic.Dimension;
import logic.Position;
import logic.utils.ParallelepipedUtils;

import java.util.ArrayList;

/**
 * Represents a room from an apartment
 */
public class Room extends AbstractStaticObject {

    public static final double FLOOR_HEIGHT = 5;
    public static final double WALL_WIDTH = 5;

    /**
     * The room's dimension
     */
    private Dimension dimension;

    /**
     * The room's floor
     */
    private StaticParallelepiped floor;

    /**
     * The room' walls
     */
    private AbstractStaticObject[] walls;

    public Room(Position position, Dimension dimension,
                Position doorHoleRelativePosition, Dimension doorHoleDimension,
                int doorIndex, Position doorRelativePosition, Dimension doorDimension, int doorRotation,
                Position windowHoleRelativePosition, Dimension windowHoleDimension,
                int windowIndex, Position windowRelativePosition, Dimension windowDimension,
                GraphicsManager graphicsManager) {
        super(position);
        renderer = new RoomRenderer(this, graphicsManager);
        this.dimension = dimension;

        Dimension textureDimension = new Dimension(100, 40, 0);

        floor = new StaticParallelepiped(position, new Dimension(dimension.getX(), dimension.getY(), FLOOR_HEIGHT),
                                         ParallelepipedUtils.createSurfaceTypeFloorArray(new ShapeSurfaceType(TextureLoader.floor), new ShapeSurfaceType(TextureLoader.wall, textureDimension)),
                                         graphicsManager);

        walls = new AbstractStaticObject[4];
        for(int i = 0; i < 4; i++)
            walls[i] = null;

        final Position[] positions = new Position[] {
                position.add(new Position(0, 0, FLOOR_HEIGHT)),
                position.add(new Position(dimension.getX() - WALL_WIDTH, WALL_WIDTH, FLOOR_HEIGHT)),
                position.add(new Position(0, dimension.getY() - WALL_WIDTH, FLOOR_HEIGHT)),
                position.add(new Position(0, WALL_WIDTH, FLOOR_HEIGHT))
        };
        final Dimension[] dimensions = new Dimension[] {
                new Dimension(dimension.getX(), WALL_WIDTH, dimension.getZ() - FLOOR_HEIGHT),
                new Dimension(WALL_WIDTH, dimension.getY() - 2 * WALL_WIDTH, dimension.getZ() - FLOOR_HEIGHT),
                new Dimension(dimension.getX(), WALL_WIDTH, dimension.getZ() - FLOOR_HEIGHT),
                new Dimension(WALL_WIDTH, dimension.getY() - 2 * WALL_WIDTH, dimension.getZ() - FLOOR_HEIGHT)
        };

        // Check if this room has a door
        if(0 <= doorIndex && doorIndex < 4)
            walls[doorIndex] = new DoorWall(positions[doorIndex], dimensions[doorIndex],
                                            doorRelativePosition, doorDimension, doorRotation, doorHoleRelativePosition, doorHoleDimension,
                                            graphicsManager);

        // Check if this room has a window
        if(0 <= windowIndex && windowIndex < 4)
            walls[windowIndex] = new WindowWall(positions[windowIndex], dimensions[windowIndex],
                                                windowHoleRelativePosition, windowHoleDimension,
                                                windowRelativePosition, windowDimension,
                                                graphicsManager);

        // Compute the rest of the (ordinary) walls
        for(int i = 0; i < 4; i++)
            if(walls[i] == null)
                walls[i] = new StaticParallelepiped(positions[i], dimensions[i],
                                                ParallelepipedUtils.createShapeSurfaceTypeArray(new ShapeSurfaceType(TextureLoader.wall, textureDimension)),
                                                graphicsManager);

        // Transparency sort
        if(0 < windowIndex && windowIndex < 4) {
            AbstractStaticObject aux = walls[windowIndex];
            walls[windowIndex] = walls[0];
            walls[0] = aux;
        }
    }

    public StaticParallelepiped getFloor() {
        return floor;
    }

    public AbstractStaticObject[] getWalls() {
        return walls;
    }

    @Override
    public void collisionSpecialEffects(AbstractObject abstractObject) {
        for(AbstractStaticObject object : walls)
            object.collisionSpecialEffects(abstractObject);
    }

    @Override
    public void specialUpdate(double time) {
        super.specialUpdate(time);
        for(AbstractStaticObject wall : walls)
            wall.specialUpdate(time);
        floor.specialUpdate(time);
    }

    @Override
    public BoundingBox[] getBoundingBoxes() {
        ArrayList<BoundingBox> ret = new ArrayList<BoundingBox>();
        for(AbstractStaticObject wall : walls) {
            BoundingBox[] crtBoundingBoxes = wall.getBoundingBoxes();
            for(BoundingBox boundingBox : crtBoundingBoxes)
                ret.add(boundingBox);
        }
        BoundingBox[] floorBoundingBox = floor.getBoundingBoxes();
        ret.add(floorBoundingBox[0]);
        return ret.toArray(new BoundingBox[ret.size()]);
    }
}
