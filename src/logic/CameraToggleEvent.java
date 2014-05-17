package logic;

import graphics.Camera;
import logic.events.AbstractObjectEvent;

/**
 * Event representing a camera toggle request
 */
public class CameraToggleEvent extends AbstractObjectEvent {
    /**
     * The target camera
     */
    private Camera camera;

    public CameraToggleEvent(Camera camera) {
        this.camera = camera;
    }

    /**
     * This method handles the event.
     */
    @Override
    public void handle() {
        camera.toggleSetting();
    }

    /**
     * Finds the object from GameWorld that will be updated.
     *
     * @return an instance of a subclass of AbstractObject class
     */
    @Override
    protected AbstractObject findObject() {
        return null;
    }

    /**
     * Updates the object given as argument from GameWorld
     *
     * @param targetObject this object will be updated
     */
    @Override
    protected void updateWorldObject(AbstractObject targetObject) {
    }
}
