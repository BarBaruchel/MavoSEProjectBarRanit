
package renderer;

import elements.LightSource;
import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;
import static primitives.Util.*;

/**
 * represent Ray Tracer Basic which extends RayTracerBase
 *
 */

public class BasicRayTracer extends RayTracerBase {
    /**
     * MAX_CALC_COLOR_LEVEL - Max recursion for transparency and reflection
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;

    /**
     * MIN_CALC_COLOR_K - Minimum color intensity for transparency and reflection
     */
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * INITIAL_K
     */
    private static final double INITIAL_K = 1.0;

    /**
     * constructor
     *
     * @param scene - scene
     */
    public BasicRayTracer(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        if (closestPoint == null) {
            return _scene.background;
        }
        return calcColor(closestPoint, ray);
    }

    /**
     * help function that calculate the color of the Point.
     *
     * @param closestPoint closest Point.
     * @param ray          ray direction of the camera.
     * @return final color in the point.
     */
    private Color calcColor(GeoPoint closestPoint, Ray ray) {
        return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(_scene.ambientlight.getIntensity());
    }

    /**
     * calculate the color of a point in the scene
     *
     * @param intersection      the point which we calculate the color from
     * @param ray               the ray to the given point
     * @param level             the number of max recursion levels
     * @param k                 the intensity of color
     * @return the color of the requested point
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
        if(intersection == null) return Color.BLACK;
        Color color = intersection.geometry.getEmission();
        color = color.add(calcLocalEffects(intersection, ray, level, k));
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
    }

    /**
     * calculate the reflection and transparency
     * @param geopoint     point which we calculate the color from
     * @param inRay         the ray to the given point
     * @param level        the number of max recursion levels
     * @param k            the intensity of color
     * @return the color in the point consider in reflection and transparency
     */
    private Color calcGlobalEffects(GeoPoint geopoint, Ray inRay, int level, double k) {
        Color color = Color.BLACK;
        Material material = geopoint.geometry.getMaterial();
        Vector n = geopoint.geometry.getNormal(geopoint.point);

        double kr = material.Kr, kkr = k * kr;
        if (kkr > MIN_CALC_COLOR_K) {
            Ray reflectedRay = constructReflectedRay(n, geopoint.point, inRay);

            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
        }
        double kt = material.Kt, kkt = k * kt;
        if (kkt > MIN_CALC_COLOR_K) {
            Ray refractedRay = constructRefractedRay(n, geopoint.point, inRay);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
        }
        return color;
    }

    /**
     *The function build reflected ray from the given point ,
     * that is reflected from the surface direction of the original ray
     * @param n     - normal to the surface`s geometry  at the point
     * @param point - the required point
     * @param inRay - ray
     * @return new Reflected Ray
     */
    private Ray constructReflectedRay(Vector n, Point3D point, Ray inRay) {
        Vector v = inRay.getDir();
        double vn = n.dotProduct(v);
        if (isZero(vn))
            return null;
        Vector r = v.subtract(n.scale(2 * vn));
        return new Ray(point, r, n);
    }

    /**
     * The function build refracted ray,
     * from the given point in continuation of the original ray.
     * @param n     - normal at the point
     * @param point - the required point
     * @param inRay - ray
     * @return new Refracted Ray
     */
    private Ray constructRefractedRay(Vector n, Point3D point, Ray inRay) {
        return new Ray(point, inRay.getDir(), n);
    }

    /**
     * The function find the intersections of a ray with the scene
     * and get the intersection point that is closest to the ray head,
     * if there is no intersections the function return null
     *
     * @param ray - ray intersecting the scene
     * @return the closest intersection point
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        return ray.findClosestGeoPoint(_scene.geometries.findGeoIntersections(ray));
    }

    /**
     * calculate the color reflect from the point by Fong's model
     *
     * @param intersection      - the point
     * @param ray               - ray direction of the camera
     * @return the color reflect from the point
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray, int level, double k) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        Material material = intersection.geometry.getMaterial();
        int nShininess = material.nShininess;
        double kd = material.Kd;
        double ks = material.Ks;
        Color color = Color.BLACK;

        for (LightSource lightSource : _scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            double ktr = transparency(l, n, intersection, lightSource);
            if (nl * nv > 0) {
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

    /**
     * help function for fong's model calculate the specular of light
     *
     * @param ks                 secular component
     * @param l                  direction from light to point
     * @param n                  normal to surface at the point
     * @param v                  direction from point of view to point
     * @param nShininess         shininess level
     * @param lightIntensity     light's intensity originally
     * @return intensity of light with the specular
     */
    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.add(n.scale(-2 * l.dotProduct(n)));
        double t = alignZero(-r.dotProduct(v));
        return t > 0 ? lightIntensity.scale(ks * Math.pow(t, nShininess)) : Color.BLACK;
    }

    /**
     * help function for fong's model calculate the diffuse of light
     *
     * @param kd             -diffusive component
     * @param l              -direction from light to point
     * @param n              -normal to surface at the point
     * @param lightIntensity -light's intensity originally
     * @return intensity of light after the diffusion
     */
    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        double s = l.dotProduct(n);
        if (s < 0)
            s = -s;
        // ensure the absolute value of l.dotProduct(n) in scalar
        return lightIntensity.scale(kd * s);
    }

//	/**
//	 * The function checks if the point is unshaded
//	 *
//	 * @param l           - direction vector of light
//	 * @param n           - normal
//	 * @param gp          - geoPoint
//	 * @param lightSource - lightSource
//	 * @return true if the point is unshaded otherwise return false
//	 */
//	private boolean unshaded(Vector l, Vector n, GeoPoint gp, LightSource lightSource) {
//		Vector lightDirection = l.scale(-1);
//		Ray ray = new Ray(gp.point, lightDirection, n);
//		List<GeoPoint> geoPoints = scene.geometries.findGeoIntersections(ray);
//		double d = lightSource.getDistance(gp.point);
//		if (geoPoints != null) {
//			for (var g : geoPoints) {
//				if (alignZero(g.point.distance(gp.point) - d) <= 0 && isZero(gp.geometry.getMaterial().kT)) {
//					return false;
//				}
//			}
//		}
//		return true;
//	}

    /**
     * The function checks if there is any object shading the light source from a point
     * @param l           -  direction from light to the point
     * @param n           - normal to the geometry object surface at the point
     * @param geoPoint    - geoPoint
     * @param light       - lightSource
     * @return volume of shade
     */
    private double transparency(Vector l, Vector n, GeoPoint geoPoint, LightSource light) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
        double lightDistance = light.getDistance(geoPoint.point);
        var intersections = _scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null)
            return 1.0;
        double ktr = 1.0;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geoPoint.point) - lightDistance) <= 0) {
                ktr *= gp.geometry.getMaterial().Kt;
                if (ktr < MIN_CALC_COLOR_K)
                    return 0;
            }
        }
        return ktr;
    }
}

