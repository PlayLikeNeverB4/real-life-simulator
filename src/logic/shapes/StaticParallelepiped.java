package logic.shapes;

import graphics.GraphicsManager;
import graphics.shapes.ParallelepipedRenderer;
import javafx.geometry.BoundingBox;
import logic.AbstractStaticObject;
import logic.Dimension;
import logic.Position;
import logic.utils.ParallelepipedUtils;

/**
 * This class represents a static component of an game world object that can be represented as a parallelepiped
 */
public class StaticParallelepiped extends AbstractStaticObject {

    /**
     * An array of {@link Quad} that represents the sides of the parallelepiped
     */
    private Quad[] sides;

    /**
     * The {@link logic.Dimension} of the component (geometrical shape) of an object that is rendered
     */
    private Dimension dimension;

    /**
     *
     * @param position      The {@link logic.Position} where the parallelepiped stays
     * @param dimension     The {@link Dimension} of the parallelepiped
     * @param graphicsManager   The {@link GraphicsManager} which manages the renderer of parallelepiped
     */
    public StaticParallelepiped(Position position, Dimension dimension, ShapeSurfaceType[] surfaceTypes, GraphicsManager graphicsManager) {
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

    public StaticParallelepiped(Position position, Dimension dimension, ShapeSurfaceType surfaceType, GraphicsManager graphicsManager) {
        this(position, dimension, ParallelepipedUtils.createShapeSurfaceTypeArray(surfaceType), graphicsManager);
    }

    public StaticParallelepiped(Position position, Dimension dimension, ShapeSurfaceType surfaceType, GraphicsManager graphicsManager, int numberOfRotations) {
        this(position, dimension, surfaceType, graphicsManager);
        rotate(numberOfRotations);
    }

    public Dimension getDimension() {
        return dimension;
    }

    public Quad[] getSides() {
        return sides;
    }

    /**
     * Rotates a parallelepiped from its initial rotation by numberOfRotations rotations
     */
    private void rotate(int numberOfRotations) {
        ParallelepipedUtils.rotate(position, dimension, sides, numberOfRotations);
    }

    @Override
    public BoundingBox[] getBoundingBoxes() {
        return new BoundingBox[] {
                new BoundingBox(position.getX(), position.getY(), position.getZ(),
                        dimension.getX(), dimension.getY(), dimension.getZ())
        };
    }

}
