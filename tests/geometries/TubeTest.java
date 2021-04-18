package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tube Tester
 */
class TubeTest {

    /**
     * Test method for {@link Tube#getNormal(Point3D)}
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: To test if the getNormal returning correct value
        Ray ray = new Ray(new Point3D(0, 0, 0), new Vector(1, 0, 0));
        Tube tube = new geometries.Tube(ray, 1);
        Point3D point = new Point3D(1, 1, 0);
        Vector tNormal = tube.getNormal(point);
        assertEquals(tNormal, new Vector(0, 1, 0), "Error: Tube getNormal not returning correct value");
    }
}