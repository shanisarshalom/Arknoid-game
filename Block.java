//209583038 Shani Sar Shalom
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class goal - create block, draw it on surface and change velocity of
 * collision object with hit method.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //fields
    private Rectangle rectangle;
    private java.awt.Color color;
    private List<HitListener> hitListeners;

    /**
     * constructor of Block.
     *
     * @param rectangle - the shape of the block.
     * @param color     - color of the ball.
     */
    public Block(Rectangle rectangle, java.awt.Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * @return = Return the "collision shape" of the object.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     *
     * @param collisionPoint  - the collision point with other object.
     * @param currentVelocity - the current velocity of the other object.
     * @param hitter - the ball that hit.
     * @return = Return new velocity expected after the hit (based on
     * the force the object inflicted on us).
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // change dy
        if (this.rectangle.getUpperLine().pointIncludeInLine(collisionPoint)
                || this.rectangle.getLowerLine().
                pointIncludeInLine(collisionPoint)) {
            currentVelocity = new Velocity(currentVelocity.getDx(),
                    -currentVelocity.getDy());
        }
        // change dx
        if (this.rectangle.getLeftLine().pointIncludeInLine(collisionPoint)
                || this.rectangle.getRightLine().
                pointIncludeInLine(collisionPoint)) {
            currentVelocity = new Velocity(-(1) * currentVelocity.getDx(),
                    currentVelocity.getDy());
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * draw the block on the surface.
     * @param surface - the surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        int x = (int) this.rectangle.getUpperLeft().getX();
        int y = (int) this.rectangle.getUpperLeft().getY();
        int width = (int) this.rectangle.getWidth();
        int height = (int) this.rectangle.getHeight();
        surface.setColor(this.color);
        surface.fillRectangle(x, y, width, height);
        //if (this.color.equals(Color.GRAY)) {
            //surface.setColor(Color.GRAY);
        //} else {
            surface.setColor(Color.black);
        //}
        surface.drawRectangle(x, y, width, height);
    }

    /**
     * add the block to required collection in the game.
     * @param g - the relevant game with the relevant collections.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * will be use to update the position of the block.
     */
    public void timePassed() {
    }

    /**
     * add hit listener to the block.
     * @param hl - the hit listener.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * remove hit listener from the block.
     * @param hl - the hit listener.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify all listeners about a hit event.
     * @param hitter - the ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Remove the block from the game.
     * @param game - the current game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
}
