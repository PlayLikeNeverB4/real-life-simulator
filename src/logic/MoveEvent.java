package logic;

public class MoveEvent extends AbstractEvent {

    private int direction;

    public MoveEvent(int direction) {
        this.direction = direction;
    }

    @Override
    protected AbstractObject find() {
        return this.gameWorld.getMainCharacter();
    }

    @Override
    protected void updateWorldObject(AbstractObject abstractObject) {
        switch (direction) {
            case 0: abstractObject.updatePosition(0,0,0); // up
            case 1: abstractObject.updatePosition(0,0,0); // down
            case 2: abstractObject.updatePosition(0,0,0); // left
            case 3: abstractObject.updatePosition(0,0,0); // right
            default: System.out.println("Error! Option does not exist!");
        }
    }

}