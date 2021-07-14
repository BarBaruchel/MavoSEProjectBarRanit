package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;

/**
 * The type Camera
 */
public class Camera {
    /**
     * the point that the rays come out from the camera
     * and hit the view plane
     */
    private Point3D _p0;
    /**
     * the point that the rays come out from the camera
     * and go in orthogonal direction to the view plane (and hit it)
     */
    private Vector _vTo;
    /**
     * the point that the rays come out from the camera
     * and go straight up
     */
    private Vector _vUp;
    /**
     * vRight is eqaul to:  vRight= vTo X vUp
     */
     private Vector _vRight;

    private double _distance =1; //distance from _p0 to the view plane
    private double _width =1;
    private double _height =1;

    /**
    * constructor that get 3 points and create Camera`s variable
    * @param p0   -the point that the rays come out from the camera
   * @param vTo  - normalized this vector
    * @param vUp - normalized this vector
    *  update _vRight to vRight= vTo X vUp
   */
  public Camera(Point3D p0, Vector vTo,Vector vUp ) {
      this._p0 = p0;
      _vTo=vTo.normalized();
      _vUp=vUp.normalized();
      /**
       * _vTo.dotProduct(_vUp) need to be orthogonal that mean need to be 0.
       * if it`s not 0- throw Exception, else do crossProduct
       */
      if(!isZero(_vTo.dotProduct(_vUp))){
          throw new IllegalArgumentException("vUp is not orthogonal to vTo ");
      }
      _vRight=_vTo.crossProduct(_vUp);  // vRight= vTo X vUp
  }

    /**
     * Constructs Ray through a single pixel of the screen
     * @param nX             Number of pixels in X axis
     * @param nY             Number of pixels in Y axis
     * @param j              The current pixel in Y axis
     * @param i              The current pixel in X axis
     * @return               The generated ray
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        double rX = _width / nX;
        double rY = _height / nY;
        double xJ = (j - (nX - 1) / 2d) * rX;
        double yIminus = (i - (nY - 1) / 2d) * rY;

        /**
         * pIJ is the center Point of the square pixel
         */
        Point3D pIJ = _p0.add(_vTo.scale(_distance)); // the view plane center point
        if (!isZero(xJ))
            pIJ = pIJ.add(_vRight.scale(xJ));
        if (!isZero(yIminus))
            pIJ = pIJ.add(_vUp.scale(-yIminus)); // it's also possible to do pIJ.subtract(vUp.scale(yIminus));

        return new Ray(_p0, pIJ.subtract(_p0));

    }

    /**
     * Constructs Ray through a single pixel of the screen (in specific resolution)
     *
     * @param nX             Number of pixels in X axis
     * @param nY             Number of pixels in Y axis
     * @param j              The current pixel in Y axis
     * @param i              The current pixel in X axis
     * @param resolution      The amount of rays sent to calculate the average color point in a pixel
     * @return  The generated ray from the calculation
     */
   public List<Ray> constructRaysThroughPixel(int nX, int nY, int j, int i, int resolution) {
       /**
        * pCenter is the center Point of the square pixel
        */
       Point3D pCenter = _p0.add(_vTo.scale(_distance));  // the view plane center point
       Point3D pixelCenter = calculateTheSquareCenterPoint(_height / nY, _width / nX, nX, nY, j, i, pCenter);
       List<Ray> rays = new LinkedList<Ray>();
       /**
        * creating a ray to center of Pixel
        */
       rays.add(new Ray(_p0, pixelCenter.subtract(_p0)));
       if (resolution != 0) {
           double squareHeight = _height / nY / resolution;
           double squareWidth = _width / nX / resolution;
           for (int row = 0; row < resolution; row++)
               for (int column = 0; column < resolution; column++) {
                   Point3D result = calculateTheSquareCenterPoint(squareHeight, squareWidth, resolution, resolution, column, row,
                           pixelCenter);
                   rays.add(new Ray(_p0, result.subtract(_p0)));
               }
       }
       return rays;
   }


    /**
     * The function get parameters and according to them ,calculate the
     * center Point of the square pixel effect by _vUp and _vRight
     *
     * @param squareHeight  height from the center of the square pixel
     * @param squareWidth   width from the center of the square pixel
     * @param nX             Number of pixels in X axis
     * @param nY             Number of pixels in Y axis
     * @param j              The current pixel in Y axis
     * @param i              The current pixel in X axis
     * @param pixelCenter    center Point 3D of the square pixel
     * @return  the center Point of the square pixel effect by _vUp and _vRight
     */
    private Point3D calculateTheSquareCenterPoint(double squareHeight, double squareWidth, int nX, int nY, int j, int i,
                                                Point3D pixelCenter) {

        double highFromCenter = -((i - (nY - 1) / 2d) * squareHeight);
        double widthFromCenter = (j - (nX - 1) / 2d) * squareWidth;
        /**
         * Pij is the center Point of the square pixel
         */
        Point3D pIJ = pixelCenter;
        /**
         * move the Point to vUp direction , in length highFromCenter
         */
        if (highFromCenter != 0) {
            pIJ = pIJ.add(_vUp.scale(highFromCenter));
        }
        /**
         * move the Point to vRight direction , in length widthFromCenter
         */
        if (widthFromCenter != 0) {
            pIJ = pIJ.add(_vRight.scale(widthFromCenter));
        }
        return pIJ;
    }


    /**
     * getter _p0 field
     * @return reference to the _p0 point of the Camera
     */
    public Point3D getP0() {
        return _p0;
    }

    /**
     * getter _vTo field
     * @return reference to the _vTo point of the Camera
     */
    public Vector getvTo() {
        return _vTo;
    }

    /**
     * getter _vUp field
     * @return reference to the _vUp point of the Camera
     */
    public Vector getvUp() {
        return _vUp;
    }

    /**
     * The function  get to numbers
     * @param width
     * @param height
     * Update the width and height of the camera for the size of the View Plane
     * @return the camera object itself
     */
    public Camera setViewPlaneSize(double width, double height) {
        _width = width;
        _height = height;
        return this;
    }

    /**
     * the function update the distance of the camera from the View Plane
     * @param distance
     * @return the camera object itself
     */
    public Camera setDistance(double distance) {
        _distance = distance;
        return this;
    }
}

