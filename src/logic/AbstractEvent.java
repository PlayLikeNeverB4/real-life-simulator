package logic;

/**
 * This class refers to all events that can appear in the application
 */
public abstract class AbstractEvent {

    /**
     * Handler for this event
     */
    public abstract void handle();

}
