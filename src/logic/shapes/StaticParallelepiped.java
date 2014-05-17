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
    protected Quad[] sides;

    /**
     * The {@link logic.Dimension} of the component (geometrical shape) of an object that is rendered
     */
    protected Dimension dimension;

    /**
     * The rotation of the parallelepiped; The real angle is PI / 2 * rotation
     */
    protected int rotation;

    /**
     * true if the textures on it should be mirrored; false otherwise
     */
    protected boolean mirrored;

    /**
     *
     * @param position      The {@link logic.Position} where the parallelepiped stays
     * @param dimension     The {@link Dimension} of the parallelepiped
     * @param numberOfRotations The rotation of the parallelepiped
     * @param surfaceTypes  An array containing 6 {@link logic.shapes.ShapeSurfaceType}s, one for each face
     * @param graphicsManager   The {@link GraphicsManager} which manages the renderer of parallelepiped
     */
    public StaticParallelepiped(Position position, Dimension dimension, int numberOfRotations, ShapeSurfaceType[] surfaceTypes, GraphicsManager graphicsManager) {
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
        this.renderer = new ParallelepipedRenderer(this, graphicsManager);
        rotate(numberOfRotations);
        if(rotation >= 2)
            mirrored = true;
        recomputeQuads();
    }

    public StaticParallelepiped(Position position, Dimension dimension, ShapeSurfaceType[] surfaceTypes, GraphicsManager graphicsManager) {
        this(position, dimension, 0, surfaceTypes, graphicsManager);
    }

    public StaticParallelepiped(Position position, Dimension dimension, ShapeSurfaceType surfaceType, GraphicsManager graphicsManager) {
        this(position, dimension, 0, ParallelepipedUtils.createShapeSurfaceTypeArray(surfaceType), graphicsManager);
    }

    public StaticParallelepiped(Position position, Dimension dimension, int numberOfRotations, ShapeSurfaceType surfaceType, GraphicsManager graphicsManager) {
        this(position, dimension, numberOfRotations, ParallelepipedUtils.createShapeSurfaceTypeArray(surfaceType), graphicsManager);
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
        ParallelepipedUtils.computeQuads(position, dimension, sides, rotation, mirrored);
    }

    /**
     * Rotates a parallelepiped from its initial rotation by numberOfRotations rotations
     */
    protected void rotate(int numberOfRotations) {
        rotation += numberOfRotations;
        ParallelepipedUtils.rotate(position, dimension, sides, rotation, numberOfRotations, mirrored);
    }

    @Override
    public BoundingBox[] getBoundingBoxes() {
        return new BoundingBox[] {
                new BoundingBox(position.getX(), position.getY(), position.getZ(),
                        dimension.getX(), dimension.getY(), dimension.getZ())
        };
    }
}
