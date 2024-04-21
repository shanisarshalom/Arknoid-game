//209583038 Shani Sar Shalom
import biuoop.DrawSurface;

/**
 * pause the game when pressing the p key.
 */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * constructor - init stop to false.
     */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * draw the message.
     * @param d - surface to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2,
                "paused -- press space to continue", 32);
    }

    /**
     *
     * @return true if should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.stop; }
}