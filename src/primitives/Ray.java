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
    /**
     * @params _p0 pOrigin
     * @params _dir direction
     */
    final Point3D _p0;
    final Vector _dir;

    /**
     * constructor that get point3D`s variable and vector`s variable and initialization them
     * and normalized the vector`s variable
     * @param p0
     * @param dir direction
     */
    public Ray(Point3D p0, Vector dir) {
        _p0 = p0;
        _dir = dir.normalized();
    }

    /**
     * getter p0 field
     * @return the p0 point of the Ray
     */
    public Point3D get_p0() {
        return _p0;
    }

    /**
     * getter dir field
     * @return copy of the dir vector of the Ray
     */
    public Vector get_dir() {
        return new Vector(_dir._head);
    }

    /**
     * find the closest Point to origin ray
     * @param pointsList intersections point List
     * @return closest point
     */
    public Point3D findClosestPoint(List<Point3D> pointsList){
        /**
         * the near point
         */
        Point3D result =null;
        /**
         * initialize with a big number that we sure it will change
         */
        double closestDistance = Double.MAX_VALUE;

        /**
         * if the point equals to null, it`s mean there
         * is no point that close to it
         */
        if(pointsList== null){
            return null;
        }

        for (Point3D p: pointsList) {
            double temp = p.distance(_p0);
            if(temp < closestDistance){
                closestDistance =temp;
                result =p;
            }
        }

        return  result;
    }

    /**
     *  find the closest Point to origin ray
     * @param geoPointsList intersections geometry point List
     * @return closest geometry point
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPointsList){
        /**
         * the near point
         */
        GeoPoint result =null;
        /**
         * initialize with a big number that we sure it will change
         */
        double closestDistance = Double.MAX_VALUE;

        /**
         * if the point equals to null, it`s mean there
         * is no point that close to it
         */
        if(geoPointsList== null){
            return null;
        }

        for (GeoPoint geo: geoPointsList) {
            double temp = geo.point.distance(_p0);
            if(temp < closestDistance){
                closestDistance =temp;
                result =geo;
            }
        }

        return  result;
    }

    /**
     * the function check if the two parameters are equal
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

    /**
     * @param t - A certain length
     * @return returns the point that the ray hit
     */
    public Point3D getTargetPoint(double t) {
        if (isZero(t)) {
            return _p0;
        }
        return _p0.add(_dir.scale(t));
    }
}
