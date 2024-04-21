//209583038 Shani Sar Shalom
/**
 * class goal - specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    //fields
    private double dx;
    private double dy;

    // constructor
    /**
     * construct velocity.
     * @param dx - horizen direction.
     * @param dy - vertical direction.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * construct velocity from angle and speed.
     * @param angle - angle of moving ball.
     * @param speed - speed of moving ball.
     * @return - new velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // using formula of trigo to convert angle to radian
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }
    /**
     * @return - calculated speed with the dx, dy.
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }
    /**
     * Take a point (x,y) and return a new point with position (x+dx, y+dy).
     * @param p -  point with position (x,y).
     * @return - point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
    /**
     * @return - dx.
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * @return - dy.
     */
    public double getDy() {
        return this.dy;
    }
}