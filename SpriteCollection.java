//209583038 Shani Sar Shalom
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
/**
 * class goal - class supports the addition of new sprites.
 */
public class SpriteCollection {
    private List<Sprite> sprites = new ArrayList<>();
    /**
     * add the given Sprite to the environment.
     * @param s = the given Sprite.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * remove sprite from environment.
     * @param s - the sprite to remove.
     */
    public void removeSprite(Sprite s)  {
        sprites.remove(s);
    }
    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : new ArrayList<>(sprites)) {
            sprite.timePassed();
        }
    }
    /**
     * call drawOn(d) on all sprites.
     * @param d - the surface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}