package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import static primitives.Util.alignZero;

/**
 * Models point light source with direction
 */
public class SpotLight extends PointLight{
    /**
     *  direction of the Point Light
     */
    private final Vector _direction;
    /**
     * Instantiates a new Directional light passing the color as argument
     *  @param intensity -intensity of ambient light, Color type
     * @param position
     * @param direction
     */
    public SpotLight(Color intensity, Point3D position, Vector direction) {
        super(intensity, position);
        _direction = direction.normalized();
    }

    /**
     * Get intensity of the color in a given point
     * the formula for the intensity:
     * iL= i0 * max (0, dir l) /( kC + kL*d + kQ*d^2 )
     * @param p  -the point to check for the intensity
     * @return the color intensity
     */
    @Override
    public Color getIntensity(Point3D p) {
        Color baseIntensity= super.getIntensity(p); // baseIntensity =(I0/kc+k*d+kq*d^2)
        Vector l=getL(p);
        if (l == null)
            l = _direction;
        double cos = alignZero(l.dotProduct(_direction));
        if (cos <= 0) return Color.BLACK;
        return baseIntensity.scale(cos); //return baseIntensity*(max(0,dir*l))
    }

}
