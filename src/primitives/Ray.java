package primitives;

import java.util.Objects;

public class Ray {
    /**
     * @params _p0 pOrigin
     * @params _dir direction
     */
    final Point3D _p0;
    final Vector _dir;

    public Ray(Point3D p0, Vector dir) {
        _p0 = p0;
        _dir = dir.normalized();;
    }

    public Point3D get_p0() {
        return _p0;
    }

    public Vector get_dir() {
        return new Vector(_dir._head);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return _p0.equals(ray._p0) && _dir.equals(ray._dir);
    }

    @Override
    public String toString() {
        return "Point3D:"+_p0+"\n"+"Vector:"+_dir;
    }
}
