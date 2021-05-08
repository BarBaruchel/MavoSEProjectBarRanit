package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormalPoint3D() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: To test if the getNormal returning correct value
        Point3D a = new Point3D(1, 1, 1);
        Point3D b = new Point3D(-2, -3, -4);
        Point3D c = new Point3D(5, 7, 8);
        Plane plane = new Plane(a, b, c);
        Vector normal = new Vector(2.0 / 3, 1.0 / 3, -2.0 / 3);
        assertTrue( normal.equals(plane.getNormal(null)),"Error: Plane getNormal not returning correct value");

    }

    @Test
    void testFindIntersections() {

        Plane pl = new Plane(new
                Point3D(0, 0, 1),
                new Vector(1, 1, 1));

        // ============ Equivalence Partitions Tests ==============
        // TC01: The Ray into the Plane
        assertEquals(List.of(new Point3D(1, 0, 0)),
                pl.findIntersections(new Ray(
                        new Point3D(0.5, 0, 0),
                        new Vector(1, 0, 0))),
                "wrong plane intersection point");

        // TC02: The Ray out of the Plane
        assertNull(pl.findIntersections(
                new Ray(new Point3D(2, 0, 0),
                        new Vector(1, 0, 0))),
                "Must not be plane intersection point");

        // =============== Boundary Values Tests ==================
        // TC11:  Ray's parallel to Plane
        assertNull(pl.findIntersections(
                new Ray(new Point3D(1, 1, 1),
                        new Vector(0, 1, -1))),
                "Must not be plane intersection point");

        // TC12: The Ray in Plane
        assertNull(pl.findIntersections(
                new Ray(new Point3D(0, 0.5, .5),
                        new Vector(0, 1, -1))),
                "Must not be plane intersection point");


        // TC13: Orthogonal Ray into the Plane
        assertEquals(List.of(new Point3D(1d / 3, 1d / 3, 1d / 3)),
                pl.findIntersections(
                        new Ray(new Point3D(1, 1, 1),
                                new Vector(-1, -1, -1))),
                "wrong plane intersection point");

        // TC14: Orthogonal Ray out of the Plane
        assertNull(pl.findIntersections(
                new Ray(new Point3D(1, 1, 1),
                        new Vector(1, 1, 1))),
                "Must not be plane intersection point");

        // TC15: Orthogonal Ray from the Plane
        assertNull(pl.findIntersections(
                new Ray(new Point3D(0, 0.5, 0.5),
                        new Vector(1, 1, 1))),
                "Must not be plane intersection point");

        // TC16: Ray from the Plane
        assertNull(pl.findIntersections(
                new Ray(new Point3D(0, 0.5, 0.5),
                        new Vector(1, 1, 0))),
                "Must not be plane intersection point");

        // TC17: The Ray from Plane's Q point
        assertNull(pl.findIntersections(
                new Ray(new Point3D(0, 0, 1),
                        new Vector(1, 1, 0))),
                "Must not be plane intersection point");
    }
}