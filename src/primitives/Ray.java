package primitives;

import java.util.Objects;

import static primitives.Util.isZero;

/**
 * class Ray that get point in 3D and vector that cannot changed
 */
public class Ray {
    /**
     * @params _p0 pOrigin
     * @params _dir direction
     */
    final Point3D _p0;
    final Vector _dir;

    /**
     * constructor that get point3D`s variable and vector`s variable and initialization them
     * and normalized the vector`s variable
     * @param p0
     * @param dir direction
     */
    public Ray(Point3D p0, Vector dir) {
        _p0 = p0;
        _dir = dir.normalized();
        ;
    }

    /**
     * getter p0 field
     * @return reference to the p0 point of the Ray
     */
    public Point3D get_p0() {
        return _p0;
    }

    /**
     * getter dir field
     * @return copy of the dir vector of the Ray
     */
    public Vector get_dir() {
        return new Vector(_dir._head);
    }

    /**
     * the function check if the two parameters are equal
     * @param o Object (basically another Ray) to compare
     * @return true if equal, else return false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return _p0.equals(ray._p0) && _dir.equals(ray._dir);
    }

    /**
     * @return the point and the vector and print
     */
    @Override
    public String toString() {
        return "Point3D:" + _p0 + "\n" + "Vector:" + _dir;
    }

    /**
     * @param t - A certain length
     * @return returns the point that the ray hit
     */
    public Point3D getTargetPoint(double t) {
        if (isZero(t)) {
            return _p0;
        }
        return _p0.add(_dir.scale(t));
    }
}
