//209583038 Shani Sar Shalom
import java.util.ArrayList;
import java.util.List;

/**
 * class goal - holds a list of Collidable objects.
 */
public class GameEnvironment {
    //fields
    static final double EPSILON = 0.0001;
    private List<Collidable> collidables = new ArrayList<>();

    /**
     * add the given collidable to the environment.
     * @param c - the given collidable.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * remove the collidable from list.
     * @param c - th collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * get the closest collision by assuming an object moving from line.start()
     * to line.end().
     * @return  - If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory - the line to check.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (collidables.isEmpty()) {
            return null;
        }
        // init closest collision info.
        CollisionInfo closestCollision = null;
        for (Collidable c : getCollidables()) {
            Rectangle collisionShape = c.getCollisionRectangle();
            if (trajectory.closestIntersectionToStartOfLine(collisionShape)
                    != null) {
                // if the first to check or closer - update the closest info.
                if (closestCollision == null
                        || closestCollision.collisionPoint().
                                distance(trajectory.start())
                        > trajectory.closestIntersectionToStartOfLine(
                                collisionShape).distance(trajectory.start())
                        - EPSILON) {
                    closestCollision = new CollisionInfo(
                            trajectory.closestIntersectionToStartOfLine(
                                    collisionShape), c);
                }
            }
        }
        return closestCollision;
    }

    /**
     *
     * @return copy of the list of collidables.
     */
    public List<Collidable> getCollidables() {
        return new ArrayList<>(collidables);
    }
}