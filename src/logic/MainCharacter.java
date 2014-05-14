package logic;

import graphics.GraphicsManager;
import graphics.MaleRenderer;
import javafx.geometry.BoundingBox;
import logic.shapes.BodyMember;
import logic.shapes.ShapeSurfaceType;
import logic.shapes.StaticParallelepiped;
import logic.shapes.StaticSphere;

import java.awt.*;

/**
 * The object which the user controls
 */
public class MainCharacter extends AbstractPerson {

    /**
     * The direction where the main character is looking
     */
    private double watchingDirection;

    /**
     * The sensitivity of the user control keys when moving the main character
     */
    private final double movingSpeed;

    /**
     * True if the main character is moving; false otherwise
     */
    private boolean inMotion;

    /**
     *
     * @param position      The position of the left leg of mainCharacter
     * @param graphicsManager
     * @param movingSpeed
     */
    public MainCharacter(Position position, Dimension dimension, GraphicsManager graphicsManager, double movingSpeed) {
        super(position, dimension);
        this.watchingDirection = 0;
        this.movingSpeed = movingSpeed;
        this.graphicsManager = graphicsManager;
        this.renderer = new MaleRenderer(this, graphicsManager);

        // create person's bodyMembers
        bodyMembers = new BodyMember[] {
                new BodyMember(this.position,
                        new Dimension(legWidth, dimension.getY(), heightUnit),
                        new ShapeSurfaceType(new Color(235, 184, 153)), this, 1), // left leg
                new BodyMember(this.position.add(new Position(4 * legWidth + 1, 0, 0)),
                        new Dimension(legWidth, dimension.getY(), heightUnit),
                        new ShapeSurfaceType(new Color(235, 184, 153)), this, -1), // right leg
                new BodyMember(this.position.add(new Position(0, 0, heightUnit + heightUnit / 2)),
                        new Dimension(legWidth, dimension.getY(), heightUnit / 2),
                        new ShapeSurfaceType(new Color(225, 184, 153)), this, -1), // left arm
                new BodyMember(this.position.add(new Position(4 * legWidth + 1, 0, heightUnit + heightUnit / 2)),
                        new Dimension(legWidth, dimension.getY(), heightUnit / 2),
                        new ShapeSurfaceType(new Color(225, 184, 153)), this, 1), // right arm
        };

        // create the person's head
        double wholeBody = 2 * dimension.getZ() / 3;
        Position positionHead = this.position.add(new Position(dimension.getX() / 2, dimension.getY() / 2, wholeBody + headRadius + 1.5));
        this.head = new StaticSphere(positionHead, new ShapeSurfaceType(new Color(240, 213, 195)), headRadius, graphicsManager);

        // create the person's abdomen
        Position positionBody = this.position.add(new Position(legWidth + 0.5, 0, heightUnit));
        this.body = new StaticParallelepiped(positionBody,
                new Dimension(bodyWidth, dimension.getY(), heightUnit),
                new ShapeSurfaceType(new Color(123, 122, 90)),
                graphicsManager);
    }

    public double getMovingSpeed() {
        return movingSpeed;
    }

    public void setWatchingDirection(double watchingDirection) {
        this.watchingDirection = watchingDirection;
    }

    public void setInMotion(boolean inMotion) {
        this.inMotion = inMotion;
    }


    @Override
    public double getWatchingDirection() {
        return watchingDirection;
    }

    /**
     * Returns the current speed of the object
     */
    @Override
    public double getCurrentSpeed() {
        if(inMotion)
            return movingSpeed;
        else
            return 0;
    }

    /**
     * Moves the mainCharacter in the XOY plane which is at a distance to (0,0) and at a specified angle
     * @param angle     The angle to the mainCharacter's watching direction axis
     */
    @Override
    public void move(double angle, double distance) {
        super.move(watchingDirection + angle, distance);
        computeHumanBodyComponents();
    }


    @Override
    public void moveTo(double nextX, double nextY, double nextZ, boolean isValid) {
        super.moveTo(nextX, nextY, nextZ, isValid);
        computeHumanBodyComponents();
    }

    /**
     * Computes the axis aligned bounding box of this object
     */
    @Override
    public BoundingBox[] getBoundingBoxes() {
        return new BoundingBox[] {
                new BoundingBox(position.getX(), position.getY(), position.getZ(),
                        dimension.getX() + 1, dimension.getY(), 2 * heightUnit + headRadius + 1.5)
        };
    }

}