package renderer;

import elements.LightSource;
import geometries.Intersectable;
import static geometries.Intersectable.GeoPoint;
import static primitives.Util.alignZero;

import primitives.*;
import scene.Scene;

import java.util.List;

/**
 * RayTracerBasic heritage from renderer.RayTracerBase
 */
public class RayTracerBasic extends RayTracerBase {

    /**
     * c-tor that get Scene object and And operates the father class constructor
     * @param scene - Scene type variable
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        /**
         * searching intersection between the ray and 3D model of the scene
         */
        List<GeoPoint> intersections = _scene.geometries.findGeoIntersections(ray);
        if (intersections != null) {
            GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
            return calcColor(closestPoint, ray);
        }
        /**
         * there ara no intersection points,
         * thereforeThe background color of the scene will be return
         */
        return _scene.background;
    }

    /**
     *The function receives point parameter and returns a color
     * for now the method will return the fill / environmental lighting color of the scene and nothing more
     * (no use of the point we received in the parameter so far)
     * @param geoPoint  - the intersection
     * @param ray
     * @return color
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        Color intensity= geoPoint.geometry.getEmission();
        intensity = intensity.add(_scene.ambientlight.getIntensity());
        return intensity.add(calcLocalEffects(geoPoint, ray));
    }

    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray) {
        Vector v = ray.get_dir ();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) {
            return Color.BLACK;
        }
        Material material = geoPoint.geometry.getMaterial();
        int nShininess = material.nShininess;
        Color color = Color.BLACK;
        for (LightSource lightSource : _scene.lights) {
            Vector l = lightSource.getL(geoPoint.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(geoPoint.point);
                color = color.add(calcDiffusive(material.Kd, l, n, lightIntensity),
                        calcSpecular(material.Ks, l, n, v, nShininess, lightIntensity));
            }
        }
        return color;
    }

    /**
     * The function get 6 parameters and calculate the specular color ,
     *  according the formula in the Presentation
     * @param ks  - coefficient
     * @param l  - Vector
     * @param n  - Vector
     * @param v  - Vector
     * @param nShininess
     * @param lightIntensity
     * @return  the suitable color
     */
    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r= l.subtract(n.scale(l.dotProduct(n)*2));
        double vrMinus= v.scale(-1).dotProduct(r);
        /**
         * vrn it`s vrMinus ^nShininess
         */
        double vrn= Math.pow(vrMinus,nShininess);
        return lightIntensity.scale(ks*vrn);
    }

    /**
     *  The function get 4 parameters and calculate the diffusive light ,
     *  according the formula in the Presentation
     * @param kd - coefficient
     * @param l  - Vector
     * @param n  - Vector
     * @param lightIntensity
     * @return the suitable color
     */
    private Color calcDiffusive (double kd, Vector l, Vector n, Color lightIntensity){
        /**
         * calculate the The absolute value of  multiplying two vectors: n , l
         */
        double ln= Math.abs(n.dotProduct(l));
        return  lightIntensity.scale(kd*ln);
    }
}