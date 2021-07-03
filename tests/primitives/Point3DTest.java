package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {

    @Test
    void testDistanceSquared() {
        Point3D p1= new Point3D(0,0,-1);
        Point3D p2= new Point3D(1,0,1);
        assertEquals(5d,p1.distanceSquared(p2));
    }

    @Test
    void testDistance() {
        Point3D p1= new Point3D(0,0,-1);
        Point3D p2= new Point3D(1,0,1);
        assertEquals(Math.sqrt(5d),p1.distance(p2));

    }

    @Test
    void subtractTest() {
        Point3D p1= new Point3D(0,0,-1);
        Point3D p2= new Point3D(1,0,1);
        Vector v= p1.subtract(p2);
        assertEquals( new Point3D(-1,0,-2), v._head);
    }
}