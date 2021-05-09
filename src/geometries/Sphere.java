package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Sphere class that inheritor from Geometry and get two variable that can not change
 */
public class Sphere extends RadialGeometry implements Geometry {
    final Point3D _center;

    /**
     * The constructor get Point3D variable and radius and initialized
     * @param center
     * @param radius
     */
    public Sphere(Point3D center, double radius) {
        super(radius);
        _center = center;
    }

    /**
     * getter center field
     * @return reference to the center of the Sphere
     */
    public Point3D getCenter() {
        return _center;
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

    /**
     * The function find the intersections the ray and the plane
     * @param ray -that need to find where is hit
     * @return list of intersections point
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {

        Point3D P0 = ray.get_p0();
        Vector v = ray.get_dir();

        if (P0.equals(_center)) {
           //throw new IllegalArgumentException("origin ray can`t be Sphere center");
            return List.of(_center.add(v.scale(_radius)));
        }

        Vector U = _center.subtract(P0);

        double tm = alignZero(v.dotProduct(U));
        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));

        // no intersections : the ray direction is above the sphere
        if (d >= _radius) {
            return null;
        }

        double th = alignZero(Math.sqrt(_radius * _radius - d * d));
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        if (t1 > 0 && t2 > 0) {
//            Point3D P1 = P0.add(v.scale(t1));
//            Point3D P2 = P0.add(v.scale(t2));
            Point3D P1 =ray.getTargetPoint(t1);
            Point3D P2 =ray.getTargetPoint(t2);
            return List.of(P1, P2);
        }
        if (t1 > 0) {
//            Point3D P1 = P0.add(v.scale(t1));
            Point3D P1 =ray.getTargetPoint(t1);
            return List.of(P1);
        }
        if (t2 > 0) {
//            Point3D P2 = P0.add(v.scale(t2));
            Point3D P2 =ray.getTargetPoint(t2);
            return List.of(P2);
        }
        return null;
    }
}
