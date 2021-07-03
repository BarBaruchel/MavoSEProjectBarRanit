package elements;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CameraRayIntersectionsIntegrationTests {

    /**
     * Test help o the  function to count the intersections and compare with expected value
     * @param cam         camera for the test
     * @param geo         3D body (can be any geometry body) to test the integration of the camera with
     * @param expected    amount of intersections
     */
    private void assertCountIntersections(Camera cam, Intersectable geo, int expected) {
        int count = 0;

        List<Point3D> allpoints = null;

        cam.setViewPlaneSize(3, 3);
        cam.setDistance(1);
        int nX =3;
        int nY =3;
        //view plane 3X3 (WxH 3X3 & nx,ny =3 => Rx,Ry =1) , there for, the for loop is 3X3
        for (int i = 0; i < nY; ++i) {
            for (int j = 0; j < nX; ++j) {
                //get a list of the points that intersect with the ray:  cam.constructRayThroughPixel(nX, nY, j, i)
                var intersections = geo.findIntersections(cam.constructRayThroughPixel(nX, nY, j, i));
                if (intersections != null) {
                    if (allpoints == null) {
                        allpoints = new LinkedList<>();
                    }
                    allpoints.addAll(intersections);
                }
                count += intersections == null ? 0 : intersections.size();   //count of how many point are intersected
            }
        }

        System.out.format("there is %d points:%n", count);
        if (allpoints != null) {
            for (var item : allpoints) {
                System.out.println(item);
            }
        }
        System.out.println();

        assertEquals(expected, count, "Wrong amount of intersections");
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Sphere intersections
     */
    @Test
    public void testCameraRaySphereIntegration() {
        Camera cam1 = new Camera(              //Camera(Point3D p0, Vector vTo,Vector vUp)
                Point3D.ZERO,
                new Vector(0, 0, -1),
                new Vector(0, -1, 0));
        Camera cam2 = new Camera(
                new Point3D(0, 0, 0.5),
                new Vector(0, 0, -1),
                new Vector(0, -1, 0));

        // TC01: The Sphere is smaller then the View plane - expected to 2 points are been hit
        assertCountIntersections(cam1, new Sphere(1, new Point3D(0, 0, -3)), 2);

        // TC02:The Sphere is bigger then the View plane , the ray can hit the Sphere twice (from inside to outside), 9 rays hit -  expected to 18 points are been hit
        assertCountIntersections(cam2, new Sphere(2.5, new Point3D(0, 0, -2.5)), 18);

        // TC03: The Sphere is Medium in ratio to the View plane, 5 rays hit - expected to 10 points are been hit
        assertCountIntersections(cam2, new Sphere(2, new Point3D(0, 0, -2)), 10);

        // TC04: The point of the camera is inside the Sphere- will be 9 points because there are no back points for the camera - expected to 9 points are been hit
        assertCountIntersections(cam2, new Sphere(4, new Point3D(0, 0, -1)), 9);

        // TC05: The camera is beyond the Sphere so there is no point of intersection - expected to 0 points are been hit
        assertCountIntersections(cam1, new Sphere(0.5, new Point3D(0, 0, 1)), 0);
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Plane intersections
     */
    @Test
    public void cameraRayPlaneIntegration() {
        Camera cam = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Plane against camera - expected to 9 points are been hit
        assertCountIntersections(cam, new Plane(new Point3D(0, 0, -5), new Vector(0, 0, 1)), 9);

        // TC02: Plane with small angle - expected to 9 points are been hit
        assertCountIntersections(cam, new Plane(new Point3D(0, 0, -5), new Vector(0, 1, 2)), 9);

        // TC03: Plane parallel to lower rays - expected to 6 points are been hit
        assertCountIntersections(cam, new Plane(new Point3D(0, 0, -5), new Vector(0, 1, 1)), 6);

        // TC04: The camera is beyond the Plane so there is no point of intersection - expected to 0 points are been hit
        assertCountIntersections(cam, new Plane(new Point3D(0, 0, -5), new Vector(0, 1, 1)), 6);
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Triangle intersections
     */
    @Test
    public void cameraRayTriangleIntegration() {
        Camera cam = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: The Triangle is smaller then the View plane - expected to 1 points are been hit
        assertCountIntersections(cam, new Triangle(new Point3D(1, 1, -2), new Point3D(-1, 1, -2), new Point3D(0, -1, -2)), 1);

        // TC02: The Triangle is Medium in ratio to the View plane - expected to 2 points are been hit
        assertCountIntersections(cam, new Triangle(new Point3D(1, 1, -2), new Point3D(-1, 1, -2), new Point3D(0, -20, -2)), 2);
    }
}
