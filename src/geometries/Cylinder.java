package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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
        _height = height;
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
        return null;
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