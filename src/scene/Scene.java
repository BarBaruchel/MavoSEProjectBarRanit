package scene;

import elements.AmbientLight;
import elements.LightSource;
import geometries.Geometries;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * Scene class, according PDS
 */
public class Scene {

    /**
     * The name of the scene
     */
    private final String _name;

    /**
     * Background color, default - Black color
     */
    public Color background = Color.BLACK;
    /**
     * Environmental lighting, default - Black lighting
     */
    public AmbientLight ambientlight= new AmbientLight(Color.BLACK, 0);//new Color(192, 192, 192),1.d);
    /**
     * 3D model, by default it will initialize to an empty model
     */
    public Geometries geometries = null;

    public List<LightSource> lights= new LinkedList<>();

    /**
     * A c-tor who gets the name of the scene (only)
     * who will also build an empty collection of bodies for model D3
     * @param name -The name of the scene
     */
    public Scene(String name) {
        _name = name;
        geometries= new Geometries();
    }

    /**
     *     chaining set methods (*not* a builder pattern)
     */

    /**
     *The function update Background color
     * @param background -Background color,
     * @return the scene object it self
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return  this;
    }

    /**
     * The function update filling / ambient lighting
     * @param ambientlight  -Environmental lighting
     * @return the scene object it self
     */
    public Scene setAmbientLight(AmbientLight ambientlight) {
        this.ambientlight = ambientlight;
        return this;
    }

    /**
     * The function update 3D model
     * @param geometries  - 3D model
     * @return the scene object it self
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return  this;
    }

}