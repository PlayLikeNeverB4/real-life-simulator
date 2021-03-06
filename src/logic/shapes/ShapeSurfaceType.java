package logic.shapes;

import graphics.TextureHandler;
import logic.Dimension;

import java.awt.*;

/**
 * Represents how a surface is rendered
 */
public class ShapeSurfaceType {

    private TextureHandler textureHandler;
    private Dimension textureDimension;
    private Color color;

    public ShapeSurfaceType() {
        textureHandler = null;
        textureDimension = null;
        color = null;
    }

    /**
     * Constructor used for a surface that will be covered with a texture
     * @param textureHandler    The texture for rendering the shape
     * @param textureDimension  The dimension of texture in the game world; If the object is larger it will be repeated
     */
    public ShapeSurfaceType(TextureHandler textureHandler, Dimension textureDimension) {
        this.textureHandler = textureHandler;
        this.textureDimension = textureDimension;
    }

    /**
     * Constructor used for a surface that will be covered with a texture
     * @param textureHandler    The texture for rendering the shape
     */
    public ShapeSurfaceType(TextureHandler textureHandler) {
        this.textureHandler = textureHandler;
        this.textureDimension = null;
    }

    /**
     * Constructor used for a surface that will be covered with a color
     * @param color     The {@link Color} for rendering the shape
     */
    public ShapeSurfaceType(Color color) {
        this.color = color;
    }

    public ShapeSurfaceType(ShapeSurfaceType surfaceType) {
        this.color = surfaceType.getColor();
        this.textureHandler = surfaceType.getTextureHandler();
        this.textureDimension = surfaceType.getTextureDimension();
    }

    public TextureHandler getTextureHandler() {
        return textureHandler;
    }

    public Dimension getTextureDimension() {
        return textureDimension;
    }

    public Color getColor() {
        return color;
    }

    /**
     * Test if the rendering for a surface will be done by using texture or color
     * @return      true if this surface is rendered as if it is covered with a texture, and false otherwise
     */
    public boolean isTextured() {
        return textureHandler != null;
    }

}
