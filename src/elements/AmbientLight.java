package elements;

import primitives.Color;

/**
 * Ambient Light Color
 */
public class AmbientLight extends Light{

    /**
     * Constructor that get two parameter and send them to father`s c-tor
     * @param Ia  - Color`s parameter, light intensity by RGB components
     * @param Ka - Double`s parameter, constant for filler light
     */
    public AmbientLight(Color Ia, double Ka) {
        super(Ia.scale(Ka));
    }

    /**
     * default Constructor that define the intensity
     * of the ambient light in the super class (=Light) to be black
     */
    public AmbientLight() {
        super(Color.BLACK);
    }

}