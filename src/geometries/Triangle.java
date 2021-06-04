package geometries;

import primitives.*;

import java.util.List;

/**
 * class Triangle that inheritor from Polygon
 */
public class Triangle extends Polygon{ //implements Geometry

    /**
     *  constructor that get 3 point from class Point3D and call to super constructor
     *  whit the three variable
     * @param p1
     * @param p2
     * @param p3
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }


    /**
     * @return the variables of the Triangle and print
     */
    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }

    /**
     * The function call to geometries.Polygon.findGeoIntersections
     * that find the intersections between the ray and the plane
     * @param ray -that need to find where is hit
     * @return list of intersections point
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return super.findGeoIntersections(ray);
    }
}
