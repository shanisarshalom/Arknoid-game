//209583038 Shani Sar Shalom

/**
 * class goal - will be used by things that can be collided with.
 */
public interface Collidable {
    /**
     * @return - Return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * @param collisionPoint - coliision point of collidable with other object.
     * @param currentVelocity - the velocity of the other object.
     * @param hitter - the ball that hit.
     * @return - the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}