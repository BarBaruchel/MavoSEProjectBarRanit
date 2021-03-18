package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * the name of the interface is Geometry and he has
 * function getNormal that receive one Point3D parameter
 * and return normalized vector
 */
public interface Geometry {
    Vector getNormal(Point3D point);
}
