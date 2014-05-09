package logic.shapes;

import graphics.GraphicsManager;
import graphics.TextureLoader;
import logic.utils.ParallelepipedUtils;
import logic.*;
import java.awt.*;

/**
 * Represents a fence
 */
public class Fence extends StaticParallelepiped {

    private static final Color woodColor = new Color(222, 184, 135);

    /**
     * @param position        The {@link logic.Position} where the parallelepiped stays
     * @param dimension       The {@link logic.Dimension} of the parallelepiped
     * @param graphicsManager The {@link graphics.GraphicsManager} which manages the renderer of parallelepiped
     */
    public Fence(Position position, logic.Dimension dimension, GraphicsManager graphicsManager, int idx) {
        super(position, dimension,
                ParallelepipedUtils.createShapeSurfaceTypeArray(new ShapeSurfaceType(TextureLoader.fences[idx])), graphicsManager);
    }

}
