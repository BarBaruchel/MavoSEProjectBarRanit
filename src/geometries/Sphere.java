package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Sphere class that inheritor from Geometry and get two variable that can not change
 */
public class Sphere implements Geometry {
    final Point3D _center;
    final double _radius;

    /**
     * The constructor get Point3D variable and radius and initialized
     * @param center
     * @param radius
     */
    public Sphere(Point3D center, double radius) {
        _center = center;
        _radius = radius;
    }

    /**
     * getter center field
     * @return reference to the center of the Sphere
     */
    public Point3D getCenter() {
        return _center;
    }

    /**
     * getter radius field
     * @return reference to the radius of the Sphere
     */
    public double getRadius() {
        return _radius;
    }

    /**
     * Function that get point
     * @param p that is the point from type Point3D
     * @return null
     */
    @Override
    public Vector getNormal(Point3D p) {
        Vector O_P= p.subtract(_center);
        return O_P.normalize();
      // return  null;
    }

    /**
     * @return the variables of Sphere and print
     */
    @Override
    public String toString() {
        return "Sphere{" +
                "_center=" + _center +
                ", _radius=" + _radius +
                '}';
    }
}
