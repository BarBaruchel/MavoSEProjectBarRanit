package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */

    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: To test if the getNormal returning correct value
        Point3D a = new Point3D(1, 1, 1);
        Point3D b = new Point3D(-2, -3, -4);
        Point3D c = new Point3D(5, 7, 8);
        Triangle tri = new Triangle(a, b, c);
        Vector normal = new Vector(2.0 / 3, 1.0 / 3, -2.0 / 3);
        assertTrue( normal.equals(tri.getNormal(a)),"Error: Triangle getNormal not returning correct value");
    }

    @Test
    public void testfindIntersectionsRay() {

        Triangle tr = new Triangle(
                new Point3D(0, 0, 1),
                new Point3D(1, 0, 0),
                new Point3D(0, 1, 0));
        Plane pl = new Plane(
                new Point3D(0, 0, 1),
                new Point3D(1, 0, 0),
                new Point3D(0, 1, 0));
        Ray ray;

        // ============ Equivalence Partitions Tests ==============
        // TC01: The ray inside the Triangle
        ray = new Ray(
                new Point3D(1, 1, 1),
                new Vector(-1, -1, -1));
        assertEquals(List.of(new Point3D(1d / 3, 1d / 3, 1d / 3)),
                tr.findIntersections(ray),
                "wrong intersection point");

        // TC02:The ray against Triangle`s edge
        ray = new Ray(
                new Point3D(0, 0, -1),
                new Vector(1, 1, 0));
        assertEquals(List.of(new Point3D(1, 1, -1)),
                pl.findIntersections(ray),
                "Wrong intersection with Triangle");
        assertNull(tr.findIntersections(ray), "wrong intersection point");

        // TC03: The ray against Triangle`s  vertex
        ray = new Ray(
                new Point3D(0, 0, 2),
                new Vector(-1, -1, 0));
        assertEquals(List.of(new Point3D(-0.5, -0.5, 2)),
                pl.findIntersections(ray),
                "Wrong intersection with Triangle");
        assertNull(tr.findIntersections(ray), "wrong intersection point");


        // =============== Boundary Values Tests ==================

        // TC11: The ray in Triangle`s  vertex
        ray = new Ray(
                new Point3D(-1, 0, 0),
                new Vector(1, 1, 0));
        assertEquals(List.of(new Point3D(0, 1, 0)),
                pl.findIntersections(ray),
                "Wrong intersection with Triangle");
        assertNull(tr.findIntersections(ray), "wrong intersection point");

        // TC12: The ray on Triangle`s  edge
        ray = new Ray(
                new Point3D(-1, -1, 0),
                new Vector(1, 1, 0));
        assertEquals(List.of(new Point3D(0.5, 0.5, 0)),
                pl.findIntersections(ray),
                "Wrong intersection with Triangle");
        assertNull(tr.findIntersections(ray), "wrong intersection point");

        // TC13: The ray on Triangle`s edge continuation
        ray = new Ray(
                new Point3D(-2, 0, 0),
                new Vector(1, 1, 0));
        assertEquals(List.of(new Point3D(-0.5, 1.5, 0)),
                pl.findIntersections(ray),
                "Wrong intersection with Triangle");
        assertNull(tr.findIntersections(ray), "wrong intersection point");
    }
}
