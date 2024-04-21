//209583038 Shani Sar Shalom
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * support for moving from one level to the next.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;

    /**
     * constructor.
     * @param ar - the animation runner.
     * @param ks -  the Keyboard Sensor.
     * @param score - the current score.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, Counter score) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = score;
    }

    /**
     * run levels as the list order.
     * @param levels - the order of running levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                    this.animationRunner, this.score);
            level.initialize();
            while (level.getRemainingBalls().getValue() > 0
                    && level.getRemainingBlocks().getValue() > 0) {
                level.run();
            }
            // game over.
            if (level.getRemainingBalls().getValue() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(
                        this.keyboardSensor, " ", new EndScreen(
                                "Game Over. Your score is "
                                        + this.score.getValue())));
                return;
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(
                this.keyboardSensor, " ", new EndScreen(
                        "You Win! Your score is " + this.score.getValue())));
    }
}