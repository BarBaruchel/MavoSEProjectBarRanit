package renderer;

import elements.Camera;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.MissingResourceException;

/**
 * Class Render will create from the scene
 * the color matrix of the image
 */
public class Render {

    ImageWriter _imageWriter = null;
   // Scene _scene ;
    Camera _camera = null;
    RayTracerBase _rayTracerBase = null;

    /**
     * setter function that get ImageWriter variable and update the _imageWriter
     * @param imageWriter
     * @return the object itself for threading
     */
    public Render setImageWriter(ImageWriter imageWriter) {
        _imageWriter = imageWriter;
        return this;
    }

//  /**
//   * setter function that get Scene variable and update the _scene
//   * @param scene
//   * @return the object itself for threading
//   */
//  public Render setScene(Scene scene) {
//      _scene = scene;
//      return this;
//  }

    /**
     * setter function that get Camera variable and update the _camera
     * @param camera
     * @return the object itself for threading
     */
    public Render setCamera(Camera camera) {
        _camera = camera;
        return this;
    }

    /**
     * setter function that get RayTracerBase variable and update the _rayTracerBase
     * @param rayTracer
     * @return the object itself for threading
     */
    public Render setRayTracer(RayTracerBase rayTracer) {
        _rayTracerBase = rayTracer;
        return this;
    }

    /**
     * The renderImage() method does not return any value
     * the function first check that a non-empty value has been entered in all fields
     * (and in case of lack throws an appropriate exception)
     * the function has a loop on all the pixels of the ViewPlane,
     * for each pixel a ray will be built and for each ray we will get a color from the ray comb.
     * The color will be put in the suitable pixel of the image maker (writePixel)
     */
    public void renderImage() {
        try {
            if (_imageWriter == null) {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
            }
 //         if (_scene == null) {
 //              throw new MissingResourceException("missing resource", Scene.class.getName(), "");
 //                 }
            if (_camera == null) {
                throw new MissingResourceException("missing resource", Camera.class.getName(), "");
            }
            if (_rayTracerBase == null) {
                throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");
            }

            //rendering the image
            int nX = _imageWriter.getNx();
            int nY = _imageWriter.getNy();
            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                    Ray ray = _camera.constructRayThroughPixel(nX, nY, j, i);
                    /**
                     * we put into pixelColor the color of the pixel from the _rayTracerBase
                     */
                    Color pixelColor = _rayTracerBase.traceRay(ray);
                    /**
                     * _imageWriter will brush the pixel in the suitable color
                     */
                    _imageWriter.writePixel(j, i, pixelColor);
                }
            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet " + e.getClassName());
        }
    }

    /**
     * A method that will create a grid of lines similar to what was performed in the test in the first step
     * @param interval
     * @param color
     */
    public void printGrid(int interval, Color color) {
        int nX = _imageWriter.getNx();
        int nY = _imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    _imageWriter.writePixel(j, i, color);
                }
            }
        }
    }

    /**
     * call to writeToImage function in ImageWriter class
     */
    public void writeToImage() {
        _imageWriter.writeToImage();
    }
}