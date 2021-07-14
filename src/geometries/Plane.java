package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * class plane that heir from geometry and had two variables one from point3D type and one from vector type
 */
public class Plane extends Geometry {

    /**
     * Point 3D on thr plane
     */
    final Point3D _q0;
    /**
     *  normal to the point _q0
     */
    final Vector _normal;

    /**
     * constructor that get Point3D`s variable and normal from vector type
     *  and normalized the normal from vector type
      * @param p0
     * @param normal
     */
    public Plane(Point3D p0, Vector normal) {
        _q0 = p0;
        _normal = normal.normalized();
    }

    /**
     *Constructor of Plane from 3 points on its surface
     * the points are ordered from right to left
     * forming an arc in right direction
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        _q0 = p1;

        Vector U = p2.subtract(p1);   // find one vector from p2-p1
        Vector V = p3.subtract(p1);   //  find second vector from p3-p1

        Vector N = U.crossProduct(V);  // do crossProduct between UXV

        N.normalize();  // do normalization on N

        //right hand rule
        _normal = N;
    }

    /**
     * @return the variables of a plane and print
     */
    @Override
    public String toString() {
        return "Plane{" +
                "_q0=" + _q0 +
                ", _normal=" + _normal +
                '}';
    }

    /**
     * getter q0 field
     * @return reference to the _q0 point of the Point3D
     */
    public Point3D get_q0() {
        return _q0;
    }

    /**
     * getter normal field
     * @return the _normal point of the Vector
     */
    public Vector get_normal() {
        return _normal;
    }

    /**
     * Receiving one point type parameter [across the geometric body]
     * @param point
     * @return And returns the normal vector (vertical) to the body at this point.
     */
    @Override
    public Vector getNormal(Point3D point) {
        return _normal;
    }

    /**
     * The function find the intersections between the ray and the plane
     * @param ray -that need to find where is hit
     * @return list of intersections point
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        Point3D P0 = ray.getP0();   // the point that outside the plane
        Vector v = ray.getDir();   //the vector that start on p0 to P that on the plane

        // if _q0 equals to p0 return immutable list 0f q0
        if (_q0.equals(P0)) {
            //return List.of(new GeoPoint(this, _q0));
            return null;
        }

        double nv = _normal.dotProduct(v);

        /**
         * if the dot Product between n and v is zero that mean they vertical to each other
         * and the ray is lying in the plane axis
         * therefore return null
         */
        if (isZero(nv)) {
            return null;
        }

        /**
         * t is the distance from the point
         */
        double t = _normal.dotProduct(_q0.subtract(P0)) / nv;
        if ( t>0 && alignZero(t - maxDistance) <= 0) {
       // if ( t>0 ) {
            Point3D p = ray.getPoint(t);
            //return list of p because, there are elements that have more then one intersection
            return List.of(new GeoPoint(this, p));
        }
        return null;
    }
}
