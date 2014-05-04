package logic.events;

import logic.AbstractObject;

public class SwitchEvent extends AbstractObjectEvent {

    public SwitchEvent(SwitchEvent switchEvent) {
    }

    public SwitchEvent() {
    }

    @Override
    protected AbstractObject findObject() {
        return null;
    }

    @Override
    protected void updateWorldObject(AbstractObject targetObject) {
    }

}