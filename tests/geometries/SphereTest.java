package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    /**
     * Test method for {@link Sphere#getNormal(Point3D)}
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: To test if the getNormal returning correct value
        Sphere sph = new Sphere(2.0, new Point3D(0, 0, 2));
        assertEquals(new Vector(0, 0, 1),
                     sph.getNormal(new Point3D(0, 0, 4)),
                "Error: Sphere getNormal not returning correct value");
    }

    @Test
    void testFindIntersections() {

        Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> exp = List.of(p1, p2);

        // TC01: The Ray's line is outside of the Sphere - means zero points
        assertNull(sphere.findIntersections(
                new Ray(
                        new Point3D(-1, 0, 0),
                        new Vector(1, 1, 0))),
                " The Ray's line out of the Sphere");


        // TC02: The Ray starts before and crosses the Sphere - means 2 points
        List<Point3D> result = sphere.findIntersections(
                new Ray(
                        new Point3D(-1, 0, 0),
                        new Vector(3, 1, 0)
                )
        );
        assertEquals(2, result.size(), "Wrong number of points");
        //if (result.get(0).getX() > result.get(1).getX())
      //  result = List.of(result.get(1), result.get(0));
        assertEquals(exp, result, "The Ray crosses the Sphere");

        // TC03: Ray starts inside the Sphere (1 point)
        assertEquals(
                List.of(p2),
                sphere.findIntersections(
                        new Ray(
                                new Point3D(0.5, 0.5, 0),
                                new Vector(3, 1, 0))),
                "Ray from inside the Sphere");

        // TC04: Ray starts after the Sphere - means zero points
        assertNull(
                sphere.findIntersections(
                        new Ray(new Point3D(2, 1, 0), new Vector(3, 1, 0))),
                "Ray starts after the Sphere");

        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)

        // TC11: The Ray starts at Sphere and goes inside - means 1 points
        assertEquals(List.of(new Point3D(2, 0, 0)),
                sphere.findIntersections(new Ray(new Point3D(1, -1, 0), new Vector(1, 1, 0))),
                "Ray from Sphere to inside");

        // TC12: Ray starts at Sphere and goes outside - means zero points
        assertNull(
                sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(1, 1, 0))),
                "Ray from Sphere to outside");

        // **** Group: Ray's line goes through the center

        // TC13: Ray starts before the Sphere - means 2 points
        result = sphere.findIntersections(new Ray(new Point3D(1, -2, 0), new Vector(0, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        //if (result.get(0).getY() > result.get(1).getY())
       // result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(new Point3D(1, -1, 0), new Point3D(1, 1, 0)),
                result,
                "Line through O (the center) , ray crosses the Sphere");

        // TC14: The Ray starts at Sphere and goes inside - means 1 points
        assertEquals(List.of(new Point3D(1, 1, 0)),
                sphere.findIntersections(new Ray(new Point3D(1, -1, 0), new Vector(0, 1, 0))),
                "Line through O (the center) , ray from the Sphere and crosses it");

        // TC15: The Ray starts inside the Sphere - means 1 points
        assertEquals(List.of(new Point3D(1, 1, 0)),
                sphere.findIntersections(new Ray(new Point3D(1, 0.5, 0), new Vector(0, 1, 0))),
                "Line through (the center) , ray from inside the Sphere");

        // TC16: The Ray starts at the center - means 1 points
        assertEquals(List.of(new Point3D(1, 1, 0)),
                sphere.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(0, 1, 0))),
                "Line through O (the center) , ray from O (the center)");

        // TC17: Ray starts at sphere and goes outside - means zero points
        assertNull(sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(0, 1, 0))),
                "Line through O (the center), ray from Sphere to outside");

        // TC18: Ray starts after Sphere - means zero points
        assertNull(sphere.findIntersections(new Ray(new Point3D(1, 2, 0), new Vector(0, 1, 0))),
                "Line through O (the center) , ray outside Sphere");

        // **** Group: The Ray's line is tangent to the sphere (all tests have zero points)

        // TC19: The Ray start before the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point3D(0, 1, 0), new Vector(1, 0, 0))),
                "Tangent line, the ray before Sphere");

        // TC20: The Ray start at the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 0, 0))),
                "Tangent line, the ray at Sphere");

        // TC21: The Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point3D(2, 1, 0), new Vector(1, 0, 0))),
                "Tangent line, the ray after Sphere");

        // *** Group of Special cases

        // TC19: The Ray's line is outside, ray is orthogonal to ray that start from sphere's
        // center line
        assertNull(sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(0, 0, 1))),
                "Ray orthogonal to ray head -> O (the center) line");

    }
}