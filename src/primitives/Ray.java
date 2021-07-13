package primitives;

import geometries.Intersectable;

import static geometries.Intersectable.GeoPoint;

import java.util.List;
import java.util.Objects;

import static primitives.Util.isZero;

/**
 * class Ray that get point in 3D and vector that cannot changed
 */
public class Ray {
    private final static double DELTA = 0.1;

    /**
     * @params _p0 pOrigin  , Ray head point
     * @params _dir direction , Ray direction vector
     */
    final Point3D _p0;
    final Vector _dir;

    /**
     * constructor that get point3D`s variable and vector`s variable and initialization them
     * and normalized the vector`s variable
     *
     * @param p0  -Ray head point
     * @param dir  -direction
     */
    public Ray(Point3D p0, Vector dir) {
        _p0 = p0;
        _dir = dir.normalized();
    }

    /**
     * constructor that get point3D`s variable and 2 vectors variable and initialization them,
     * the beginning point is moved by DELTA lengthwise the line defined by vector n and according to
     * 	direction (dir)
     *
     * @param p0   -Ray head point
     * @param dir  -direction  of moving the point
     * @param n   -line vector for point p movement
     */
    public Ray(Point3D p0, Vector dir, Vector n) {
        if (n.dotProduct(dir) >= 0){
            Vector delta = n.scale(DELTA);
            _p0 = p0.add(delta);
            _dir = dir.normalized();
        }
       else{
           Vector delta = n.scale(-DELTA);
           _p0 = p0.add(delta);
           _dir = dir.normalized();
       }
    }


    /**
     * We created instead the lightDirection we had,
     * We wanted something that comes out of point and a bit shifted in the direction of the lightdirection
     *
     * c-tor that get 4 variables:
     * @param point                   ray beginning point
     * @param lightDirection          ray direction vector
     * @param n                       line vector for point p movement
     * @param DELTA                    equal 0.1
     */
    public Ray(Point3D point, Vector lightDirection, Vector n, double DELTA) {
        /**
         * if n.dotProduct(lightDirection) it`s positive (bigger then zero)
         * then add DELTA , else minus DELTA
         */
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
        _p0 = point.add(delta);
        _dir= lightDirection.normalized();
    }

    /**
     * getter p0 field
     *
     * @return the p0 point of the Ray
     */
    public Point3D getP0() {
        return _p0;
    }

    /**
     * getter dir field
     *
     * @return copy of the dir vector of the Ray
     */
    public Vector getDir() {
        return new Vector(_dir._head);
    }

    /**
     * Get point on ray at a distance from ray's head
     *
     * @param delta distance from ray head
     * @return the point
     */
    public Point3D getPoint(double delta) {
        return isZero(delta) ? _p0 : _p0.add(_dir.scale(delta));
    }

    /**
     * find the closest Point to origin ray
     *
     * @param pointsList intersections point List
     * @return closest point
     */
    public Point3D findClosestPoint(List<Point3D> pointsList) {
        /**
         * the near point
         */
        Point3D result = null;
        /**
         * initialize with a big number that we sure it will change
         */
        double closestDistance = Double.MAX_VALUE;

        /**
         * if the point equals to null, it`s mean there
         * is no point that close to it
         */
        if (pointsList == null) {
            return null;
        }

        for (Point3D p : pointsList) {
            double temp = p.distance(_p0);
            if (temp < closestDistance) {
                closestDistance = temp;
                result = p;
            }
        }

        return result;
    }

    /**
     * find the closest Point to origin ray
     *
     * @param geoPointsList intersections geometry point List
     * @return closest geometry point
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPointsList) {
        /**
         * the near point
         */
        GeoPoint result = null;
        /**
         * initialize with a big number that we sure it will change
         */
        double closestDistance = Double.MAX_VALUE;

        /**
         * if the point equals to null, it`s mean there
         * is no point that close to it
         */
        if (geoPointsList == null) {
            return null;
        }

        for (GeoPoint geo : geoPointsList) {
            double temp = geo.point.distance(_p0);
            if (temp < closestDistance) {
                closestDistance = temp;
                result = geo;
            }
        }

        return result;
    }

    /**
     * the function check if the two parameters are equal
     *
     * @param o Object (basically another Ray) to compare
     * @return true if equal, else return false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return _p0.equals(ray._p0) && _dir.equals(ray._dir);
    }

    /**
     * @return the point and the vector and print
     */
    @Override
    public String toString() {
        return "Point3D:" + _p0 + "\n" + "Vector:" + _dir;
    }

}
