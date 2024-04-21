//209583038 Shani Sar Shalom
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * level "direct Hit" information.
 */
public class WideEasy implements LevelInformation {
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 600;

    /**
     *
     * @return number of balls.
     */
    @Override
    public int numberOfBalls() {
        return 10;
    }

    /**
     *
     * @return list of velocity of balls.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        double speed = 5;
        // balls in rainbow shape
        for (int i = 0; i < this.numberOfBalls(); i++) {
            double angle;
            if (i < 5) {
                angle = -60 + i * 12;
            } else {
                angle = -60 + (i + 1) * 12;
            }
            velocities.add(Velocity.fromAngleAndSpeed(angle, speed));
        }
        return velocities;
    }

    /**
     *
     * @return the speed of paddle.
     */
    @Override
    public int paddleSpeed() {
        return 2;
    }

    /**
     *
     * @return the width of paddle.
     */
    @Override
    public int paddleWidth() {
        return 600;
    }

    /**
     *
     * @return the name of level.
     */
    @Override
    public String levelName() {
        return "Wide Easy";
    }

    /**
     *
     * @return a sprite with the background of the level.
     */
    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 15),
                SCREEN_WIDTH, SCREEN_HEIGHT), Color.WHITE);
    }

    /**
     * The Blocks that make up this level, each block contains its size,
     * color and location.
     * @return list of blocks in level.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Point startOfBlocks = addBlocks(new Point(25, 200), blocks,
                Color.RED, 2);
        startOfBlocks = addBlocks(startOfBlocks, blocks, Color.ORANGE,
                2);
        startOfBlocks = addBlocks(startOfBlocks, blocks, Color.YELLOW,
                2);
        startOfBlocks = addBlocks(startOfBlocks, blocks, Color.GREEN,
                3);
        startOfBlocks = addBlocks(startOfBlocks, blocks, Color.BLUE,
                2);
        startOfBlocks = addBlocks(startOfBlocks, blocks, Color.PINK,
                2);
        Color lightBlue = new Color(135, 206, 235);
        addBlocks(startOfBlocks, blocks, lightBlue, 2);
        return blocks;
    }

    /**
     * add blocks of game to the list.
     * @param startOfBlocks - the start point of blocks in line.
     * @param blocks - the list to add it the required blocks.
     * @param color - color of current blocks to add.
     * @param numOfBlocks - the num of blocks to add.
     * @return the new start point of next blocks.
     */
    public Point addBlocks(Point startOfBlocks, List<Block> blocks, Color color,
                           int numOfBlocks) {
        int width = 50;
        int hight = 30;
        for (int i = 0; i < numOfBlocks; i++) {
            blocks.add(new Block(new Rectangle(startOfBlocks, width,
                    hight), color));
            startOfBlocks = new Point(startOfBlocks.getX() + width,
                    startOfBlocks.getY());
        }
        return startOfBlocks;
    }

    /**
     *
     * @return the number of blocks in level.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

    /**
     *
     * @return the color of paddle.
     */
    public Color getPaddaleColor() {
        return Color.WHITE;
    }
}
