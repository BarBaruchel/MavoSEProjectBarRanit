package elements;

import primitives.Color;

/**
 * The type Light.
 */
public abstract class Light {
    /**
     * intensity of ambient light, Color type
     */
    protected final Color _intensity;

    /**
     * C-tor that initialize Light`s parameters
     * @param intensity   -intensity of ambient light, Color type
     */
    public Light(Color intensity) {
        _intensity = intensity;
    }

    /**
     * getter _intensity field
     * @return the _intensity of the light
     */
    public Color getIntensity() {
        return _intensity;
    }
}
