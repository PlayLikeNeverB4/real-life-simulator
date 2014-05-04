package logic.events;

import logic.AbstractObject;
import logic.GameWorld;
import logic.MainCharacter;

/**
 * Event that handles the movement of the mainCharacter
 */
public class MoveMainCharacterEvent extends AbstractObjectEvent {
    /**
     * BitMask containing the directions towards which the mainCharacter moves
     */
    private int activeDirections;

    /**
     * Amount of time in which the mainCharacter moves
     */
    private long movingTime;

    public MoveMainCharacterEvent(int activeDirections, GameWorld gameWorld, long movingTime) {
        this.activeDirections = activeDirections;
        this.gameWorld = gameWorld;
        this.movingTime = movingTime;
    }

    @Override
    protected AbstractObject findObject() {
        return this.gameWorld.getMainCharacter();
    }

    @Override
    protected void updateWorldObject(AbstractObject abstractObject) {
        MainCharacter mainCharacter = (MainCharacter) abstractObject;

        // Compute the average of directions
        double sumOfAngles = 0;
        int countActiveDirections = 0;
        for(int i = 0; i <= 3; i++) {
            if( (activeDirections & (1 << i)) > 0) {
                countActiveDirections++;
                sumOfAngles += (90 * i) * Math.PI / 180;
            }
        }

        // Corner case: FORWARD + RIGHT
        if( (activeDirections & (1 << 0)) > 0 && (activeDirections & (1 << 3)) > 0) {
            sumOfAngles = (360 + 270) * Math.PI / 180;
        }

        double distance = mainCharacter.getSpeed() * movingTime;
        double angle = sumOfAngles / countActiveDirections;
        mainCharacter.move(angle, distance);
    }

}