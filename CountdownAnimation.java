//209583038 Shani Sar Shalom
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * display few seconds of wait before the game starts.
 */
public class CountdownAnimation implements Animation {
    private boolean stop;
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;

    /**
     * constructor.
     * @param numOfSeconds - num of seconds to show the seconds.
     * @param countFrom - the num to count from to zero.
     * @param gameScreen - all sprites on current level.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen) {
        this.stop = false;
        this.numOfSeconds = 40 * numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
    }

    /**
     * run one frame of counting.
     * @param d - surface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // draw sprites of level in order to display the on top of the game.
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.RED);
        d.drawText(d.getWidth() / 2 - 25, d.getHeight() / 2 + 20,
                Integer.toString(countFrom), 100);
        // update the num of seconds
        numOfSeconds--;
        // each number will appear on the screen for (numOfSeconds / countFrom)
        if (numOfSeconds / countFrom == 0) {
            countFrom--;
            numOfSeconds = 30;
        }
        // stop counting when get to 0.
        if (countFrom == 0) {
            d.setColor(Color.RED);
            d.drawText(d.getWidth() / 2 - 25, d.getHeight() / 2 + 20,
                    Integer.toString(countFrom), 100);
            this.stop = true;
        }
    }

    /**
     * indicate when to stop running.
     * @return true if got to zero.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}