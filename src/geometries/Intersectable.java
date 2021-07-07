
package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;
import java.util.stream.Collectors;

/**
 * An interface for all intersectables 3D objects
 */
public interface  Intersectable {

    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;

        /**
         * Instantiates a new GeoPoint with a Geometry and a Point
         * @param geometry   -the geometry of the point
         * @param point      -the point in the geometry
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }

    }

    /**
     * Find intersections of a Ray with the Object(s)
     * @param ray - The Ray to intersect
     * @return List of Point3D intersection points
     */
    default List<Point3D> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList
                .stream()
                .map(gp -> gp.point)
                .collect(Collectors.toList());
    }

    /**
     * Find intersections of a Ray with the Object(s)
     * @param ray        - The Ray to intersect
     * @return List of GeoPoint intersection points
     */
    default List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }

    List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance);
}
