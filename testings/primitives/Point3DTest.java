package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {

    Point3D pt1=new Point3D(0.0d,0.0d,0.000d);
    Point3D pt2=new Point3D(0.00d,0.5d,0.0000000000000001);
    @Test
    void testEquals() {
        /**
         * check if pt1 equals to pt2
         */
        assertEquals(pt1,pt2);
    }

    @Test
    void testToString() {
        System.out.println(pt1);
        System.out.println(pt2);
    }


    @Test
    void distanceSquared() {
        Point3D pt3= new Point3D(0,1,3);
        double result=pt1.distanceSquared(pt3);
        assertEquals(10.0,result,0.76);
        System.out.println(result);
    }
}