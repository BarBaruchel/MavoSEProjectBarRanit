package primitives;

import java.util.Objects;

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
     * @param dir
     */
    public Ray(Point3D p0, Vector dir) {
        _p0 = p0;
        _dir = dir.normalized();;
    }

    /**
     * @return point
     */
    public Point3D get_p0() {
        return _p0;
    }

    /**
     * @return vector
     */
    public Vector get_dir() {
        return new Vector(_dir._head);
    }

    /**
     * check if the two parameter are equal
     * @param
     * @return if equal true else false
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
        return "Point3D:"+_p0+"\n"+"Vector:"+_dir;
    }
}
