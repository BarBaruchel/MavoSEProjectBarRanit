package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * The type Camera
 */
public class Camera {
    private Point3D _p0;   // the point that the rays come out from the camera
    private Vector _vTo;
    private Vector _vUp;
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

        Point3D pIJ = _p0.add(_vTo.scale(_distance)); // the view plane center point
        if (!isZero(xJ))
            pIJ = pIJ.add(_vRight.scale(xJ));
        if (!isZero(yIminus))
            pIJ = pIJ.add(_vUp.scale(-yIminus)); // it's also possible to do pIJ.subtract(vUp.scale(yIminus));

        return new Ray(_p0, pIJ.subtract(_p0));



     // Point3D Pc = _p0.add(_vTo.scale(_distance));  // Image center: Pc= p0+ vTo

     //   //Ratio (pixel width & height):
     //   double Rx = _width / nX;
     //   double Ry = _height / nY;
//
     //   Point3D Pij = Pc;
     //   Vector Vij; //vector that goes from _p0 TO Pij
//
     //   double Xj = (j - (nX - 1) / 2d) * Rx;
     //   double Yi = -(i - (nY - 1) / 2d) * Ry;
//
//
     //   if (isZero(Xj) && isZero(Yi)) {  //  if Yi=0 and Xi=0, then Yi = Pc
     //      Vij=Pij.subtract(_p0);
     //   }
     //   else if (isZero(Xj)) {
     //       Pij = Pij.add(_vUp.scale(Yi));
     //   }
     //   else if (isZero(Yi)) {
     //       Pij = Pij.add(_vRight.scale(Xj));
     //   }
//
     //   Pij = Pc.add(_vRight.scale(Xj).add(_vUp.scale(Yi)));
     //   Vij=Pij.subtract(_p0);
     //   return new Ray(_p0, Vij);

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

