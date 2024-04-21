//209583038 Shani Sar Shalom
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * wrap an existing animation and add a "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * constructor.
     * @param sensor - Keyboard Sensor.
     * @param key - the key press.
     * @param animation - the animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key,
                                      Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * run animation until user stop.
     * @param d - surface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            if (!isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            isAlreadyPressed = false;
        }
    }

    /**
     *
     * @return true if should stop, false otherwise.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}