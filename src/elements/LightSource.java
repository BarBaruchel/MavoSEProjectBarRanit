package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface that represents a source of the light
 */
public interface LightSource {
    /**
     * Get the color in a given point
     *
     * @param p the point to check for the intensity
     * @return the color
     */
    public Color getIntensity(Point3D p);

    /**
     * Get vector L, given a point
     *
     * @param p the given point
     * @return the vector
     */
    public Vector getL(Point3D p);

    /**
     * The function get distance between a point and the light source
     * @param point the lighted point
     * @return the distance
     */
    public double getDistance(Point3D point);
}
