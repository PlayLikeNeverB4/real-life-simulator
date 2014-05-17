package logic.shapes;

import graphics.GraphicsManager;
import graphics.TextureLoader;
import graphics.shapes.DoorWallRenderer;
import javafx.geometry.BoundingBox;
import logic.AbstractObject;
import logic.Dimension;
import logic.Position;

import java.awt.*;

/**
 * Represents a wall with a door in it
 */
public class DoorWall extends HoleWall {

    private Door door;

    public DoorWall(Position position, Dimension dimension, Position doorRelativePosition, Dimension doorDimension, int doorRotation, Position holeRelativePosition, Dimension holeDimension, GraphicsManager graphicsManager) {
        super(position, dimension, holeRelativePosition, holeDimension, graphicsManager);
        renderer = new DoorWallRenderer(this, graphicsManager);
        door = new Door(position.add(doorRelativePosition), doorDimension, doorRotation,
                new ShapeSurfaceType(TextureLoader.doorFront),
                new ShapeSurfaceType(TextureLoader.doorBack),
                new ShapeSurfaceType(new Color(241, 182, 107)),
                graphicsManager);
    }

    public Door getDoor() {
        return door;
    }

    @Override
    public void collisionSpecialEffects(AbstractObject abstractObject) {
        super.collisionSpecialEffects(abstractObject);
        if(door.isInCollisionWith(abstractObject))
            door.collisionSpecialEffects(abstractObject);
    }

    @Override
    public void specialUpdate(double time) {
        super.specialUpdate(time);
        door.specialUpdate(time);
    }

    @Override
    public BoundingBox[] getBoundingBoxes() {
        BoundingBox[] prev = super.getBoundingBoxes();
        BoundingBox[] ret = new BoundingBox[prev.length + 1];
        for(int i = 0; i < prev.length; i++)
            ret[i] = prev[i];
        BoundingBox[] doorBoundingBox = door.getBoundingBoxes();
        ret[ret.length - 1] = doorBoundingBox[0];
        return ret;
    }
}
