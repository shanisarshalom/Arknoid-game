//209583038 Shani Sar Shalom
import java.util.ArrayList;
/**
 * class goal - create rectangle, check intersectionPoints with other lines.
 */
public class Rectangle {
    //fields
    private Point upperLeft;
    private double width;
    private double height;
    static final double DEFAULT = 100;
    /**
     * constructor of Ball - Create a new rectangle with location and
     * width/height.
     * @param upperLeft     - the upperLeft point of the rectangle.
     * @param width     - the width of the rectangle.
     * @param height     - the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        //check if invalid input, if invalid make it default value
        if (upperLeft == null) {
            upperLeft = new Point(DEFAULT, DEFAULT);
        }
        //check if invalid input, if negative multiply by -1
        if (width < 0) {
            width = -width;
        }
        if (height < 0) {
            height = -height;
        }
        //check if invalid input, if invalid make it default value
        if (Double.isNaN(width) || Double.isInfinite(width)) {
            width = DEFAULT;
        }
        if (Double.isNaN(height) || Double.isInfinite(height)) {
            height = DEFAULT;
        }
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }
    /**
     * @param line     - the line to check intersection points.
     * @return - Return a (possibly empty) List of intersection points with the
     * specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // check if line is valid
        if (line == null) {
            return null;
        }
        //create the list and the rectangle lines
        java.util.List<Point> intersectionPoints = new ArrayList<>();
        // call isIntersecting method
        // call intersectionWith method if isIntersecting is true and add the
        // point to list
        if (this.getUpperLine().isIntersecting(line)) {
            intersectionPoints.add(getUpperLine().intersectionWith(line));
        }
        if (this.getLowerLine().isIntersecting(line)) {
            intersectionPoints.add(this.getLowerLine().intersectionWith(line));
        }
        if (this.getLeftLine().isIntersecting(line)) {
            intersectionPoints.add(this.getLeftLine().intersectionWith(line));
        }
        if (this.getRightLine().isIntersecting(line)) {
            intersectionPoints.add(this.getRightLine().intersectionWith(line));
        }
        return intersectionPoints;
    }
    /**
     * @return -  the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * @return -  the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * @return -  Returns the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * @return -  Returns the upper line of the rectangle.
     */
    public Line getUpperLine() {
        Double uppY = upperLeft.getY();
        Double rightX = upperLeft.getX() + this.width;
        Point upperRight = new Point(rightX, uppY);
        return new Line(upperLeft, upperRight);
    }
    /**
     * @return -  Returns the lower line of the rectangle.
     */
    public Line getLowerLine() {
        Double leftX = upperLeft.getX();
        Double lowY = upperLeft.getY() + this.height;
        Double rightX = upperLeft.getX() + this.width;
        Point lowerRight = new Point(rightX, lowY);
        Point lowerLeft = new Point(leftX, lowY);
        return new Line(lowerLeft, lowerRight);
    }
    /**
     * @return -  Returns the right line of the rectangle.
     */
    public Line getRightLine() {
        Double uppY = upperLeft.getY();
        Double lowY = upperLeft.getY() + this.height;
        Double rightX = upperLeft.getX() + this.width;
        Point upperRight = new Point(rightX, uppY);
        Point lowerRight = new Point(rightX, lowY);
        return new Line(lowerRight, upperRight);
    }
    /**
     * @return -  Returns the left line of the rectangle.
     */
    public Line getLeftLine() {
        Double leftX = upperLeft.getX();
        Double lowY = upperLeft.getY() + this.height;
        Point lowerLeft = new Point(leftX, lowY);
        return new Line(lowerLeft, upperLeft);
    }
}