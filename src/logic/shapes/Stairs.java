package logic.shapes;

import graphics.GraphicsManager;
import graphics.TextureLoader;
import graphics.shapes.StairsRenderer;
import javafx.geometry.BoundingBox;
import logic.*;

/**
 * Represents a staircase
 */
public class Stairs extends AbstractStaticObject {

    /**
     * The number of steps in the staircase
     */
    int numberOfSteps;

    /**
     * The component steps that for the staircase
     */
    StaticParallelepiped[] steps;

    public Stairs(Position position, int rotationIndex, int numberOfSteps, Dimension stepDimension, GraphicsManager graphicsManager) {
        super(position);
        double stairsDirection = Math.PI / 2 * (rotationIndex + 1);
        this.numberOfSteps = numberOfSteps;
        steps = new StaticParallelepiped[numberOfSteps];

        // create the steps
        double stepHeight = stepDimension.getZ();
        for(int stepIdx = 0; stepIdx < numberOfSteps; stepIdx++) {
            steps[stepIdx] = new StaticParallelepiped(new Position(position), new Dimension(stepDimension), rotationIndex,
                                                      new ShapeSurfaceType(TextureLoader.step), graphicsManager);

            position = position.move(stairsDirection, stepDimension.getY());
            stepDimension.setZ(stepDimension.getZ() + stepHeight);
        }

        renderer = new StairsRenderer(this, graphicsManager);
    }

    public StaticParallelepiped[] getSteps() {
        return steps;
    }

    /**
     * Computes the axis aligned bounding box of this object
     */
    @Override
    public BoundingBox[] getBoundingBoxes() {
        BoundingBox[] boundingBoxes = new BoundingBox[numberOfSteps];
        for(int stepIdx = 0; stepIdx < numberOfSteps; stepIdx++) {
            BoundingBox[] crtBoundingBox = steps[stepIdx].getBoundingBoxes();
            boundingBoxes[stepIdx] = crtBoundingBox[0];
        }
        return boundingBoxes;
    }

    /**
     * Notifies this object that it collided with another object
     * It updates the other object's state depending on this object's special effects
     * @param abstractObject The other object that this object collided with
     */
    @Override
    public void collisionSpecialEffects(AbstractObject abstractObject) {
        if(!abstractObject.isMovable())
            return;

        double currentHeight = abstractObject.getPosition().getZ();
        double nextHeight = abstractObject.getPosition().getZ();
        double stepHeight = steps[0].getDimension().getZ();
        for(StaticParallelepiped step : steps) {
            double top = step.getPosition().getZ() + step.getDimension().getZ();
            if(Math.abs(top - abstractObject.getPosition().getZ()) < stepHeight + 1e-6) {
                if(step.isInCollisionWith(abstractObject))
                    nextHeight = Math.max(nextHeight, top);
            }
        }

        // make the object climb the steps
        if(nextHeight - currentHeight > 1e-6) {
            AbstractMovableObject movableObject = (AbstractMovableObject) abstractObject;
            movableObject.move(0, 0, nextHeight + 1e-6 - currentHeight, true);
        }
    }
}
