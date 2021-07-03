package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * the name of the interface is Geometry and he has
 * function getNormal that receive one Point3D parameter
 * and return normalized vector
 */
public abstract class Geometry implements Intersectable {

    protected Color _emission = Color.BLACK;
    private Material _material= new Material();

    /**
     * getter _material field
     * @return reference to the _normal point of the Vector
     */
    public Material getMaterial() {
        return _material;
    }

    /**
     * setter _material field
     * @param material source Color object
     * @return the Geometry object itself for chaining calls
     */
    public Geometry setMaterial(Material material) {
        this._material = material;
        return  this;
    }

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
        this._emission = emission;
        return  this;
    }

    /**
     * The function receive a point and return a normal in this point to the body
     * @param point point pointing in direction of the normal
     * @return normal vector to the Geometry
     */
   public abstract Vector getNormal(Point3D point);

}
