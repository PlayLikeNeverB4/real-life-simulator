package logic.shapes;

import graphics.GraphicsManager;
import graphics.shapes.AlleyRenderer;
import javafx.geometry.BoundingBox;
import logic.AbstractStaticObject;
import logic.Dimension;
import logic.Position;
import logic.utils.ParallelepipedUtils;

/**
 * Represents an alley on which ambient characters walk
 */
public class Alley extends AbstractStaticObject {

    /**
     * The array of vertices that make up the alley
     */
    Position[] vertices;

    public Alley(Position position, Dimension dimension, GraphicsManager graphicsManager) {
        super(position);
        vertices = ParallelepipedUtils.computeQuad(position, dimension);
        renderer = new AlleyRenderer(this, graphicsManager);
    }

    public Position[] getVertices() {
        return vertices;
    }

    /**
     * Computes and returns an array containing the axis aligned bounding boxes that form of this object
     */
    @Override
    public BoundingBox[] getBoundingBoxes() {
        return new BoundingBox[0];
    }
}
