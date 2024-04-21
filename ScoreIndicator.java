//209583038 Shani Sar Shalom
import biuoop.DrawSurface;

/**
 * charge of displaying the current score.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * constructor of class.
     * @param score - the counter to display.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * draw on surface the score.
     * @param d - the draw surface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.drawText(390, 10, "Score: " + score.getValue(), 10);
    }

    /**
     * no need for now.
     */
    public void timePassed() {
    }
}
