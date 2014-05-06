package logic.utils;

import logic.Dimension;
import logic.Position;
import logic.shapes.Quad;
import logic.shapes.ShapeSurfaceType;

/**
 * Handles most of the parallelepiped operations
 */
public class ParallelepipedUtils {
    /**
     * Index matrix needed on computing sides
     */
    private static final int[][] indexes = {
            {0, 1, 5, 4},    // front face vertices
            {1, 2, 6, 5},    // right side face vertices
            {2, 3, 7, 6},    // back face vertices
            {0, 3, 7, 4},    // left side face vertices
            {0, 1, 2, 3},    // down face vertices
            {4, 5, 6, 7}     // up face vertices
    };

    /**
     * Computes the sides of the parallelepiped
     * @param position      The origin position from where the parallelepiped is created
     * @param dimension     The dimension of the parallelepiped
     * @param sides         An array with the sides of the parallelepiped that will be computed
     */
    public static void computeQuads(Position position, Dimension dimension, Quad[] sides) {
        double x, y, z, xLength, yWidth;
        x =  position.getX();
        y = position.getY();
        z = position.getZ();
        xLength = x + dimension.getX();
        yWidth = y + dimension.getY();
        Position[] vertices = new Position[8];

        vertices[0] = new Position(x, y, z);
        vertices[1] = new Position(xLength, y, z);
        vertices[2] = new Position(xLength, yWidth, z);
        vertices[3] = new Position(x, yWidth, z);

        for(int i = 4; i <= 7; i++) {
            vertices[i] = vertices[i - 4].add(new Position(0, 0, dimension.getZ()));
        }

        for(int sideIdx = 0; sideIdx < 6; sideIdx++) {
            Position[] v = sides[sideIdx].getVertices();
            for(int vIdx = 0; vIdx < 4; vIdx++) {
                v[vIdx].setPosition(vertices[indexes[sideIdx][vIdx]]);
            }
        }
    }

    /**
     * Creates an array of 6 {@link ShapeSurfaceType}s, all of them equal to surfaceType
     */
    public static ShapeSurfaceType[] createShapeSurfaceTypeArray(ShapeSurfaceType surfaceType) {
        ShapeSurfaceType[] surfaceTypes = new ShapeSurfaceType[6];
        for(int i = 0; i < 6; i++)
            surfaceTypes[i] = new ShapeSurfaceType(surfaceType);
        return surfaceTypes;
    }

    /**
     * Rotates a parallelepiped from its initial rotation by numberOfRotations rotations
     */
    public static void rotate(Position position, Dimension dimension, Quad[] sides, int numberOfRotations) {
        if((numberOfRotations & 1) == 1) { // rotate 90 degrees
            double aux = dimension.getX();
            dimension.setX(dimension.getY());
            dimension.setY(aux);
        }
        if(numberOfRotations == 2)
            position.setX(position.getX() - dimension.getX());
        else if(numberOfRotations == 3)
            position.setY(position.getY() - dimension.getY());

        ParallelepipedUtils.computeQuads(position, dimension, sides);
    }

}
