package logic;

public abstract class AbstractEvent {

    protected abstract void find();
    protected abstract void updateWorldObject();

    private GameWorld gameWorld;

    public void handle() {
    }

}