
package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Composite class for all Intersectable object
 */
public class Geometries implements Intersectable {

    private List<Intersectable> _intersectables =null;

    /**
     * Default C-tor that initializes the field-   _intersectables LinkedList
     * with an empty list
     */
    public Geometries() {
        _intersectables = new LinkedList<>();
    }

    /**
     * C-tor that get a list of Intersectable
     * @param geometries - the name of the Intersectable`s param
     *                   We use the add function to add that to the list
     */
    public Geometries(Intersectable... geometries) {
        this._intersectables = new LinkedList<>();
        add(geometries);
    }

    /**
     * The function get a Intersectable`s param
     * @param geometries - the name of the Intersectable`s param
     * and add that to the _intersectables list
     */
    public void add(Intersectable... geometries) {
        /**
        * The for loop goes over each parameter in geometries
         * and add that to _intersectables LinkedList
        */
        Collections.addAll(this._intersectables, geometries);
    }

  /**
   * The function find the intersections between the ray and the
   * @param ray        - The Ray to intersect
   * @return list of intersections point
   */
  @Override
  public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
      List<GeoPoint> result = null;

      for (Intersectable item : _intersectables) {
          //get intersections points of a particular item from _intersectables
          List<GeoPoint> itempoints = item.findGeoIntersections(ray, maxDistance);
          if(itempoints!= null){
              //first time initialize result to new LinkedList
              if(result== null){
                  result= new LinkedList<>();
              }
              //add all item points to the resulting list
              result.addAll(itempoints);
          }
      }
      return result;
  }
}
