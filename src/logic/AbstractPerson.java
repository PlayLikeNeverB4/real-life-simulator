package logic;

import graphics.GraphicsManager;
import logic.shapes.BodyMember;
import logic.shapes.StaticParallelepiped;
import logic.shapes.StaticSphere;
import logic.utils.ParallelepipedUtils;

public abstract class AbstractPerson extends AbstractMovableObject {

    protected StaticSphere head;

    /**
     * An array of all the {@link BodyMember}s of a person
     */
    protected BodyMember[] bodyMembers;

    /**
     * A {@link StaticParallelepiped} that represents the abdomen part of the person's body
     */
    protected StaticParallelepiped body;

    /**
     * Represents the width on the Ox axis of the leg
     */
    protected double legWidth;

    /**
     * Represents the radius of the sphere used to render the person's head
     */
    protected double headRadius;

    /**
     * Represents the width on the Ox axis of the abdomen
     */
    protected double bodyWidth;

    /**
     * Represents an unit of the height of the person
     */
    protected double heightUnit;

    /**
     * Represents the {@link Dimension} of the person
     */
    protected Dimension dimension;

    protected GraphicsManager graphicsManager;

    public abstract double getWatchingDirection();

    protected AbstractPerson(Position position, Dimension dimension) {
        super(position);
        this.dimension = dimension;
        bodyWidth = 3 * (dimension.getX() - 1) / 5.0;
        legWidth = (dimension.getX() - 1) / 5.0;
        heightUnit = dimension.getZ() / 3.0;
        headRadius = heightUnit / 4.0;
    }

    public AbstractPerson(Position position, double direction) {
        super(position, direction);
    }

    public StaticSphere getHead() {
        return head;
    }

    public StaticParallelepiped getBody() {
        return body;
    }

    public BodyMember[] getBodyMembers() {
        return bodyMembers;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public GraphicsManager getGraphicsManager() {
        return graphicsManager;
    }

    /**
     * Computes all of the person's parts (head, body, members)
     */
    protected void computeHumanBodyComponents() {
        // computing person's bodyMembers
        computeBodyMembers();

        // computing person's head
        double wholeBody = 2 * dimension.getZ() / 3;
        Position positionHead = this.position.add(new Position(dimension.getX() / 2, dimension.getY() / 2, wholeBody + headRadius + 1.5));
        this.head.setPosition(positionHead);

        // computing person's abdomen
        Position positionBody = this.position.add(new Position(legWidth + 0.5, 0, heightUnit));
        this.body.setPosition(positionBody);
        ParallelepipedUtils.computeQuads(this.body.getPosition(), this.body.getDimension(), this.body.getSides(), 0, false);
    }

    /**
     * Computes the four person's bodyMembers (two legs and two arms)
     */
    private void computeBodyMembers() {
        // computing the person's left leg
        bodyMembers[0].setPosition(this.position);
        ParallelepipedUtils.computeQuads(bodyMembers[0].getPosition(), bodyMembers[0].getDimension(), bodyMembers[0].getSides(), 0, false);
        bodyMembers[0].setAnchor(bodyMembers[0].getPosition().add(new Position(bodyMembers[0].getDimension().getX() / 2.0,
                bodyMembers[0].getDimension().getY() / 2.0,
                bodyMembers[0].getDimension().getZ())));

        // computing the person's right leg
        bodyMembers[1].setPosition(this.position.add(new Position(4 * legWidth + 1, 0, 0)));
        ParallelepipedUtils.computeQuads(bodyMembers[1].getPosition(), bodyMembers[1].getDimension(), bodyMembers[1].getSides(), 0, false);
        bodyMembers[1].setAnchor(bodyMembers[1].getPosition().add(new Position(bodyMembers[1].getDimension().getX() / 2.0,
                bodyMembers[1].getDimension().getY() / 2.0,
                bodyMembers[1].getDimension().getZ())));

        // computing the person's left arm
        bodyMembers[2].setPosition(this.position.add(new Position(0, 0, heightUnit + heightUnit / 2)));
        ParallelepipedUtils.computeQuads(bodyMembers[2].getPosition(), bodyMembers[2].getDimension(), bodyMembers[2].getSides(), 0, false);
        bodyMembers[2].setAnchor(bodyMembers[2].getPosition().add(new Position(bodyMembers[2].getDimension().getX() / 2.0,
                bodyMembers[2].getDimension().getY() / 2.0,
                bodyMembers[2].getDimension().getZ())));

        // computing the person's right arm
        bodyMembers[3].setPosition(this.position.add(new Position(4 * legWidth + 1, 0, heightUnit + heightUnit / 2)));
        ParallelepipedUtils.computeQuads(bodyMembers[3].getPosition(), bodyMembers[3].getDimension(), bodyMembers[3].getSides(), 0, false);
        bodyMembers[3].setAnchor(bodyMembers[3].getPosition().add(new Position(bodyMembers[3].getDimension().getX() / 2.0,
                bodyMembers[3].getDimension().getY() / 2.0,
                bodyMembers[3].getDimension().getZ())));
    }

    @Override
    public void specialUpdate(double time) {
        super.specialUpdate(time);
        for(int i = 0; i < bodyMembers.length; i++) {
            bodyMembers[i].specialUpdate(time);
        }
    }

}