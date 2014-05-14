package logic;

public abstract class AmbientCharacter extends AbstractPerson {

    private Object path;

    protected AmbientCharacter(Position position) {
        super(position, new Dimension(0));
    }
}