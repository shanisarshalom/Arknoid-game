//209583038 Shani Sar Shalom
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Once the game is over, display the final score.
 */
public class EndScreen implements Animation {
    private boolean stop;
    private String message;

    /**
     * constructor.
     * @param message - win or lose message.
     */
    public EndScreen(String message) {
        this.stop = false;
        this.message = message;
    }

    /**
     * draw the message.
     * @param d - surface to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(10, d.getHeight() / 2, message, 32);
    }

    /**
     * indicate if to stop show the animation.
     * @return true if should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}