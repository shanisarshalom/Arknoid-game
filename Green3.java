//209583038 Shani Sar Shalom
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * level "direct Hit" information.
 */
public class Green3 implements LevelInformation {
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 600;

    /**
     *
     * @return number of balls.
     */
    @Override
    public int numberOfBalls() {
        return 2;
    }

    /**
     *
     * @return list of velocity of balls.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(-30, 6));
        velocities.add(Velocity.fromAngleAndSpeed(30, 6));
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
        return "Green 3";
    }

    /**
     *
     * @return a sprite with the background of the level.
     */
    @Override
    public Sprite getBackground() {
        Color darkGreen = new Color(0, 100, 0);
        return new Block(new Rectangle(new Point(0, 15),
                SCREEN_WIDTH, SCREEN_HEIGHT), darkGreen);
    }

    /**
     * The Blocks that make up this level, each block contains its size,
     * color and location.
     * @return list of blocks in level.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        // call a addBlocks method
        Point startOfBlocks = addBlocks(new Point(275, 150), blocks,
                Color.GRAY, 10);
        startOfBlocks = addBlocks(startOfBlocks, blocks, Color.RED,
                9);
        startOfBlocks = addBlocks(startOfBlocks, blocks, Color.YELLOW,
                8);
        startOfBlocks = addBlocks(startOfBlocks, blocks, Color.BLUE,
                7);
        addBlocks(startOfBlocks, blocks, Color.WHITE, 6);
        return blocks;
    }

    /**
     * add blocks of game to the list.
     * @param startOfBlocks - the start point of blocks in line.
     * @param blocks - the list to add it the required blocks.
     * @param color - color of current blocks to add.
     * @param numOfBlocks - the num of blocks to add.
     * @return the new start point of next line.
     */
    public Point addBlocks(Point startOfBlocks, List<Block> blocks, Color color,
                           int numOfBlocks) {
        int width = 50;
        int hight = 30;
        Point saveStart = startOfBlocks;
        for (int i = 0; i < numOfBlocks; i++) {
            blocks.add(new Block(new Rectangle(startOfBlocks, width,
                    hight), color));
            startOfBlocks = new Point(startOfBlocks.getX() + width,
                    startOfBlocks.getY());
        }
        return new Point(saveStart.getX() + width, saveStart.getY()
                + hight);
    }

    /**
     *
     * @return the number of blocks in level.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }

    /**
     *
     * @return the color of paddle.
     */
    public Color getPaddaleColor() {
        return Color.GREEN;
    }
}
