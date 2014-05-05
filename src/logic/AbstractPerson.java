package logic;

public abstract class AbstractPerson extends AbstractMovableObject {

    protected double height;
    protected String color;
    protected String gender;

    protected AbstractPerson(Position position) {
        super(position);
    }

    public AbstractPerson(Position position, double direction) {
        super(position, direction);
    }
}