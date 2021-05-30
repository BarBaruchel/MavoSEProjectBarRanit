package renderer;

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
        List<Point3D> intersections = _scene.geometries.findIntersections(ray);
        if (intersections != null) {
            Point3D closestPoint = ray.findClosestPoint(intersections);
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
     * @param point
     * @return color
     */
    private Color calcColor(Point3D point) {
        return _scene.ambientlight.getIntensity();
    }
}