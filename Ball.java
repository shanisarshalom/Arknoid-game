//209583038 Shani Sar Shalom
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class goal - create ball, draw it on surface and change velocity as required.
 */
public class Ball implements Sprite {
    //fields
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    static final int DEFAULT_R = 15;
    private GameEnvironment gameEnvironment;
    private List<HitListener> hitListeners;
    static final double EPSILON = 0.0001;
    // constructors
    /**
     * constructor of Ball.
     * @param x - x value of point center of the ball.
     * @param y - y  value of point center of the ball.
     * @param r - radius of the ball.
     * @param color - color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        if (r < 0) {
            r = DEFAULT_R;
        }
        if (color == null) {
            color = java.awt.Color.BLACK;
        }
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        // init velocity
        this.velocity = new Velocity(0, 0);
        this.hitListeners = new ArrayList<>();
    }
    /**
     * constructor of Ball.
     * @param center -value of point center of the ball.
     * @param r      - radius of the ball.
     * @param color  - color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        if (r < 0) {
            r = DEFAULT_R;
        }
        if (color == null) {
            color = java.awt.Color.BLACK;
        }
        this.r = r;
        this.color = color;
        // init velocity
        this.velocity = new Velocity(0, 0);
    }
    /**
     * @return - x value of center point.
     */
    // accessors
    public int getX() {
        return (int) center.getX();
    }
    /**
     * @return - y value of center point.
     */
    public int getY() {
        return (int) center.getY();
    }
    /**
     * @return - radius value of the ball.
     */
    public int getSize() {
        return this.r;
    }
    /**
     * @return - color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * draw the ball on the given DrawSurface.
     * @param surface - given DrawSurface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(getColor());
        surface.fillCircle(getX(), getY(), getSize());
        surface.setColor(Color.BLACK); // This will be the color of the border.
        surface.drawCircle(getX(), getY(), getSize());
    }
    /**
     * set velocity of the ball.
     *
     * @param v - values of the new required velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;

    }
    /**
     * set velocity of the ball.
     *
     * @param dx - horizen value of the new required velocity.
     * @param dy - vertical value of the new required velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }
    /**
     * @return - velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }
    /**
     * move the direction of the ball if hits the border.
     * @param gameEnvironment = the game environment to check collision
     */
    public void moveOneStep(GameEnvironment gameEnvironment) {
        // create the vector of the ball.
        Point endTrajectory = this.getVelocity().applyToPoint(this.center);
        Line trajectory = new Line(this.center, endTrajectory);
        CollisionInfo collisionInfo =
                gameEnvironment.getClosestCollision(trajectory);
        // if there isn't collision continue to endTrajectory.
        if (collisionInfo == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            // there is a collision.
            double saveX = this.center.getX();
            double saveY = this.center.getY();
            double dx;
            double dy;
            Point collisionPoint = gameEnvironment.
                    getClosestCollision(trajectory).collisionPoint();
            // calculate the new positive of center.
            // plus or minus 1 to move the ball "almost" to the collision point.
            if (isPositive(this.velocity.getDx())) {
                dx = this.velocity.getDx() + 1;
            } else {
                dx = this.velocity.getDx() - 1;
            }
            if (isPositive(this.velocity.getDy())) {
                dy = this.velocity.getDy() + 1;
            } else {
                dy = this.velocity.getDy() - 1;
            }
            // init the point to add the velocity (dx, dy) to get the
            // required point.
            Point continueFromThisPoint = new Point(collisionPoint.getX()
                    - dx, collisionPoint.getY() - dy);
            // update location of the ball.
            this.center = this.getVelocity().
                    applyToPoint(continueFromThisPoint);
            // if the ball inside the paddle ( the paddle continue moving)
            if (this.center.getX() + EPSILON >= collisionInfo.collisionObject().
                    getCollisionRectangle().getUpperLeft().getX()
                    && this.center.getX() - EPSILON <= collisionInfo.
                    collisionObject().getCollisionRectangle().getUpperLeft().
                    getX() + collisionInfo.collisionObject().
                    getCollisionRectangle().getWidth() && this.center.getY()
                    + EPSILON >= collisionInfo.collisionObject().
                    getCollisionRectangle().getUpperLeft().getY()
                    && this.center.getY() + EPSILON <= collisionInfo.
                    collisionObject().getCollisionRectangle().getUpperLeft().
                    getY() + collisionInfo.collisionObject().
                    getCollisionRectangle().getHeight()) {
                continueFromThisPoint = new Point(saveX, saveY
                        - this.getSize());
                this.center = this.getVelocity().
                        applyToPoint(continueFromThisPoint);
            }
            // Notify the object about hit and update ball velocity.
            this.setVelocity(gameEnvironment.
                    getClosestCollision(trajectory).collisionObject().
                    hit(this, gameEnvironment.getClosestCollision(trajectory).
                            collisionPoint(), this.getVelocity()));
        }
    }

    /**
     * add to the sprite collection.
     * @param g = the game with the relevant sprites.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * set the gameEnvironment of the ball.
     * @param gameEnvironment = the game environment to update for.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * update the move of ball in the game.
     */
    public void timePassed() {
        moveOneStep(this.gameEnvironment);
    }

    /**
     * @param x - check this param.
     * @return true if x positive, false otherwise.
     */
    public Boolean isPositive(Double x) {
        return (x + EPSILON > 0);
    }

    /**
     * remove the ball from the game.
     * @param game - the current game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}