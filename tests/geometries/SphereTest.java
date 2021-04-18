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
        Sphere sph = new Sphere(new Point3D(0, 0, 2),2.0 );
        assertEquals(new Vector(0, 0, 1), sph.getNormal(new Point3D(0, 0, 4)), "Error: Sphere getNormal not returning correct value");
    }
}