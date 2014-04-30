package logic;

import graphics.GraphicsManager;
import graphics.MaleRenderer;

public class MainCharacter extends AbstractPerson {

    public MainCharacter(Position position, GraphicsManager graphicsManager) {
        super(position);
        this.renderer = new MaleRenderer(this, graphicsManager);
    }

}