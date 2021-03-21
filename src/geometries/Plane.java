package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * class plane that heir from geometry and had two variables one from point3D type and one from vector type
 */
public class Plane implements Geometry {

    final Point3D _q0;
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
     * @return reference to the _normal point of the Vector
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

}
