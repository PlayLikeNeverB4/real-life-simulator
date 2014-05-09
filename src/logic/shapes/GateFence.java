package logic.shapes;

import graphics.GraphicsManager;
import graphics.shapes.GateFenceRenderer;
import javafx.geometry.BoundingBox;
import logic.AbstractStaticObject;

/**
 * Represents a special {@link Fence} that is formed by two fences with a gate between them
 */
public class GateFence extends AbstractStaticObject {

    /**
     * The two {@link Fence}s of the gateFence
     */
    private Fence[] fences;

    public GateFence(Fence[] fences, GraphicsManager graphicsManager) {
        super(fences[0].getPosition());
        this.fences = fences;
        renderer = new GateFenceRenderer(this, graphicsManager);
    }

    public Fence[] getFences() {
        return fences;
    }

    @Override
    public BoundingBox[] getBoundingBoxes() {
        BoundingBox[] boundingBoxes = new BoundingBox[2];
        BoundingBox[] fence1BoundBox = fences[0].getBoundingBoxes();
        BoundingBox[] fence2BoundBox = fences[1].getBoundingBoxes();
        boundingBoxes[0] = fence1BoundBox[0];
        boundingBoxes[1] = fence2BoundBox[0];
        return boundingBoxes;
    }

}
