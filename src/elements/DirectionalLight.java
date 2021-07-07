package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Models a light source with its direction
 */
public class DirectionalLight extends Light implements LightSource  {
    /**
     * direction of the light
     */
    private final Vector _direction;
    /**
     * Instantiates a new Directional light passing the color as argument
     * @param intensity -intensity of ambient light, Color type
     * @param direction - of the light
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        _direction = direction.normalized();
    }

    /**
     * Get intensity of the color in a given point
     * @param p the point to check for the intensity
     * @return the color intensity
     */
    @Override
    public Color getIntensity(Point3D p) {
        return _intensity;
    }

    /**
     * Get vector L, given a point
     *
     * @param p the given point
     * @return the vector
     */
    @Override
    public Vector getL(Point3D p) {
        return _direction.normalized();
    }

    @Override
    public double getDistance(Point3D point) {
        return Double.POSITIVE_INFINITY;
    }
}
