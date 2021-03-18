package primitives;

import java.util.Objects;

import static primitives.Point3D.ZERO;

public class Vector {
    Point3D _head;


    public Vector(Point3D head) {
        if (head.equals(ZERO)) {
            throw new IllegalArgumentException("Vector head can not be Point(0,0,0)");
        }
        _head = new Point3D(head._x, head._y, head._z);
    }

    public Vector(double x, double y, double z) {
        this(new Point3D(x, y, z));
    }

    public Vector(Vector dir) {
        _head = new Point3D(dir._head._x, dir._head._y, dir._head._z);
    }

    public Point3D getHead() {
        return _head;
    }

    public Vector crossProduct(Vector v) {
        double u1 = _head._x._coord;
        double u2 = _head._y._coord;
        double u3 = _head._z._coord;

        double v1 = v._head._x._coord;
        double v2 = v._head._y._coord;
        double v3 = v._head._z._coord;

        return new Vector(
                u2 * v3 - u3 * v2,
                u3 * v1 - u1 * v3,
                u1 * v2 - u2 * v1
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return _head.equals(vector._head);
    }

    @Override
    public String toString() {
        return "{" + _head + '}';
    }

    /**
     * This function do scalar product
     * @param v
     * @return the result
     */
    public double dotProduct(Vector v) {
        double x = _head._x._coord * v._head._x._coord;
        double y = _head._y._coord * v._head._y._coord;
        double z = _head._z._coord * v._head._z._coord;

        return x + y + z;
    }

    /**
     * This function do vector connection
     * @param vector Vector
     * @return new Vector (u+v)
     */
    public Vector add(Vector vector) {
        return new Vector(new Point3D(_head._x._coord + vector._head._x._coord,
                _head._y._coord + vector._head._y._coord,
                _head._z._coord + vector._head._z._coord
        ));
    }

    /**
     * This function do vector subtraction
     * @param vector Vector
     * @return new Vector(u-v)
     */
    public Vector subtract(Vector vector) {
        double x = _head._x._coord - vector._head._x._coord;
        double y = _head._y._coord - vector._head._y._coord;
        double z = _head._z._coord - vector._head._z._coord;

        return new Vector(new Point3D(x, y, z));
    }

    /**
     *This function do vector multiplication by a number - scalar
     * @param scalar scaling factor
     * @return new vector
     */
    public Vector scale(double scalar) {
        if(Double.compare(scalar,0d)== 0){
            throw new IllegalArgumentException("scaling factor == 0");
        }
        return new Vector(
                new Point3D(
                        scalar * _head._x._coord,
                        scalar * _head._y._coord,
                        scalar * _head._z._coord));
    }

    /**
     * This function do vector product
     * @param vector
     * @return new vector
     */
    public Vector crossPruduct(Vector vector)
    {
        double u1 = _head._x._coord;
        double u2 = _head._y._coord;
        double u3 = _head._z._coord;
        double v1 = vector._head._x._coord;
        double v2 = vector._head._y._coord;
        double v3 = vector._head._z._coord;

        return new Vector(new Point3D(
                u2 * v3 - u3 * v2,
                u3 * v1 - u1 * v3,
                u1 * v2 - u2 * v1
        ));
    }

    /**
     * This function do calculate the length of the vector squared
     * @return the result
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * This function do calculate the length of the vector
     * @return the result
     */
    public double lengthSquared() {
        double u1 = _head._x._coord;
        double u2 = _head._y._coord;
        double u3 = _head._z._coord;

        return u1 * u1 + u2 * u2 + u3 * u3;
    }

    /**
     * A normalization operation that returns a new normalized vector
     * in the same direction as the original vector
     * @return new Vector normalized
     */
    public Vector normalized() {
        Vector result = new Vector(_head);
        result.normalize();
        return result;
    }

    /**
     * The vector normalization action that will change the vector itself
     * (the only action that changes the object to which it was summoned).
     * The change is made by replacing
     * the head point with a new point with updated coordinates
     * @return The operation will also return the vector itself
     * for the purpose of chaining the operations if necessary
     */
    public Vector normalize() {
        double length = this.length();

        //cannot divide by 0
        if (length == 0)
            throw new ArithmeticException("divide by Zero");

        double x = this._head._x._coord;
        double y = this._head._y._coord;
        double z = this._head._z._coord;

        this._head = new Point3D(x / length, y / length, z / length);

        return this;
    }
}
