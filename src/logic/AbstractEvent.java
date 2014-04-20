package logic;

public abstract class AbstractEvent {

    protected abstract AbstractObject find();
    protected abstract void updateWorldObject(AbstractObject abstractObject);

    protected GameWorld gameWorld;

    public void handle() {
        AbstractObject abstractObject = find();
        if(abstractObject != null) {
            updateWorldObject(abstractObject);
        }
    }

}