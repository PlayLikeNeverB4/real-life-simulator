package logic;

/**
 * This class refers to all events that can appear into the application
 */
public abstract class AbstractEvent {
    /**
     * Finds the object from GameWorld that will be updated.
     * @return an instance of a subclass of AbstractObject class
     */
    protected abstract AbstractObject findObject();

    /**
     * Updates the object given as argument from GameWorld
     * @param targetObject this object will be updated
     * @throws Exception
     */
    protected abstract void updateWorldObject(AbstractObject targetObject) throws Exception;

    /**
     * The GameWorld for which it updates the object
     */
    protected GameWorld gameWorld;

    /**
     * This method handle the event by finding the object and updating it as the event ask
     */
    public void handle() throws Exception {
        AbstractObject targetObject = findObject();
        if(targetObject != null) {
            updateWorldObject(targetObject);
        }
    }

}