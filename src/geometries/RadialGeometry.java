package geometries;

public abstract class RadialGeometry {
    final protected double _radius;

    public RadialGeometry(double radius) {
        _radius = radius;
    }

    /**
     * getter radius field
     *
     * @return reference to the requested radius
     */
    public double getRadius() {
        return _radius;
    }
}
