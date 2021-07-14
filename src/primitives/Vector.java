package primitives;

import java.util.Objects;

import static primitives.Point3D.ZERO;

/**
 * class vector that get variable from Point3D type
 */
public class Vector {
      Point3D _head;

    /**
     * constructor that get point and check if it ZERO
     * if yes throw exception "vector head can not be point (0,0,0)"
     * else get the point
     * @param head
     */
    public Vector(Point3D head) {
        if (head.equals(ZERO)) {
            throw new IllegalArgumentException("Vector head can not be Point(0,0,0)");
        }
        _head = head;
    }

    /**
     * constructor that get 3 points and create Point3D`s variable
     * with the 3 points
     * @param x
     * @param y
     * @param z
     */
    public Vector(double x, double y, double z) {
        this(new Point3D(x, y, z));
    }

    /**
     * constructor that get vector
     * constructor that get vector and create Point3D`s variable
     *  with the 3 points in the vector
     * @param dir
     */
    public Vector(Vector dir) {
        _head = new Point3D(dir._head._x._coord, dir._head._y._coord, dir._head._z._coord);
    }

    /**
     * getter head field
     * @return reference to the _head point of the Vector
     */
    public Point3D getHead() {
        return _head;
    }

    /**
     * the function get two vectors
     * @param v the second vector
     * @return the result of two vectors multiplied by a vector
     */
    public Vector crossProduct(Vector v) {
        double u1 = _head._x._coord;            // get the x in the first vector
        double u2 = _head._y._coord;            // get the y in the first vector
        double u3 = _head._z._coord;            // get the z in the first vector

        double v1 = v._head._x._coord;          // get the x in the second vector
        double v2 = v._head._y._coord;          // get the y in the second vector
        double v3 = v._head._z._coord;          // get the z in the second vector

        /**
         *  create a new Vector and do the subtraction between the two points
         *  that accordingly multiplication and return that
         */
        return new Vector(
                u2 * v3 - u3 * v2,
                u3 * v1 - u1 * v3,
                u1 * v2 - u2 * v1
        );
    }

    /**
     * the function check if the two parameters are equal
     * @param o Object (basically another Vector) to compare
     * @return true if equal, else return false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return _head.equals(vector._head);
    }

    /**
     * @return the vector and print
     */
    @Override
    public String toString() {
        return "{" + _head + '}';
    }

    /**
     * dot product between two vectors (scalar product)
     * @param v the right vector of U.V
     * @return scalre value of dot product
     */
    public double dotProduct(Vector v) {
        double x = _head._x._coord * v._head._x._coord;     //multiply the the point x int the first Vector with the x point in the second Vector
        double y = _head._y._coord * v._head._y._coord;    //multiply the the point y int the first Vector with the y point in the second Vector
        double z = _head._z._coord * v._head._z._coord;    //multiply the the point z int the first Vector with the z point in the second Vector

        /**
         * return the 3 points additional after the multiplication
         */
        return x + y + z;
    }

    /**
     * This function do vector additional
     * @param vector the second vector
     * @return new Vector (u+v)
     */
    public Vector add(Vector vector) {
        /**
         *  create a new Vector and do the additional between the two points
         *  accordingly and return that
         */
        return new Vector(new Point3D(_head._x._coord + vector._head._x._coord,
                _head._y._coord + vector._head._y._coord,
                _head._z._coord + vector._head._z._coord
        ));
    }

    /**
     * This function do vector subtraction
     * @param other the second vector
     * @return new Vector(u-v)
     */
    public Vector subtract(Vector other) {
        return _head.subtract(other._head);

    }

    /**
     * This function do vector multiplication by a number ( scalar)
     * @param scalar scaling factor
     * @return new vector
     */
    public Vector scale(double scalar) {
        /**
         * if the scalar number is zero throw IllegalArgumentException "scaling factor == 0"
         * else create a new vector and do multiplication between the points and the scalar
         * and return the that
         */
        if (Double.compare(scalar, 0d) == 0) {
            throw new IllegalArgumentException("scaling factor == 0");
        }
        /**
         *  create a new Vector and do the subtraction between the scalar and the points
         *  that accordingly multiplication and return that
         */
        return new Vector(
                        scalar * _head._x._coord,
                        scalar * _head._y._coord,
                        scalar * _head._z._coord);
    }

    /**
     * This function do vector cross product (vectorial product)
     * @param vector the second vector
     * @return new vector
     */
    public Vector crossPruduct(Vector vector) {
        double u1 = _head._x._coord;
        double u2 = _head._y._coord;
        double u3 = _head._z._coord;
        double v1 = vector._head._x._coord;
        double v2 = vector._head._y._coord;
        double v3 = vector._head._z._coord;

        /**
         *  create a new Vector and do the subtraction between the two points
         *  that accordingly multiplication and return that
         */
        return new Vector(
                u2 * v3 - u3 * v2,
                u3 * v1 - u1 * v3,
                u1 * v2 - u2 * v1
        );
    }

    /**
     * This function calculate the length of the vector squared
     * using by lengthSquared function
     * @return the result
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * This function calculate the length of the vector
     * @return the result
     */
    public double lengthSquared() {
        double u1 = _head._x._coord;
        double u2 = _head._y._coord;
        double u3 = _head._z._coord;

        /**
         * return all the 3 points by multiplied themselves and do additional
         * x^2 + y^2 + z^2
         */
        return u1 * u1 + u2 * u2 + u3 * u3;
    }

    /**
     * The vector normalization action that will change the vector itself
     * (the only action that changes the object to which it was summoned).
     * The change is made by replacing
     * the head point with a new point with updated coordinates
     *
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

        /**
         * create a new Point3D, and dived the each point by the length of the vector
         */
        Point3D newPoint = new Point3D(x / length, y / length, z / length);

        /**
         * if newPoint is (0,0,0) throw IllegalArgumentException that
         * head vector cannot be point (0,0,0)
         * else enter to the head the normalize vector and return reference
         */
        if (ZERO.equals(newPoint)) {
            throw new IllegalArgumentException("head vector cannot be point (0,0,0)");
        }

        _head = newPoint;
        return this;
    }

    /**
     * A normalization operation that returns a new normalized vector
     * in the same direction as the original vector
     *
     * @return new Vector normalized
     */
    public Vector normalized() {
        /**
         * create a new vector and to that the vector that we want to do normalized
         * call to the normalize function on the new created vector
         * and return that
         */
        Vector result = new Vector(_head);
        result.normalize();
        return result;
    }


}
