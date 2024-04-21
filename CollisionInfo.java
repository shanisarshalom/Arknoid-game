//209583038 Shani Sar Shalom

/**
 * class goal - hold the information of the point at which the collision occurs
 * and the collidable object involved in the collision.
 */
public class CollisionInfo {
    //fields
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor of collisionInfo.
     * @param collisionPoint - the point at which the collision occurs.
     * @param collisionObject - the collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * @return - the point at which the collision occurs
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return - the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}