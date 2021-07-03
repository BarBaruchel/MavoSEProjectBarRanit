
package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

import static primitives.Util.alignZero;

/**
 * Class Cylinder that inheritor from Tube and get height
 */
public class Cylinder extends Tube {   // implements Geometry
    final double _height;

    /**
     * Constructor that get Ray variable type , radius and height
     * and call to super whits axisRay and radius
     * @param axisRay
     * @param radius
     * @param height
     */
    public Cylinder(Ray axisRay, double radius,double height) {
        super(axisRay,radius);
        this._height = height;
    }

    /**
     * getter height field
     * @return reference to the _height
     */
    public double get_height() {
        return _height;
    }

    /**
     * Function that get point
     * @param point
     * @return null
     */
    @Override
    public Vector getNormal(Point3D point) {
        Point3D o = _axisRay.getP0();
        Vector v = _axisRay.getDir();

        // projection of P-O on the ray:
        double t;
        try {
            t = alignZero(point.subtract(o).dotProduct(v));
        } catch (IllegalArgumentException e) { // P = O
            return v;
        }

        // if the point is at a base
        if (t == 0 || isZero(_height - t)) // if it's close to 0, we'll get ZERO vector exception
            return v;

        o = o.add(v.scale(t));
        return point.subtract(o).normalize();
    }


    /**
     * @return the variables of the Cylinder and print
     */
    @Override
    public String toString() {
        return "Cylinder{" +
                "_height=" + _height +
                ", _axisRay=" + _axisRay +
                ", _radius=" + _radius +
                '}';
    }
}