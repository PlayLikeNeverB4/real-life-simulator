package logic.shapes;

import graphics.GraphicsManager;
import javafx.geometry.BoundingBox;
import logic.Dimension;
import logic.Position;
import logic.utils.ParallelepipedUtils;

import java.awt.*;

/**
 * Represents a window (usually in a room)
 */
public class Window extends Quad {

    public Window(Position position, Dimension dimension, GraphicsManager graphicsManager) {
        super(ParallelepipedUtils.computeQuad(position, dimension), graphicsManager,
              new ShapeSurfaceType(new Color(0, 0, 0, 255)));
//              new ShapeSurfaceType(new Color(255, 255, 255, 64)));
    }

    @Override
    public BoundingBox[] getBoundingBoxes() {
        return new BoundingBox[0];
    }
}
