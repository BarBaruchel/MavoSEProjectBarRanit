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
}
