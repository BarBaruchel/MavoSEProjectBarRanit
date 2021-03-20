package geometries;

import primitives.*;

public class Triangle extends Polygon{ //implements Geometry
//* constructor that det 3 point from class Point3D*/
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }

// the function return the object */

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }
// Function that get point and return null
    public Vector getNormal(Point3D point) {
        return null;
    }

}
