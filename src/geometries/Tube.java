package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 *The class inheritor from Geometry  and get two variable that can not change
 */
public class Tube implements Geometry {

    final Ray _axisRay;
    final double _radius;

    /**
     * Constructor that get the two variable
     * @param axisRay
     * @param radius
     */
    public Tube(Ray axisRay, double radius) {
        _axisRay = axisRay;
        _radius = radius;
    }

    /**
     * @return _axisRay
     */
    public Ray get_axisRay() {
        return _axisRay;
    }

    /**
     * @return _radius
     */
    public double get_radius() {
        return _radius;
    }

    /**
     * @param point
     * @return null
     */
    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }

   /**
     * print the variables of the Tube
     */
    @Override
    public String toString() {
        return "Tube{" +
                "_axisRay=" + _axisRay +
                ", _radius=" + _radius +
                '}';
    }
}