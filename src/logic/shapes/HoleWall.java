package logic.shapes;

import graphics.GraphicsManager;
import graphics.TextureLoader;
import graphics.shapes.HoleWallRenderer;
import javafx.geometry.BoundingBox;
import logic.AbstractStaticObject;
import logic.Dimension;
import logic.Position;
import logic.utils.ParallelepipedUtils;

/**
 * Represents a wall with a hole in it
 */
public class HoleWall extends AbstractStaticObject {

    /**
     * The walls that make up the {@link logic.shapes.HoleWall}
     */
    private StaticParallelepiped[] walls;

    public HoleWall(Position position, Dimension dimension, Position holeRelativePosition, Dimension holeDimension, GraphicsManager graphicsManager) {
        super(position);
        renderer = new HoleWallRenderer(this, graphicsManager);

        Dimension textureDimension = null;

        // Check if it is rotated by 90 degrees
        boolean rotated = dimension.getX() < dimension.getY();

        if(!rotated)
            textureDimension = new Dimension(holeRelativePosition.getX(), holeDimension.getZ(), 0);
        else
            textureDimension = new Dimension(holeRelativePosition.getY(), holeDimension.getZ(), 0);

        walls = new StaticParallelepiped[4];

        // Compute the walls
        if(!rotated) {
            walls[0] = new StaticParallelepiped(position.add(new Position(0, 0, holeRelativePosition.getZ())),
                    new Dimension(holeRelativePosition.getX(), dimension.getY(), holeDimension.getZ()),
                    ParallelepipedUtils.createShapeSurfaceTypeArray(new ShapeSurfaceType(TextureLoader.wall, textureDimension)),
                    graphicsManager);
            walls[1] = new StaticParallelepiped(position.add(new Position(holeRelativePosition.getX() + holeDimension.getX(), 0, holeRelativePosition.getZ())),
                    new Dimension(dimension.getX() - (holeRelativePosition.getX() + holeDimension.getX()), dimension.getY(), holeDimension.getZ()),
                    ParallelepipedUtils.createShapeSurfaceTypeArray(new ShapeSurfaceType(TextureLoader.wall, textureDimension)),
                    graphicsManager);
        }
        else { // rotated
            walls[0] = new StaticParallelepiped(position.add(new Position(0, 0, holeRelativePosition.getZ())),
                    new Dimension(dimension.getX(), holeRelativePosition.getY(), holeDimension.getZ()),
                    ParallelepipedUtils.createShapeSurfaceTypeArray(new ShapeSurfaceType(TextureLoader.wall, textureDimension)),
                    graphicsManager);
            walls[1] = new StaticParallelepiped(position.add(new Position(0, holeRelativePosition.getY() + holeDimension.getY(), holeRelativePosition.getZ())),
                    new Dimension(dimension.getX(), dimension.getY() - (holeRelativePosition.getY() + holeDimension.getY()), holeDimension.getZ()),
                    ParallelepipedUtils.createShapeSurfaceTypeArray(new ShapeSurfaceType(TextureLoader.wall, textureDimension)),
                    graphicsManager);
        }

        walls[2] = new StaticParallelepiped(position.add(new Position(0, 0, holeRelativePosition.getZ() + holeDimension.getZ())),
                new Dimension(dimension.getX(), dimension.getY(), dimension.getZ() - (holeRelativePosition.getZ() + holeDimension.getZ())),
                ParallelepipedUtils.createShapeSurfaceTypeArray(new ShapeSurfaceType(TextureLoader.wall, textureDimension)),
                graphicsManager);
        walls[3] = new StaticParallelepiped(position,
                new Dimension(dimension.getX(), dimension.getY(), holeRelativePosition.getZ()),
                ParallelepipedUtils.createShapeSurfaceTypeArray(new ShapeSurfaceType(TextureLoader.wall, textureDimension)),
                graphicsManager);
    }

    public StaticParallelepiped[] getWalls() {
        return walls;
    }

    @Override
    public BoundingBox[] getBoundingBoxes() {
        BoundingBox[] ret = new BoundingBox[4];
        for(int i = 0; i < 4; i++) {
            BoundingBox[] crtBoundingBox = walls[i].getBoundingBoxes();
            ret[i] = crtBoundingBox[0];
        }
        return ret;
    }
}
