package geometries;

import primitives.*;

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
     * Function that get point
     * @param point
     * @return null
     */
    public Vector getNormal(Point3D point) {
        return null;
    }

}
