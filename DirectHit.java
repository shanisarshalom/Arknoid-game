//209583038 Shani Sar Shalom
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * level "direct Hit" information.
 */
public class DirectHit implements LevelInformation {
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 600;

    /**
     *
     * @return number of balls.
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     *
     * @return list of velocity of balls.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(0, -5));
        return velocities;
    }

    /**
     *
     * @return the speed of paddle.
     */
    @Override
    public int paddleSpeed() {
        return 10;
    }

    /**
     *
     * @return the width of paddle.
     */
    @Override
    public int paddleWidth() {
        return 100;
    }

    /**
     *
     * @return the name of level.
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     *
     * @return a sprite with the background of the level.
     */
    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 15),
                SCREEN_WIDTH, SCREEN_HEIGHT), Color.BLACK);
    }

    /**
     * The Blocks that make up this level, each block contains its size,
     * color and location.
     * @return list of blocks in level.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(new Rectangle(new Point(SCREEN_WIDTH / 2 - 12.5,
                150), 25, 25), Color.RED));
        return blocks;
    }

    /**
     *
     * @return the number of blocks in level.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    /**
     *
     * @return the color of paddle.
     */
    public Color getPaddaleColor() {
        return Color.BLACK;

    }
}
