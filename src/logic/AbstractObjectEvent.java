package logic;

/**
 * Refers to all events referring to objects that can appear in the application
 */
public abstract class AbstractObjectEvent extends AbstractEvent {

    /**
     * The GameWorld for which it updates the object
     */
    protected GameWorld gameWorld;

    /**
     * Finds the object from GameWorld that will be updated.
     * @return an instance of a subclass of AbstractObject class
     */
    protected abstract AbstractObject findObject();

    /**
     * Updates the object given as argument from GameWorld
     * @param targetObject this object will be updated
     */
    protected abstract void updateWorldObject(AbstractObject targetObject);

    /**
     * This method handles the event by finding the object and updating it as the event requires
     */
    public void handle() {
        AbstractObject targetObject = findObject();
        if(targetObject != null) {
            updateWorldObject(targetObject);
        }
    }

}