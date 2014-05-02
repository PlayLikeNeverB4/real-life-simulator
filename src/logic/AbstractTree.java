package logic;

public abstract class AbstractTree extends AbstractStaticObject {

    protected double height;

    protected AbstractTree(Position position, double height) {
        super(position);
        this.height = height;
    }
}