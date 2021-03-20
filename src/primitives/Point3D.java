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
    public final static Point3D ZERO  = new Point3D(0d, 0d, 0d);

    /**
     * constructor that make a point from coordinate class
     * @param x
     * @param y
     * @param z
     */
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        _x = new Coordinate(x._coord);
        _y = new Coordinate(y._coord);
        _z = new Coordinate(z._coord);
    }

    /**
     * constructor that make a double point from coordinate
     * @param x
     * @param y
     * @param z
     */
    public Point3D(double x, double y, double z) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);
    }

    /**
     * constructor that get Point3D type and return point
     * @param pt
     */
    public Point3D(Point3D pt) {
        this(pt._x, pt._y, pt._z);
    }

    /**
     * @return _x
     */
    public Coordinate get_x() {
        return _x;
    }

    /**
     * @return _y
     */
    public Coordinate get_y() {
        return _y;
    }

    /**
     * @return _z
     */
    public Coordinate get_z() {
        return _z;
    }

    /**
     * the function check if the two parameters are equal
     * @param o
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
     * @return the point (_x,_y,_z)
     */
    @Override
    public String toString() {
        return "(" + _x + ',' + _y + ',' + _z + ")";
    }

    /**
     * Vector subtraction - gets a second point in the parameter
     * @param pt2
     * @return Returns a vector from the second point to the point on which the operation is performed
     */
    public Vector subtract(Point3D pt2) {
        Point3D head = new Point3D(
                _x._coord - pt2._x._coord,
                _y._coord - pt2._y._coord,
                _z._coord - pt2._z._coord
        );
        if (ZERO.equals(head)) {
            throw new IllegalArgumentException("Vector head cannot be Point(0,0,0)");
        }

        return new Vector(head);
    }

    /**
     * squared distance between 2 3D points
     * @param point3D
     * @return the squared distance
     */
    public double distanceSquared(Point3D point3D) {
        final double x1 = _x._coord;
        final double y1 = _y._coord;
        final double z1 = _z._coord;
        final double x2 = point3D._x._coord;
        final double y2 = point3D._y._coord;
        final double z2 = point3D._z._coord;

        return (((x2 - x1) * (x2 - x1)) * ((y2 - y1) * (y2 - y1)) * ((z2 - z1) * (z2 - z1)));
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
     * @param vector
     * @return Returns a new point
     */
    public Point3D add(Vector vector){
        return new Point3D(_x._coord+vector._head._x._coord,
                            _y._coord+vector._head._y._coord,
                            _z._coord+vector._head._z._coord
                );
    }
}
