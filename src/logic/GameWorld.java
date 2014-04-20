package logic;

import graphics.IRenderable;
import java.util.List;

public class GameWorld implements IRenderable {

    private List<AbstractObject> objectList;
    private MainCharacter mainCharacter;

    public MainCharacter getMainCharacter() {
        return mainCharacter;
    }
}