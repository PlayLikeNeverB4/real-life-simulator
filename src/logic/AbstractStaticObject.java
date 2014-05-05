package logic;

public abstract class AbstractStaticObject extends AbstractObject {

    public AbstractStaticObject(Position position) {
        super(position);
    }

    /**
     * Tests if this object can move or not
     */
    public boolean isMovable() {
        return false;
    }

    protected void collisionBounceHandler(AbstractObject abstractObject) {
        // do nothing
    }

}