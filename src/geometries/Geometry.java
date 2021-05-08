package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * the name of the interface is Geometry and he has
 * function getNormal that receive one Point3D parameter
 * and return normalized vector
 */
public interface Geometry extends Intersectable {
    /**
     * The function receive a point and return a normal in this point to the body
     * @param point point pointing in direction of the normal
     * @return normal vector to the Geometry
     */
    Vector getNormal(Point3D point);
}
