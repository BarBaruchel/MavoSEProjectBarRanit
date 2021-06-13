package primitives;

/**
 * Class Material - represents the material of objects
 */
public class Material {
    public double Kd =0d;
    public double Ks=0d;
    public int nShininess=0;

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

}
