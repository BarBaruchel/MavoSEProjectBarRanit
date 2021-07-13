package primitives;

/**
 * Class Material - represents the material of objects
 */
public class Material {
    /**
     * Diffusion attenuation
     */
    public double Kd =0d;
    /**
     * Specular attenuation
     */
    public double Ks=0d;
    /**
     * Specular shininess degree
     */
    public int nShininess=0;
    /**
     * Reflection attenuation, for default there is no reflection at all
     */
    public double Kr =0d;
    /**
     * Transparency attenuation
     */
    public double Kt = 0d;



    /**
     * setter Kd field
     * @param kd   -coefficient
     * @return the Material object itself for chaining calls
     */
    public Material setKd(double kd) {
        Kd = kd;
        return this;
    }

    /**
     * setter Ks field
     * @param ks
     * @return the Material object itself for chaining calls
     */
    public Material setKs(double ks) {
        Ks = ks;
        return this;
    }

    /**
     * setter nShininess field
     * @param nShininess
     * @return the Material object itself for chaining calls
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     * setter kr field
     * @param kr   -coefficient
     * @return the Material object itself for chaining calls
     */
    public Material setKr(double kr) {
        Kr = kr;
        return this;
    }

    /**
     * setter t field
     * @param kt   -coefficient
     * @return the Material object itself for chaining calls
     */
    public Material setKt(double kt) {
        Kt = kt;
        return this;
    }

    /**
     * getter Kt field
     * @return kt
     */
    public double getKt() {
        return Kt;
    }

    /**
     * getter Kr field
     * @return Kr
     */
    public double getKr() {
        return Kr;
    }
}
