package logic;

import javafx.geometry.BoundingBox;

public class WorldBox extends AbstractStaticObject {

    private Dimension dimension;

    public WorldBox(Position position, Dimension dimension) {
        super(position);
        this.dimension = dimension;
    }

    /**
     * Computes the axis aligned bounding box of this object.
     * Considers only the floor as a physical object
     */
    @Override
    public BoundingBox getBoundingBox() {
        return new BoundingBox(0, 0, 0,
                               dimension.getX(), dimension.getY(), 0);
    }
}
