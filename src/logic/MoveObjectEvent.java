package logic;

public class MoveObjectEvent extends AbstractObjectEvent {
    /**
     * FORWARD, BACK, LEFT and RIGHT are the four possible directions into which that the MainCharacter can goes
     */
    public static final int FORWARD = 0;
    public static final int BACK = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    /**
     * This field shows the direction to which the object will be moved
     */
    private int direction;

    /**
     * Copy constructor
     * @param direction
     */
    public MoveObjectEvent(int direction) {
        this.direction = direction;
    }

    public MoveObjectEvent(MoveObjectEvent moveEvent) {
        this.direction = moveEvent.direction;
    }

    @Override
    protected AbstractObject findObject() {
        return this.gameWorld.getMainCharacter();
    }

    @Override
    protected void updateWorldObject(AbstractObject abstractObject) {
        switch (direction) {
            case FORWARD: abstractObject.updatePosition(0,0,0);
            case BACK: abstractObject.updatePosition(0,0,0);
            case LEFT: abstractObject.updatePosition(0,0,0);
            case RIGHT: abstractObject.updatePosition(0,0,0);
            default: throw new RuntimeException("Error! Option does not exist!");
        }
    }

}