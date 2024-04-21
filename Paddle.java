//209583038 Shani Sar Shalom
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
/**
 * class goal - create paddle - block controlled by the keyboard,
 * move it as required and draw it.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private int speed;
    static final int SCREEN_WIDTH = 800;
    private java.awt.Color color;
    /**
     * constructor of paddle.
     * @param keyboard - keyboard sensor which indicate which direct to move.
     * @param paddleRect - the shape (rectangle) of the paddle.
     * @param paddleSpeed - speed of the paddle.
     * @param color - color of the paddle.
     */
    public Paddle(KeyboardSensor keyboard, Rectangle paddleRect,
                  int paddleSpeed, java.awt.Color  color) {
        this.keyboard = keyboard;
        this.paddle = paddleRect;
        this.speed = paddleSpeed;
        this.color = color;
    }
    /**
     * update the location of the paddle while saving the screen boundaries.
     */
    public void moveLeft() {
        if (this.paddle.getUpperLeft().getX() - this.speed > 25) {
            Point newLocation = new Point(this.paddle.getUpperLeft().getX()
                    - speed, this.paddle.getUpperLeft().getY());
            this.paddle = new Rectangle(newLocation, this.paddle.getWidth(),
                    this.paddle.getHeight());
        } else {
            Point newLocation = new Point(25, this.paddle.getUpperLeft().getY());
            this.paddle = new Rectangle(newLocation, this.paddle.getWidth(),
                    this.paddle.getHeight());
        }
    }
    /**
     * update the location of the paddle while saving the screen boundaries.
     */
    public void moveRight() {
        if (this.paddle.getUpperLeft().getX() + this.paddle.getWidth()
                + this.speed < SCREEN_WIDTH - 25) {
            Point newLocation = new Point(this.paddle.getUpperLeft().getX()
                    + speed, this.paddle.getUpperLeft().getY());
            this.paddle = new Rectangle(newLocation, this.paddle.getWidth(),
                    this.paddle.getHeight());
        } else {
            Point newLocation = new Point(SCREEN_WIDTH - 25
                    - this.paddle.getWidth(), this.paddle.getUpperLeft().getY());
            this.paddle = new Rectangle(newLocation, this.paddle.getWidth(),
                    this.paddle.getHeight());
        }
    }
    // Sprite
    /**
     * update the location of the paddle bu calling the required method, as the
     * keyboard sensor.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }
    /**
     * draw the paddle on the surface.
     * @param d = given DrawSurface to draw on it.
     */
    public void drawOn(DrawSurface d) {
        int x = (int) this.paddle.getUpperLeft().getX();
        int y = (int) this.paddle.getUpperLeft().getY();
        int width = (int) this.paddle.getWidth();
        int height = (int) this.paddle.getHeight();
        d.setColor(this.color);
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.black);
        d.drawRectangle(x, y, width, height);
    }
    // Collidable
    /**
     * @return the paddle- its the collision rectangle in this class.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }
    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * @param collisionPoint - the collision point with other object.
     * @param currentVelocity - the current velocity of the other object.
     * @param hitter - the ball that hit.
     * @return = Return new velocity expected after the hit (based on
     * the force the object inflicted on us). return in accordance to the region
     * of collision on the paddle.
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        // if the collision on the sides lines of the paddle.
        if (this.paddle.getLeftLine().pointIncludeInLine(collisionPoint)
                || this.paddle.getRightLine().
                pointIncludeInLine(collisionPoint)) {
            return new Velocity(-currentVelocity.getDx(),
                    currentVelocity.getDy());
        }
        // get the region where the collision point.
        int region = (int) (((collisionPoint.getX() - this.paddle.
                getUpperLeft().getX()) / (this.paddle.getWidth() / 5)) + 1);
        double speed = currentVelocity.getSpeed();
        if (region == 1) {
            return Velocity.fromAngleAndSpeed(300, speed);
        } else if (region == 2) {
            return Velocity.fromAngleAndSpeed(330, speed);
        } else if (region == 4) {
            return Velocity.fromAngleAndSpeed(30, speed);
        } else if (region == 5) {
            return Velocity.fromAngleAndSpeed(60, speed);
            // the collision in the middle
        } else {
            return new Velocity(currentVelocity.getDx(),
                    -currentVelocity.getDy());
        }
    }
    /**
     * Add this paddle to the game.
     * @param g = given game to add to it's collection.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}