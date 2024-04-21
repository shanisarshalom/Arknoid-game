//209583038 Shani Sar Shalom
/**
 * class goal - init line with start point and an end point, check length of.
 * line, intersect with other lines anf if equal another line segment.
 */
public class Line {
    //fields
    private Point start;
    private Point end;
    //threshold to compare doubles
    static final double EPSILON = 0.0001;
    static final double DEFAULT = 100;
    // constructors
    /**
     * constructor of line.
     * @param start - start point of line.
     * @param end - end point of line.
     */
    public Line(Point start, Point end) {
        //check if invalid input, if invalid make it default value
        if (start == null) {
            start = new Point(DEFAULT, DEFAULT);
        }
        if (end == null) {
            end = new Point(DEFAULT, DEFAULT);
        }
        // make sure that the points are distinct
        if (start.equals(end)) {
            end = new Point(start.getX() + 1, start.getY() + 1);
        }
        this.start = start;
        this.end = end;
    }
    /**
     * constructor of line.
     * @param x1 - x value of start point of line.
     * @param y1 - y value of start point of line.
     * @param x2 - x value of end point of line.
     * @param y2 - y value of end point of line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        //check if invalid input, if invalid make it default value
        if (Double.isNaN(x1) || Double.isInfinite(x1)) {
            x1 = DEFAULT;
        }
        if (Double.isNaN(y1) || Double.isInfinite(y1)) {
            y1 = DEFAULT;
        }
        if (Double.isNaN(x2) || Double.isInfinite(x2)) {
            x2 = DEFAULT;
        }
        if (Double.isNaN(y2) || Double.isInfinite(y2)) {
            y2 = DEFAULT;
        }
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }
    // Return the length of the line
    /**
     * calculate length of the line.
     * @return - length of the line.
     */
    public double length() {
        //the distance equal to the distance between the start and end point
        return start.distance(end);
    }
    /**
     * calculate the middle point of the line.
     * @return - middle point of the line.
     */
    // Returns the middle point of the line
    public Point middle() {
        // the middle point is the division by 2 of sum of the points
        double midX = ((start.getX() + end.getX()) / 2);
        double midY = ((start.getY() + end.getY()) / 2);
        return new Point(midX, midY);
    }
    /**
     * @return - start point of the line.
     */
    // Returns the start point of the line
    public Point start() {
        return new Point(start.getX(), start.getY());
    }
    /**
     * @return - end point of the line.
     */
    // Returns the end point of the line
    public Point end() {
        return new Point(end.getX(), end.getY());
    }
    /**
     * check if the line is vertical.
     * @return - true if vertical, false otherwise.
     */
    public boolean isVertical() {
        return (equalsValues(end.getX(), start.getX()));
    }
    /**
     * calculate the slope of the line.
     * @return - the slope of the line.
     */
    public double slope() {
        //if vertical return zero in order to avoid division fo zero
        if (!isVertical()) {
            // use formula of calculate slope
            return (end.getY() - start.getY()) / (end.getX() - start.getX());
        }
        return 0;
    }
    /**
     * @return - intercept in equasion of a line.
     */
    public double intercept() {
        // use formula of calculate intercept
        return (start.getY() - (slope() * start.getX()));
    }

