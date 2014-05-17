package logic.shapes;

import graphics.GraphicsManager;
import graphics.shapes.ParallelepipedRenderer;
import javafx.geometry.BoundingBox;
import logic.AbstractMovableObject;
import logic.AbstractObject;
import logic.Dimension;
import logic.Position;
import logic.utils.ParallelepipedUtils;

/**
 * This class represents a movable component of an game world object that can be represented as a parallelepiped
 */
public class MovableParallelepiped extends AbstractMovableObject {

    /**
     * An array of {@link Quad} that represents the sides of the parallelepiped
     */
    private Quad[] sides;

    /**
     * The {@link logic.Dimension} of the parallelepiped that is rendered
     */
    protected Dimension dimension;

    /**
     *
     * @param position      The {@link logic.Position} where the parallelepiped stays
     * @param dimension     The {@link logic.Dimension} of the parallelepiped
     * @param graphicsManager   The {@link graphics.GraphicsManager} which manages the renderer of parallelepiped
     */
    public MovableParallelepiped(Position position, Dimension dimension, ShapeSurfaceType[] surfaceTypes, GraphicsManager graphicsManager) {
        super(position, 0, 0, -2e-4);
        this.position = position;
        this.dimension = dimension;
        sides = new Quad[6];

        for(int i = 0; i < 6; i++) {
            Position[] vertices = new Position[4];
            for(int j = 0; j < 4; j++) {
                vertices[j] = new Position(0, 0, 0);
            }
            sides[i] = new Quad(vertices, graphicsManager, surfaceTypes[i]);
        }
        recomputeQuads();
        this.renderer = new ParallelepipedRenderer(this, graphicsManager);
    }

    public MovableParallelepiped(Position position, Dimension dimension, ShapeSurfaceType surfaceType, GraphicsManager graphicsManager) {
        this(position, dimension, ParallelepipedUtils.createShapeSurfaceTypeArray(surfaceType), graphicsManager);
    }

    public Dimension getDimension() {
        return dimension;
    }

    public Quad[] getSides() {
        return sides;
    }

    /**
     * Recomputes the component quads of this parallelepiped
     */
    protected void recomputeQuads() {
        ParallelepipedUtils.computeQuads(position, dimension, sides, 0, false);
    }

    /**
     * Moves the object located at (x, y, z) to (x+dx, y+dy, z+dz)
     * @param dx x distance
     * @param dy y distance
     * @param dz z distance
     */
    @Override
    public void move(double dx, double dy, double dz) {
        move(dx, dy, dz, false);
        recomputeQuads();
    }

    /**
     * Notifies this object that it collided with a movable object.
     * It updates this object's state depending on the movable object's speed and direction
     *
     * @param movableObject The other object that this object collided with
     */
    @Override
    public void collidedWithMovableObject(AbstractMovableObject movableObject) {
        super.collidedWithMovableObject(movableObject);
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
    }

    /**
     * Same as moveTo(nextX, nextY, nextZ)
     */
    @Override
    public void moveTo(Position next, boolean isValid) {
        moveTo(next.getX(), next.getY(), next.getZ(), isValid);
        recomputeQuads();
    }

    @Override
    public BoundingBox[] getBoundingBoxes() {
        return new BoundingBox[] {
                new BoundingBox(position.getX(), position.getY(), position.getZ(),
                        dimension.getX(), dimension.getY(), dimension.getZ())
        };
    }
}
