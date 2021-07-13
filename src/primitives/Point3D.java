package primitives;

/**
 * class point3D that get 3 point from Coordinate that cannot change
 */
public class Point3D {
    final Coordinate _x;
    final Coordinate _y;
    final Coordinate _z;

    /**
     * point that const and the point is (0,0,0)
     */
    public final static Point3D ZERO = new Point3D(0d, 0d, 0d);

    /**
     * primary constructor that make a double point from coordinate
     *
     * @param x double value for X axis
     * @param y double value for Y axis
     * @param z double value for Z axis
     */
    public Point3D(double x, double y, double z) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);
    }

    /**
     * the function check if the two parameters are equal
     *
     * @param o Object (basically another Point3d) to compare
     * @return true if equal, else return false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return _x.equals(point3D._x) && _y.equals(point3D._y) && _z.equals(point3D._z);
    }

    /**
     * @return the point (x,y,z) and print
     */
    @Override
    public String toString() {
        return "(" + _x + ',' + _y + ',' + _z + ")";
    }


    /**
     * Vector subtraction - gets a second point in the parameter
     *
     * @param other the origin point in 3D
     * @return Returns a vector from the origin point to the current point
     */
    public Vector subtract(Point3D other) {
        return new Vector(_x._coord - other._x._coord,
                _y._coord - other._y._coord,
                _z._coord - other._z._coord);
    }


  /**
     * calulte the squared distance between 2 3D points
     *
     * @param point3D the second point of 3D
     * @return the squared distance
     */
    public double distanceSquared(Point3D point3D) {
         double x1 = _x._coord;             // get the x in the first point
         double y1 = _y._coord;             //get the y in the first point
         double z1 = _z._coord;             //get the z in the first point

         double x2 = point3D._x._coord;    //get the x in the second point
         double y2 = point3D._y._coord;    //get the y in the second point
         double z2 = point3D._z._coord;    //get the z in the second point

        /**
         *  return the squared distance
         */
        return (((x2 - x1) * (x2 - x1)) +((y2 - y1) * (y2 - y1)) + ((z2 - z1) * (z2 - z1)));
    }

    /**
     * @param point3D
     * @return euclidean distance between 2  3D points
     */
    public double distance(Point3D point3D) {
        return Math.sqrt(distanceSquared(point3D));
    }

    /**
     * Adding a vector to a point
     *
     * @param vector the second vector
     * @return Returns a new point
     */
    public Point3D add(Vector vector) {
        /**
         * create a new Point 3D and do the addition between the two points
         * and return that
         */
        return new Point3D(_x._coord + vector._head._x._coord,
                _y._coord + vector._head._y._coord,
                _z._coord + vector._head._z._coord
        );
    }
}
