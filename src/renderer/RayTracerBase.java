package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * abstract class renderer.RayTracerBase
 */
public abstract class RayTracerBase {

    /**
     * Scene type variable
     */
    protected Scene _scene;

    /**
     * c-tor that get Scene object
     * @param scene -  Scene type variable
     */
    public RayTracerBase(Scene scene) {
        _scene = scene;
    }

    /**
     * public abstract function that get ray
     * @param ray
     * @return color
     */
    public abstract Color traceRay(Ray ray);

}