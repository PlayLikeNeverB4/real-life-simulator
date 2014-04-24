package logic;

import graphics.GraphicsManager;
import graphics.TestObjectRenderer;

public class TestObject extends AbstractTree {

    public Position posOrigin, posBack, posUp;

    public TestObject(Position p, double height, GraphicsManager graphicsManager) {
        this.renderer = new TestObjectRenderer(this, graphicsManager);
        this.position = p;
        this.posOrigin = position;
//        double alpha = 45;
//        double cosAlpha = Math.cos(alpha), sinAlpha = Math.sin(alpha);
//        double xOrigin = this.posOrigin.getX(), yOrigin = this.posOrigin.getY(), zOrigin = posOrigin.getZ();
//        double xBack = xOrigin * cosAlpha - yOrigin * sinAlpha;
//        double yBack = xOrigin * sinAlpha + yOrigin * cosAlpha;
//        this.posBack = new Position(xBack, yBack, zOrigin);
//        this.posUp = new Position(Math.abs(xBack - xOrigin) / 2, Math.abs(yBack - yOrigin) / 2, zOrigin + height);
        this.height = height;
        this.posBack = new Position(posOrigin.getX() + 3, posOrigin.getY(), posOrigin.getZ());
        this.posUp = new Position(posOrigin.getX() + 1, posOrigin.getY(), posOrigin.getZ() + height);
    }
}
