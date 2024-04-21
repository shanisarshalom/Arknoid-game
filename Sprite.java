//209583038 Shani Sar Shalom
import biuoop.DrawSurface;
/**
 * class goal - draw on the screen the sprites,
 * and notified that time has passed.
 */
public interface Sprite {
    /**
     *  draw the sprite to the screen.
     * @param d - the draw surface to draw on.
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}