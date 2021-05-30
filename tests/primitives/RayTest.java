package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * tests for primitives.Ray class
 */
class RayTest {

    Ray ray = new Ray(Point3D.ZERO,new Vector(0, 0, 1));

    @Test
    void testFindClosestPointEP() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: To test The first point is closest to the beginning of the foundation

        List<Point3D> list = new LinkedList<Point3D>();
        list.add(new Point3D(-1000, 90, 100));
        list.add(new Point3D(50, 40, 1000));
        list.add(new Point3D(0, 5, 1));
        list.add(new Point3D(-20, 60, 50));
        list.add(new Point3D(0, 0, -90));

        assertEquals(list.get(2), ray.findClosestPoint(list), "not expected point");

    }

    @Test
    void testFindClosestPointBVA() {
        // =============== Boundary Values Tests ==================
        // TC11: To test Empty list (method should return null value)
        List<Point3D> list = null;
        assertNull(ray.findClosestPoint(list), "supposed to be null");

    }

}