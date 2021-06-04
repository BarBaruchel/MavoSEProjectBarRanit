package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * the name of the interface is Geometry and he has
 * function getNormal that receive one Point3D parameter
 * and return normalized vector
 */
public abstract class Geometry implements Intersectable {

    protected Color _emission = Color.BLACK;

    /**
     * getter emission field
     * @return reference to the _normal point of the Vector
     */
    public Color getEmission() {
        return _emission;
    }

    /**
     * setter emission field
     * @param emission source Color object
     * @return the Geometry object itself for chaining calls
     */
    public Geometry setEmission(Color emission) {
        _emission = emission;
        /**
         * chaining method
         */
        return  this;
    }

    /**
     * The function receive a point and return a normal in this point to the body
     * @param point point pointing in direction of the normal
     * @return normal vector to the Geometry
     */
    abstract Vector getNormal(Point3D point);
}
