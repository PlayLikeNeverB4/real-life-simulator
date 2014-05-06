package logic.shapes;

import graphics.GraphicsManager;
import graphics.shapes.ParallelepipedRenderer;
import javafx.geometry.BoundingBox;
import logic.AbstractMovableObject;
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
    private Dimension dimension;

    /**
     *
     * @param position      The {@link logic.Position} where the parallelepiped stays
     * @param dimension     The {@link logic.Dimension} of the parallelepiped
     * @param graphicsManager   The {@link graphics.GraphicsManager} which manages the renderer of parallelepiped
     */
    public MovableParallelepiped(Position position, Dimension dimension, ShapeSurfaceType[] surfaceTypes, GraphicsManager graphicsManager) {
        super(position);
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
        ParallelepipedUtils.computeQuads(position, dimension, sides);
        this.renderer = new ParallelepipedRenderer(this, graphicsManager);
    }

    public Dimension getDimension() {
        return dimension;
    }

    public Quad[] getSides() {
        return sides;
    }

    @Override
    public BoundingBox[] getBoundingBoxes() {
        return new BoundingBox[] {
                new BoundingBox(position.getX(), position.getY(), position.getZ(),
                        dimension.getX(), dimension.getY(), dimension.getZ())
        };
    }
}
