package elements;

import primitives.Color;

/**
 * Ambient Light Color
 */
public class AmbientLight {
    /**
     * intensity of ambient light, Color type
     */
    final private Color _intensity;

    /**
     * Constructor
     * @param Ia  - Color`s parameter, light intensity by RGB components
     * @param Ka - Double`s parameter, constant for filler light
     */
    public AmbientLight(Color Ia, double Ka) {
        _intensity = Ia.scale(Ka);
    }

    /**
     * The func getIntensity return value of ambient lighting intensity
     * @return intensity (Color type)
     */
    public Color getIntensity() {
        return _intensity;
    }

}