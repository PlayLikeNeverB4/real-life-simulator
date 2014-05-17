package graphics;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.io.IOException;

/**
 * Class for easier loading of textures
 */
public class TextureHandler {

    /**
     * The GraphicsManager used for rendering this texture
     */
    GraphicsManager graphicsManager;

    /**
     * The id of this texture within the graphics library
     */
    int[] textureId;

    /**
     * The structure holding the data of the texture
     */
    TextureReader.Texture texture;

    /**
     * Whether or not the texture has an alpha channel
     */
    boolean transparent;

    public TextureHandler(String filename, GraphicsManager graphicsManager) {
        this(filename, graphicsManager, true);
    }

    public TextureHandler(String filename, GraphicsManager graphicsManager, boolean transparent) {
        this.graphicsManager = graphicsManager;
        this.transparent = transparent;

        textureId = new int[1];
        texture = null;

        GL2 gl = graphicsManager.getGlObject();

        // Generate an id for the texture
        gl.glGenTextures(1, textureId, 0);

        // Define the filters used when the texture is scaled
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);

        enableTexturing(gl);
        bind();

        // Read the texture from the image
        texture = getTexture(filename, transparent);

        // Construct the texture and use mipmapping in the process
        this.makeRGBTexture();

        disableTexturing(gl);
    }

    public TextureReader.Texture getTexture() {
        return texture;
    }

    /**
     * Enables texturing. This must be done before using the texture.
     */
    public static void enableTexturing(GL2 gl) {
        gl.glColor3d(1, 1, 1);
        gl.glEnable(GL.GL_TEXTURE_2D);
    }

    /**
     * Disables texturing. This must be done after using the texture.
     */
    public static void disableTexturing(GL2 gl) {
        gl.glDisable(GL.GL_TEXTURE_2D);
    }

    /**
     * Reads a {@link graphics.TextureReader.Texture} from a file
     * @param filename          The path to the file
     * @return                  A {@link graphics.TextureReader.Texture} object containing the data in the texture
     */
    private TextureReader.Texture getTexture(String filename) {
        return getTexture(filename, false);
    }

    /**
     * Reads a {@link graphics.TextureReader.Texture} from a file
     * @param filename          The path to the file
     * @param storeAlphaChannel Whether or not to store the alpha channel
     * @return                  A {@link graphics.TextureReader.Texture} object containing the data in the texture
     */
    private TextureReader.Texture getTexture(String filename, boolean storeAlphaChannel) {
        TextureReader.Texture ret = null;
        try {
            ret = TextureReader.readTexture(filename, storeAlphaChannel);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return ret;
    }

    /**
     * Marks this texture as the active one
     */
    public void bind() {
        graphicsManager.getGlObject().glBindTexture(GL.GL_TEXTURE_2D, textureId[0]);
    }

    /**
     * Constructs the texture and use mipmapping in the process
     */
    private void makeRGBTexture() {
        if(transparent)
            graphicsManager.getGluObject().gluBuild2DMipmaps(GL.GL_TEXTURE_2D, GL.GL_RGBA, texture.getWidth(), texture.getHeight(), GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, texture.getPixels());
        else
            graphicsManager.getGluObject().gluBuild2DMipmaps(GL.GL_TEXTURE_2D, GL.GL_RGB, texture.getWidth(), texture.getHeight(), GL.GL_RGB, GL.GL_UNSIGNED_BYTE, texture.getPixels());
    }
}
