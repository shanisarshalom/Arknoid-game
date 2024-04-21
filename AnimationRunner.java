//209583038 Shani Sar Shalom
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The AnimationRunner takes an Animation object and runs it.
 */
public class AnimationRunner {
    // fields.
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * constructor.
     * @param gui - the gui torun on it.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * loop method to run the animation.
     * @param animation - the required animation to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        // loop until should stop.
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            // call required animation to run frame.
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}