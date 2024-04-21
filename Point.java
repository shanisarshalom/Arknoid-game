//209583038 Shani Sar Shalom
/**
 * class goal - init point with values, measure distance to other points and.
 * check if equal to another point.
 */
public class Point {
    //fields
    private double x;
    private double y;
    //threshold to compare doubles
    static final double EPSILON = 0.0001;
    static final double DEFAULT = 100;
    // constructor
    /**
     * constructor of point.
     * @param x - double, x value of the point.
     * @param y - double, y value of the point.
     */
    public Point(double x, double y) {
        //check if invalid input, if invalid make it default value
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            x = DEFAULT;
        }
        if (Double.isNaN(y) || Double.isInfinite(y)) {
            y = DEFAULT;
        }
        this.x = x;
        this.y = y;
    }
    /**
     * measure distance to other points.
     * @param other - Point, other point to measure distance to this point.
     * @return - distance to other points, or DEFAULT if invalid input.
     */
    // distance -- return the distance of this point to the other point
    public double distance(Point other) {
        // check validate
        if (other != null) {
            //use of formula of calculating distance
            return Math.sqrt(((x - other.x) * (x - other.x)) + ((y - other.y)
                    * (y - other.y)));
        }
        return DEFAULT;
    }

    /**
     * check if equal to another point.
     * @param other - Point, other point to compare.
     * @return - true is the points are equal, false otherwise.
     */
    // equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        // check validate
        if (other != null) {
            //call other method to check x and y respectively
            return (equalsValues(getX(), other.getX())
                    && equalsValues(getY(), other.getY()));
        }
        return false;
    }
    /**
     * check if equals values.
     * @param x - double to compare with.
     * @param y - double to compare with.
     * @return - true is the values are equal, false otherwise.
     */
    public boolean equalsValues(double x, double y) {
        return (Math.max(x, y) - Math.min(x, y) < EPSILON);
    }
    /**
     * return the x value of this point.
     * @return - the x value of this point.
     */
    // Return the x and y values of this point
    public double getX() {
        return this.x;
    }
    /**
     * return the y value of this point.
     * @return - the y value of this point.
     */
    public double getY() {
        return this.y;
    }
}