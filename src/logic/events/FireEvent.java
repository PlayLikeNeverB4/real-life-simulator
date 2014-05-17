package logic.events;

import logic.AbstractObject;
import logic.FirePlace;
import logic.GameWorld;

/**
 * This class will handle the events that are referring to a FireObject
 */
public class FireEvent extends AbstractObjectEvent {

    GameWorld gameWorld;

    public FireEvent(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    protected AbstractObject findObject() {
        for(FirePlace firePlace : gameWorld.getFirePlaces())
            if(gameWorld.getMainCharacter().getPosition().distanceTo(firePlace.getPosition()) < 50)
                return firePlace;
        return null;
    }

    @Override
    protected void updateWorldObject(AbstractObject targetObject) {
        FirePlace firePlace = (FirePlace) targetObject;
        if(!firePlace.isOn())
            firePlace.startFire();
        else
            firePlace.stopFire();
    }


}