    /**
     * @param other - other line to check if the line intersect with it.
     * @return - true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        //check lines overlap be checking slopes and intercepts and midpoint
        // of the lines - if overlap return true
        if (equalsValues(slope(), other.slope()) && equalsValues(intercept(),
                other.intercept()) && (checkPointIntersection(start(), other)
                || checkPointIntersection(end(), other))) {
            return true;
        }
        return (intersectionWith(other) != null);
    }
    /**
     * @param other - other line to check intersection point if lines intersect.
     * @return - intersection point if the lines intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {
        // init x and y values of intersection point
        double xIntersection;
        double yIntersection;
        //use formula of equasion of a line to find x and y values
        // if line is vertical avoid from division by zero
        if (isVertical()) {
            xIntersection = start.getX();
            yIntersection = (other.slope() * xIntersection) + other.intercept();
        } else if (other.isVertical()) {
            xIntersection = other.start.getX();
            yIntersection = (slope() * xIntersection) + intercept();
        } else if (start.equals(other.end) && !(equals(other))
                && !(other.pointIncludeInLine(end)
                || pointIncludeInLine(other.start))) {
            //check if start or and equals, and if the lines are not inclusion
            return start;
        } else if (start.equals(other.start) && !(equals(other))
                && !(other.pointIncludeInLine(end)
                        || pointIncludeInLine(other.end))) {
            return start;
        } else if (end.equals(other.end) && !(equals(other))
                && !(other.pointIncludeInLine(start)
                || pointIncludeInLine(other.start))) {
            return end;
        } else if (end.equals(other.start) && !(equals(other))
                && !(other.pointIncludeInLine(start)
                || pointIncludeInLine(other.end))) {
            return end;
        } else if (equalsValues(slope(), other.slope())) {
            return null;
        } else {
            xIntersection = (other.intercept() - intercept())
                    / (slope() - other.slope());
            yIntersection = (slope() * xIntersection) + intercept();
        }
        // create the point
        Point intersection = new Point(xIntersection, yIntersection);
        //check that point on the lines-segment
        if (!checkPointIntersection(intersection, other)) {
            return null;
        }
        return intersection;
    }
    /**
     * check that point on the lines-segment.
     * @param point - point to check if on the lines-segment.
     * @param other - other line to check intersection point.
     * @return - true if the point on the lines-segment, false otherwise.
     */
    public boolean checkPointIntersection(Point point, Line other) {
        // check if x and y values of the point is between the x and y values
        // of the start and end points of lines respectively
        //if (start.equals(point) || end.equals(point) ||
                //other.start.equals(point) || other.end.equals(point))
        //{
            //return true;
        //}
        return (point.getX() <= Math.max(start.getX() + EPSILON, end.getX()
                + EPSILON)
                && point.getX() >= Math.min(start.getX() - EPSILON, end.getX()
                        - EPSILON)
                && point.getX() <= Math.max(other.start.getX() + EPSILON,
                        other.end.getX() + EPSILON)
                && point.getX() >= Math.min(other.start.getX() - EPSILON,
                        other.end.getX() - EPSILON)
                && point.getY() <= Math.max(start.getY() + EPSILON, end.getY()
                        + EPSILON)
                && point.getY() >= Math.min(start.getY() - EPSILON, end.getY()
                        - EPSILON))
                && point.getY() <= Math.max(other.start.getY() + EPSILON,
                        other.end.getY() + EPSILON)
                && point.getY() >= Math.min(other.start.getY() - EPSILON,
                        other.end.getY() - EPSILON);
    }
    /**
     * check if lines are equal.
     * @param other - other line to check if lines are equal.
     * @return - true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        // check if valid input
        if (other != null) {
            //check if the start and end points are the same (or start of one
            // line is the same as the end of other, and vice versa)
            // check the equasion of a lines are the same
            return ((start.equals(other.start) && end.equals(other.end))
                    || (start.equals(other.end) && end.equals(other.start))
                    && equalsValues(slope(), other.slope())
                    && equalsValues(intercept(), other.intercept()));
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
     * check if point is include in line.
     * @param point - the point to check.
     * @return - true if the point include, false otherwise.
     */
    public boolean pointIncludeInLine(Point point) {
        return (((point.getX() <= end.getX() && point.getX() >= start.getX())
                || (point.getX() >= end.getX() && point.getX() <= start.getX()))
                && ((point.getY() <= end.getY() && point.getY() >= start.getY())
                || (point.getY() >= end.getY()
                && point.getY() <= start.getY())));
    }
    /**
     *  check If this line intersect with the rectangle.
     * @param rect - rectangle to check
     * @return - If this line does not intersect with the rectangle,return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //if there is no intersection points with the rectangle return null
        if (rect.intersectionPoints(this).isEmpty()) {
            return null;
        }
        java.util.List<Point> intersectionPoints =
                rect.intersectionPoints(this);
        //start check in for loop with the first point, and override if required
        Point closest = intersectionPoints.get(0);
        for (int i = 1; i < intersectionPoints.size(); i++) {
            //check bu comparing distances
            if (this.start.distance(closest)
                    > this.start.distance(intersectionPoints.get(i))
                    + EPSILON) {
                closest = intersectionPoints.get(i);
            }
        }
        return closest;
    }
}