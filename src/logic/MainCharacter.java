package logic;

import graphics.GraphicsManager;
import graphics.MaleRenderer;

public class MainCharacter extends AbstractPerson {

    public MainCharacter(GraphicsManager graphicsManager) {
        this.renderer = new MaleRenderer(this, graphicsManager);
    }

}