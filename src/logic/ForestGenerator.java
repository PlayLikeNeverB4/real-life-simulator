package logic;

import graphics.BillboardRenderer;
import graphics.GraphicsManager;
import logic.utils.GeometryUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Generates billboards in a rectangle representing a distant forest
 */
public class ForestGenerator {

    private static final Position referencePosition = new Position(5000, 5000, 0);
    private static int MIN_TREE_HEIGHT = 250;
    private static int MAX_TREE_HEIGHT = 450;
    private static int TREE_WIDTH = 300;

    /**
     * The game world where the forest is generated
     */
    private GameWorld gameWorld;

    /**
     * The graphics manager which renders the generated forest
     */
    private GraphicsManager graphicsManager;

    public ForestGenerator(GameWorld gameWorld, GraphicsManager graphicsManager) {
        this.gameWorld = gameWorld;
        this.graphicsManager = graphicsManager;
    }

    /**
     * Generates a forest
     * @param position      The lower-left point of the forest
     * @param dimension     The dimension of the forest
     * @param direction     The orientation of the forest represented by the angle to the OX axis
     * @param numberOfTrees The number of the trees to generate
     */
    public void generate(Position position, Dimension dimension, double direction, int numberOfTrees) {
        Random rng = new Random();
        ArrayList<Billboard> trees = new ArrayList<Billboard>();
        while(numberOfTrees-- > 0) {
            // generate the tree's coordinates inside the forest
            double x = rng.nextDouble() * dimension.getX();
            double y = rng.nextDouble() * dimension.getY();

            // convert the coordinates to (angle, distance)
            double angle = Math.atan2(y, x);
            double distance = Math.sqrt(x * x + y * y);

            double totalAngle = GeometryUtils.normalizeAngle(angle + direction);
            Position treePos = GeometryUtils.computePointOnCircle(totalAngle, distance).add(position);
            double height = rng.nextDouble() * (MAX_TREE_HEIGHT - MIN_TREE_HEIGHT) + MIN_TREE_HEIGHT;
            int treeType = rng.nextInt(2);
            Billboard tree = new Billboard(treePos, direction, new Dimension(TREE_WIDTH, height), BillboardRenderer.getTreeTexture(treeType), graphicsManager);
            trees.add(tree);
        }

        // sort the tree according
        Collections.sort(trees, new Comparator<Billboard>() {
            @Override
            public int compare(Billboard o1, Billboard o2) {
                Double d1 = o1.getPosition().distanceTo(referencePosition);
                Double d2 = o2.getPosition().distanceTo(referencePosition);
                return d2.compareTo(d1);
            }
        });

        // add the trees to the game world
        for(Billboard tree : trees)
            gameWorld.addUntouchableObject(tree);
    }

}
