package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Represents a omni-directional point source
 */
public class PointLight extends Light implements LightSource{
    /**
     *  position of the Point Light
     */
    private final Point3D _position;

    /**
     * attenuation coefficient of the Point Light
     * the formula:  iL= i0/( kC + kL*d + kQ*d^2 )
     */

    /**
     * kC have to initialize to 1 because , it's in the attenuation formula in the denominator (mechane),
     * the constant.
     */
    private double _Kc=1d;

    /**
     *Linear coefficient
     */
    private double _Kl =0d;

    /**
     * Square coefficient
     */
    private double _Kq =0d;

    /**
     * Instantiates a new Directional light passing the color as argument
     * @param intensity -intensity of ambient light, Color type
     * @param position
     */
    public PointLight(Color intensity, Point3D position) {
        super(intensity);
        _position = position;
    }

    /**
     * setter method for _Kc field
     * @param kc  - the constant
     * @return the PointLight object for chaining method
     */
    public PointLight setKc(double kc) {
        _Kc = kc;
        return this;
    }

    /**
     * setter method for _Kl field
     * @param kl  - Linear coefficient
     * @return the PointLight object for chaining method
     */
    public PointLight setKl(double kl) {
        _Kl = kl;
        return this;
    }

    /**
     * setter method for _Kq field
     * @param kq  - Square coefficient
     * @return the PointLight object for chaining method
     */
    public PointLight setKq(double kq) {
        _Kq = kq;
        return this;
    }

    /**
     * Get intensity of the color in a given point
     * @param p the point to check for the intensity
     * @return Color at the point p
     */
    @Override
    public Color getIntensity(Point3D p) {
        double d = p.distance(_position);
        double attenuation= 1d/(_Kc + _Kl * d + _Kq * d * d);  // this is the denominator
        return _intensity.scale(attenuation);           // I0*(1/kc+k*d+kq*d^2)
    }


    /**
     * Get vector L, given a point
     * @param p the given point
     * @return the vector
     */
    @Override
    public Vector getL(Point3D p) {
        try {
            return p.subtract(_position).normalize();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * The function get distance between a point and the light source
     * @param point the lighted point
     * @return the distance
     */
    @Override
    public double getDistance(Point3D point) {
        return _position.distance(point);
    }

}
