package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 *The class inheritor from Geometry and get two variable that can not change
 */
public class Tube implements Geometry {

    final Ray _axisRay;
    final double _radius;

    /**
     * Constructor that get the two variables and initializes them
     * @param axisRay
     * @param radius
     */
    public Tube(Ray axisRay, double radius) {
        _axisRay = axisRay;
        _radius = radius;
    }

    /**
     * getter ray field
     * @return reference to the _axisRay of the Tube
     */
    public Ray get_axisRay() {
        return _axisRay;
    }

    /**
     * getter radius field
     * @return reference to the _radius of the Tube
     */
    public double get_radius() {
        return _radius;
    }

    /**
     * Function that get point
     * @param point
     * @return null
     */
    @Override
    public Vector getNormal(Point3D point) {
        Point3D PO=_axisRay.get_p0();
        Vector v=_axisRay.get_dir();
        Vector PO_P= point.subtract(PO);

        double t= alignZero(v.dotProduct(PO_P));

        // EXPLAIN HERE WHAT HAPPENED
        if(isZero(t)){
            return PO_P.normalize();
        }

        Point3D O= PO.add(v.scale(t));

        if (O.equals(point)){
            throw new IllegalArgumentException(("point p cannot be on the tube`s axis"));
        }

        Vector n= point.subtract(O);
        return n.normalized();
        //return null;
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