package logic;

/**
 * This class will handle the events that are referring to a FireObject
 */
public class FireEvent extends AbstractObjectEvent {

    public FireEvent(FireEvent fireEvent) {
    }

    public FireEvent() {
    }

    @Override
    protected AbstractObject findObject() {
        return null;
    }

    @Override
    protected void updateWorldObject(AbstractObject targetObject) {
    }


}