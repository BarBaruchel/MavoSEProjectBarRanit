package renderer;

import geometries.Intersectable;
import static geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
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
            return calcColor(closestPoint);
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
     * @param geoPoint
     * @return color
     */
    private Color calcColor(GeoPoint geoPoint) {
        Color intensity= geoPoint.geometry.getEmission();
        intensity = intensity.add(_scene.ambientlight.getIntensity());
        return intensity;
    }
}