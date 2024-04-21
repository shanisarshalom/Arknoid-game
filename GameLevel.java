//209583038 Shani Sar Shalom
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * class goal - hold the sprites and the collidables, charge of the animation.
 */
public class GameLevel implements Animation {
    private AnimationRunner runner;
    private boolean running;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private ScoreTrackingListener scoreTrackingListener;
    private ScoreIndicator scoreIndicator;
    private Counter score;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 600;
    static final int HIGHT_BLOCKS = 20;
    static final int RADIUS_OF_BALL = 8;
    static final Color COLOR_OF_PADDLE = Color.YELLOW;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    /**
     * constructor.
     * @param levelInformation - the information of current level.
     * @param keyboardSensor - keyboard Sensor.
     * @param animationRunner - the animation runner.
     * @param score - the current score.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor
            keyboardSensor, AnimationRunner animationRunner, Counter score) {
        this.runner = animationRunner;
        this.keyboard = keyboardSensor;
        this.levelInformation = levelInformation;
        this.score = score;
    }

    /**
     * add required collidable to the collidable collection in environment.
     * @param c - collidable to add to environment's collidiable collection.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     *
     * @return the num of remaining blocks.
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     *
     * @return the num of remaining balls.
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    /**
     * remove required collidable from the collidable collection in environment.
     * @param c - collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * add required sprite to the sprites collection in environment.
     * @param s - sprite to add to sprites collection.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * remove required sprite from the sprites collection in environment.
     * @param s - the required sprite to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Initialize a new game by creating the Blocks, Ball and Paddle
     * and add them to the game.
     */
    public void initialize() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(0);
        this.scoreTrackingListener = new ScoreTrackingListener(this.score);
        this.blockRemover = new BlockRemover(this, remainingBlocks);
        this.ballRemover = new BallRemover(this, remainingBalls);
        this.scoreIndicator = new ScoreIndicator(score);
        sprites.addSprite(scoreIndicator);
        createDeathRegion(new Point(25, SCREEN_HEIGHT - 25),
                SCREEN_WIDTH - 50, 25);
        createBackgroung();
        createLimitScreenBlock(new Point(0, 15),
                SCREEN_WIDTH, 25);
        createLimitScreenBlock(new Point(0, 40),
                25, SCREEN_HEIGHT - 10);
        createLimitScreenBlock(new Point(SCREEN_WIDTH - 25,
                40), 25, SCREEN_HEIGHT - 25);
        createBlocksLevel();
        createPaddleLevel();
        this.remainingBalls.increase(this.levelInformation.numberOfBalls());
    }

    /**
     * Run the game - start the animation loop.
     */
    public void run() {
        this.createBallsLevel();
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(1, 3,
                this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * run one frame.
     * @param d - surface to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(650, 10, "Level Name: "
                + this.levelInformation.levelName(), 10);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (remainingBlocks.getValue() == 0) {
            this.score.increase(100);
        }
        if (remainingBlocks.getValue() == 0
                || remainingBalls.getValue() == 0) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    " ", new PauseScreen()));
        }
    }

    /**
     *
     * @return true of should stop running. false otherwise.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * init the balls on paddle to start from this location the level.
     */
    public void createBallsLevel() {
        Point onTopOfPaddale = new Point(SCREEN_WIDTH / 2,
                SCREEN_HEIGHT - HIGHT_BLOCKS - 25 - RADIUS_OF_BALL);
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(onTopOfPaddale, RADIUS_OF_BALL, Color.WHITE);
            ball.setVelocity(this.levelInformation.
                    initialBallVelocities().get(i));
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
        }
    }

    /**
     * add the blocks to game.
     */
    public void createBlocksLevel() {
        for (int i = 0; i < this.levelInformation.numberOfBlocksToRemove();
             i++) {
            Block block = this.levelInformation.blocks().get(i);
            block.addToGame(this);
            block.addHitListener(this.blockRemover);
            block.addHitListener(this.scoreTrackingListener);
        }
        this.remainingBlocks.increase(this.levelInformation.
                numberOfBlocksToRemove());
    }

    /**
     * create the limit of the screen with grey blocks.
     * @param startOfBlocks - the left point of rect.
     * @param width - width of rect.
     * @param hight -  hight of rect.
     */
    public void createLimitScreenBlock(Point startOfBlocks, double width,
                                       double hight) {
        Block block = new Block(new Rectangle(startOfBlocks, width,
                hight), Color.GRAY);
        block.addToGame(this);
    }

    /**
     * create the death region which indicate that ball need to be removed.
     * @param startOfBlocks - the left point of rect.
     * @param width - width of rect.
     * @param hight -  hight of rect.
     */
    public void createDeathRegion(Point startOfBlocks, double width,
                                  double hight) {
        Block block = new Block(new Rectangle(startOfBlocks, width, hight),
                this.levelInformation.getPaddaleColor());
        block.addToGame(this);
        block.addHitListener(this.ballRemover);
    }

    /**
     * create the paddle = player.
     */
    public void createPaddleLevel() {
        Paddle paddle = new Paddle(keyboard, new Rectangle(
                new Point(SCREEN_WIDTH / 2 - this.levelInformation.
                        paddleWidth() / 2,
                        SCREEN_HEIGHT - HIGHT_BLOCKS - 25),
                this.levelInformation.paddleWidth(), HIGHT_BLOCKS),
                this.levelInformation.paddleSpeed(),
                COLOR_OF_PADDLE);
        paddle.addToGame(this);
    }

    /**
     * create the screen.
     */
    public void createBackgroung() {
        Sprite background = this.levelInformation.getBackground();
        addSprite(background);
    }
}