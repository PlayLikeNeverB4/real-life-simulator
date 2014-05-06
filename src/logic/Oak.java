package logic;

import javafx.geometry.BoundingBox;

public class Oak extends AbstractTree {

    public Oak(Position position, double height) {
        super(position, height);
    }

    /**
     * Computes the axis aligned bounding box of this object
     */
    @Override
    public BoundingBox[] getBoundingBoxes() {
        return null;
    }
}