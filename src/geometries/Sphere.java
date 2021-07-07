
package geometries;


import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Sphere class that inheritor from Geometry and get two variable that can not change
 */
public class Sphere extends RadialGeometry{
    final Point3D _center;

    /**
     * The constructor get Point3D variable and radius and initialized
     * @param radius
     * @param center
     */
    public Sphere(double radius, Point3D center) {
        super(radius);
        _center = center;
    }

    /**
     * getter center field
     * @return the center of the Sphere
     */
    public Point3D getCenter() {
        return _center;
    }

    /**
     * Function that get point
     * @param p that is the point from type Point3D
     * @return the normalize vector
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
     * The function find the intersections between the ray and the plane
     * @param ray -that need to find where is hit
     * @return list of intersections point
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray , double maxDistance) {
        Point3D P0 = ray.getP0();
        Vector v = ray.getDir();

        if (P0.equals(_center)) {
            return List.of(new GeoPoint(this,_center.add(v.scale(_radius))));
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
        boolean validT1=alignZero(t1 - maxDistance ) <=0;
        boolean validT2=alignZero( t2 - maxDistance )<=0;
        if (t1>0 && t2>0 && validT1 && validT2) {
            Point3D P1 =ray.getPoint(t1);
            Point3D P2 =ray.getPoint(t2);
            return List.of(new GeoPoint(this,P1),new GeoPoint(this, P2));
        }
        if (t1>0 && validT1){
            Point3D P1 =ray.getPoint(t1);
            return List.of(new GeoPoint(this,P1));
        }
        if (t2>0 && validT2) {
            Point3D P2 =ray.getPoint(t2);
            return List.of(new GeoPoint(this,P2));
        }
        return null;
    }
}
