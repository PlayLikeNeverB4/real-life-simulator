package logic.shapes;

import graphics.shapes.BodyMemberRenderer;
import logic.AbstractPerson;
import logic.Dimension;
import logic.Position;
import logic.utils.ParallelepipedUtils;

/**
 * Represents a component of an {@link AbstractPerson}'s body
 */
public class BodyMember extends StaticParallelepiped {

    /**
     * The moving speed of the bodyMember
     */
    private static final double BODY_MEMBER_MOVING_SPEED = 0.03;

    /**
     * The direction in which the bodyMember rotates
     */
    private int angleDirection;

    /**
     * The current relative angle of the bodyMember; it means the angle where the bodyMember is
     */
    private double relativeAngle;

    /**
     * The person to which the bodyMember belongs
     */
    private AbstractPerson person;

    /**
     * The maximum value to which the relative angle can go
     */
    private double maxAngle;

    /**
     * The minimum value to which the relative angle can go
     */
    private double minAngle;

    /**
     * The {@link logic.Position} of the anchor point of the bodyMember
     */
    private Position anchor;

    public BodyMember(Position position, Dimension dimension, ShapeSurfaceType surfaceType, AbstractPerson person, int startAngleDirection) {
        super(position, dimension, 0, ParallelepipedUtils.createShapeSurfaceTypeArray(surfaceType), person.getGraphicsManager());
        renderer = new BodyMemberRenderer(this, person.getGraphicsManager());
        this.person = person;
        this.maxAngle = Math.PI / 4.0;
        this.minAngle = -Math.PI / 4.0;
        relativeAngle = 0;
        angleDirection = startAngleDirection;
        anchor = position.add(new Position(dimension.getX() / 2, dimension.getY() / 2, dimension.getZ()));
    }

    public double getRelativeAngle() {
        return relativeAngle;
    }

    public Position getAnchor() {
        return anchor;
    }

    public void setRelativeAngle(int angle) {
        this.relativeAngle = angle;
    }

    public void setAnchor(Position position) {
        anchor = position;
    }

    /**
     * Checks if the bodyMember should change it moving direction and updates the relativeAngle
     */
    @Override
    public void specialUpdate(double time) {
        if(relativeAngle > maxAngle) {
            angleDirection = -1;
        }
        if(relativeAngle < minAngle) {
            angleDirection = 1;
        }
        relativeAngle += angleDirection * BODY_MEMBER_MOVING_SPEED * time * (person.getCurrentSpeed() / 2.0);
    }

}
