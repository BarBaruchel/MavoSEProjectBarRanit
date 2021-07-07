

package renderer;

import elements.LightSource;

import static geometries.Intersectable.GeoPoint;
import static primitives.Util.alignZero;

import primitives.*;
import scene.Scene;

import java.util.List;

/**
 * RayTracerBasic heritage from renderer.RayTracerBase
 */
public class BasicRayTracer extends RayTracerBase {

    private static final double DELTA = 0.1;
    private static final double INITIAL_K = 1.0;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * c-tor that get Scene object and And operates the father class constructor
     * @param scene - Scene type variable
     */
    public BasicRayTracer(Scene scene) {
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
         * there are no intersection points,
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
    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
        Color color = intersection.geometry.getEmission();
        color = color.add(calcLocalEffects(intersection, ray));
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
    }

    private Color calcGlobalEffects(GeoPoint geoPoint, Ray ray, int level, double k) {
        Color color = Color.BLACK;
        Material material = geoPoint.geometry.getMaterial();
        double kkr = k * material.Kr;
        if (kkr > MIN_CALC_COLOR_K) {
            Ray reflectedRay = constructReflectedRay( geoPoint, ray);
            GeoPoint reflectedPoint= reflectedRay.findClosestGeoPoint(_scene.geometries.findGeoIntersections(ray));
            color = color.add(calcColor(reflectedPoint, reflectedRay, level -1, kkr).scale(material.Kr));
        }
        double kkt = k * material.Kt;
        if (kkt > MIN_CALC_COLOR_K) {
            Ray refractedRay  = constructRefractedRay( geoPoint, ray);
            GeoPoint refractedPoint = refractedRay.findClosestGeoPoint(_scene.geometries.findGeoIntersections(ray));
            color = color.add(calcColor(refractedPoint, refractedRay, level -1 , kkt).scale(material.Kt));
        }
        return color;
    }

    private Ray constructRefractedRay(GeoPoint geoPoint, Ray ray) {
        return new Ray(geoPoint.point, ray.getDir());
    }

    private Ray constructReflectedRay(GeoPoint geoPoint, Ray ray) {
        Vector n= geoPoint.geometry.getNormal(geoPoint.point);
        Vector v= ray.getDir();
        Vector r=v.subtract(n.scale(2*v.dotProduct(n)));
        return new Ray(geoPoint.point, r);
    }


    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray) {
        Vector v = ray.getDir();
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
                if (unshaded2(lightSource, l, n, geoPoint)) {
                    Color lightIntensity = lightSource.getIntensity(geoPoint.point);
                    color = color.add(calcDiffusive(material.Kd, l, n, lightIntensity),
                            calcSpecular(material.Ks, l, n, v, nShininess, lightIntensity));
                }
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

    private boolean unshaded(LightSource light,Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay= new Ray (geopoint.point, lightDirection, n, DELTA);
        List<GeoPoint> intersections = _scene.geometries.findGeoIntersections(lightRay);
       if (intersections == null){
           return true;
       }
        double lightDistance = light.getDistance(geopoint.point);
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0)
                return false;
        }
        return true;
    }

    private boolean unshaded2(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
        Point3D point = geopoint.point.add(delta);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = _scene.geometries
                .findGeoIntersections(lightRay, light.getDistance(geopoint.point));
        return intersections == null;
    }

}
