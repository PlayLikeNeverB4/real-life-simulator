package logic.events;

import logic.AbstractObject;
import logic.GameWorld;
import logic.MainCharacter;

public class StopMainCharacterEvent extends AbstractObjectEvent {

    GameWorld gameWorld;

    public StopMainCharacterEvent(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    /**
     * Finds the object from GameWorld that will be updated.
     *
     * @return an instance of a subclass of AbstractObject class
     */
    @Override
    protected AbstractObject findObject() {
        return this.gameWorld.getMainCharacter();
    }

    /**
     * Updates the object given as argument from GameWorld
     *
     * @param targetObject this object will be updated
     */
    @Override
    protected void updateWorldObject(AbstractObject targetObject) {
        MainCharacter mainCharacter = (MainCharacter) targetObject;
        mainCharacter.setInMotion(false);
    }
}
