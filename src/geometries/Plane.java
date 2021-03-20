package geometries;

import primitives.Point3D;
import primitives.Vector;
// class plane that heir from geometry and had two variables one from point3D type and one from vector type*/

public class Plane implements Geometry {

    final Point3D _q0;
    final Vector _normal;
//constructor that get variable from Point3D type and other from vector type
 //and return the point and the normalized normal from vector type
    public Plane(Point3D p0, Vector normal) {
        _q0 = p0;
        _normal = normal.normalized();
    }
//constructor that calculate the normal according to what is learned on a triangle normal straight as null from the normal field
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        _q0 = p1;

        Vector U = p2.subtract(p1);
        Vector V = p3.subtract(p1);

        Vector N = U.crossProduct(V);

        N.normalize();

        //right hand rule
        _normal = N;
    }

// function that return the variables of a plane */
    @Override
    public String toString() {
        return "Plane{" +
                "_q0=" + _q0 +
                ", _normal=" + _normal +
                '}';
    }
//return the point
    public Point3D get_q0() {
        return _q0;
    }
//return the normal
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
        return null;
        //return _normal;
    }

}
