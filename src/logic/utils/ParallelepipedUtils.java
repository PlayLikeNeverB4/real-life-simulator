package logic.utils;

import graphics.TextureHandler;
import logic.Dimension;
import logic.Position;
import logic.shapes.Quad;
import logic.shapes.ShapeSurfaceType;
import java.awt.*;
import java.util.Arrays;

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
     * The pre-established list of colors
     */
    private static final Color[] setOfBeautifulColors = new Color[] {
            new Color(21, 217, 238),
            new Color(21, 217, 105),
            new Color(255, 0, 0),
            new Color(155, 4, 160),
            new Color(38, 15, 253),
            new Color(252, 252, 0),
            new Color(0, 255, 0),
            new Color(0, 0, 90),
            new Color(255, 228, 181),
            new Color(255, 20, 147),
            new Color(184, 134, 11),
            new Color(255, 69, 0),
            new Color(0, 250, 154),
            new Color(139, 69, 19),
            new Color(148, 0, 211),
            new Color(173, 255, 47),
            new Color(17, 180, 0),
            new Color(205, 92, 92),
            new Color(100, 149, 237),
            new Color(220, 20, 60)
    };

    /**
     * Creates an array of 6 {@link ShapeSurfaceType}s, all of them equal to surfaceType
     * @return      An array with 6 {@link ShapeSurfaceType}s
     */
    public static ShapeSurfaceType[] createShapeSurfaceTypeArray(ShapeSurfaceType surfaceType) {
        ShapeSurfaceType[] surfaceTypes = new ShapeSurfaceType[6];
        for(int i = 0; i < 6; i++)
            surfaceTypes[i] = new ShapeSurfaceType(surfaceType);
        return surfaceTypes;
    }

    /**
     * Creates an array of 6 {@link ShapeSurfaceType}s.
     * All of the lateral sides are rendered with the texture sent as parameter.
     * Up and down sides are rendered with the color send as parameter.
     * @param color     The {@link Color} used for rendering the up and down sides
     * @param textureHandler        The {@link TextureHandler} used for rendering the lateral sides
     * @return      An array with 6 {@link ShapeSurfaceType}s
     */
    public static ShapeSurfaceType[] createSurfaceTypeColorAndTexture(Color color, TextureHandler textureHandler) {
        ShapeSurfaceType[] shapeSurfaceType = new ShapeSurfaceType[6];
        shapeSurfaceType[4] = new ShapeSurfaceType(color);
        shapeSurfaceType[5] = new ShapeSurfaceType(color);
        for(int i = 0; i < 4; i++) {
            shapeSurfaceType[i] = new ShapeSurfaceType(textureHandler);
        }
        return shapeSurfaceType;
    }

    /**
     * Creates an array of 6 {@link ShapeSurfaceType}s used for rendering the door.
     * @param frontFaceType     The {@link logic.shapes.ShapeSurfaceType} used for rendering the door's front face
     * @param backFaceType     The {@link logic.shapes.ShapeSurfaceType} used for rendering the door's back face
     * @param sidesType        The {@link logic.shapes.ShapeSurfaceType} used for rendering the door's lateral sides
     * @return      An array with 6 {@link ShapeSurfaceType}s
     */
    public static ShapeSurfaceType[] createSurfaceTypeDoorArray(ShapeSurfaceType frontFaceType, ShapeSurfaceType backFaceType, ShapeSurfaceType sidesType) {
        ShapeSurfaceType[] shapeSurfaceType = new ShapeSurfaceType[6];
        shapeSurfaceType[0] = new ShapeSurfaceType(frontFaceType);
        shapeSurfaceType[2] = new ShapeSurfaceType(backFaceType);
        shapeSurfaceType[1] = new ShapeSurfaceType(sidesType);
        for(int i = 3; i < 6; i++) {
            shapeSurfaceType[i] = new ShapeSurfaceType(sidesType);
        }
        return shapeSurfaceType;
    }

    /**
     * Creates an array of 6 {@link ShapeSurfaceType}s, all of them equal to a random color
     * from a pre-established list of colors
     * @return      An array with 6 {@link ShapeSurfaceType}s
     */
    public static ShapeSurfaceType[] createShapeSurfaceTypeArrayRNGColors() {
        int numberElements = 20;
        ShapeSurfaceType[] surfaceTypes = new ShapeSurfaceType[6];
        boolean[] inList = new boolean[numberElements];
        Arrays.fill(inList, false);
        for(int i = 0; i < 6; i++) {
            int newIdx;
            do {
                newIdx = (int) RNGUtils.generateDouble(numberElements);
            } while(inList[newIdx]);
            surfaceTypes[i] = new ShapeSurfaceType(setOfBeautifulColors[newIdx]);
            inList[newIdx] = true;
        }
        return surfaceTypes;
    }

    /**
     * Computes the sides of the parallelepiped
     * @param position      The origin position from where the parallelepiped is created
     * @param dimension     The dimension of the parallelepiped
     * @param sides         An array with the sides of the parallelepiped that will be computed
     * @param rotation      The rotation of the parallelepiped
     * @param mirrored      True if the textures should be mirrored; false otherwise
     */
    public static void computeQuads(Position position, Dimension dimension, Quad[] sides, int rotation, boolean mirrored) {
        double x, y, z, xLength, yWidth;
        x =  position.getX();
        y = position.getY();
        z = position.getZ();
        xLength = x + dimension.getX();
        yWidth = y + dimension.getY();
        Position[] vertices = new Position[8];

        vertices[(4 - rotation + 4) % 4] = new Position(x, y, z);
        vertices[(4 - rotation + 1 + 4) % 4] = new Position(xLength, y, z);
        vertices[(4 - rotation + 2 + 4) % 4] = new Position(xLength, yWidth, z);
        vertices[(4 - rotation + 3 + 4) % 4] = new Position(x, yWidth, z);

        for(int i = 4; i <= 7; i++) {
            vertices[i] = vertices[i - 4].add(new Position(0, 0, dimension.getZ()));
        }

        for(int sideIdx = 0; sideIdx < 6; sideIdx++) {
            Position[] v = sides[sideIdx].getVertices();
            if(mirrored) {
                for(int vIdx = 0; vIdx < 4; vIdx++) // 0, 1, 2, 3 => 1, 0, 3, 2
                    v[vIdx].setPosition(vertices[indexes[sideIdx][vIdx + 1 - 2 * (vIdx % 2)]]);
            }
            else {
                for(int vIdx = 0; vIdx < 4; vIdx++)
                    v[vIdx].setPosition(vertices[indexes[sideIdx][vIdx]]);
            }
        }
    }

    /**
     * Rotates a parallelepiped from its initial rotation by numberOfRotations rotations
     * @param position         The origin position from where the parallelepiped is created
     * @param dimension        The dimension of the parallelepiped
     * @param sides            An array with the sides of the parallelepiped that will be computed
     * @param rotation         The rotation of the parallelepiped
     * @param currentRotations The number of rotations that were last updated
     * @param mirrored         True if the textures should be mirrored; false otherwise
     */
    public static void rotate(Position position, Dimension dimension, Quad[] sides, int rotation, int currentRotations, boolean mirrored) {
        currentRotations = (currentRotations + 4) % 4;

        double angle = Math.PI / 2 * currentRotations;

        // Compute the opposite corner of the parallelepiped origin
        Position P = new Position(position.getX() + dimension.getX(), position.getY() + dimension.getY());

        // Rotate it around the origin
        P = GeometryUtils.rotateAroundPoint(position, P, angle);

        double posX = Math.min(P.getX(), position.getX());
        double posY = Math.min(P.getY(), position.getY());
        double maxX = Math.max(P.getX(), position.getX());
        double maxY = Math.max(P.getY(), position.getY());

        position.setX(posX);
        position.setY(posY);
        dimension.setX(maxX - posX);
        dimension.setY(maxY - posY);

        ParallelepipedUtils.computeQuads(position, dimension, sides, rotation, mirrored);
    }

}
