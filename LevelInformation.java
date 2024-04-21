//209583038 Shani Sar Shalom
import java.awt.Color;
import java.util.List;

/**
 * each level information.
 */
public interface LevelInformation {

    /**
     *
     * @return number of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * @return list of velocity of balls.
     */
    List<Velocity> initialBallVelocities();

    /**
     *
     * @return the speed of paddle.
     */
    int paddleSpeed();

    /**
     *
     * @return the width of paddle.
     */
    int paddleWidth();

    /**
     *
     * @return the name of level.
     */
    String levelName();

    /**
     *
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size,
     * color and location.
     * @return list of blocks in level.
     */
    List<Block> blocks();

    /**
     *
     * @return the number of blocks in level.
     */
    int numberOfBlocksToRemove();

    /**
     *
     * @return the color of paddle.
     */
    Color getPaddaleColor();
}