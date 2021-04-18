package primitives;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static primitives.Util.isZero;

/**
 * tests for primitives.Vector class
 */
class VectorTest {
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

    @Test
    void testDotProductBVA() {
        // =============== Boundary Values Tests ==================
        // TC11: To dotProduct() function for orthogonal vectors
        assertTrue( isZero(v1.dotProduct(v3)),
                "ERROR: dotProduct() for orthogonal vectors is not zero");
    }


    @Test
    public void testDotProductEP() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: To test if the dotProduct() function returning correct value
        assertTrue(isZero(v1.dotProduct(v2) + 28),
                "ERROR: dotProduct() wrong value");
    }

    @Test
    void testAddEP() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: To check if the add() function on vectors is calculate correctly
        assertEquals(new Vector(1, 2, 3),
                new Vector(4, 5, 6).add(new Vector(-3, -3, -3)),
                "ERROR:  Problem with the calculation in the add() function");
    }
    @Test
    void testAddBVA() {
        // =============== Boundary Values Tests ==================
        // TC11: To test adding v + (-v)
        assertThrows(IllegalArgumentException.class,
                ()-> new Vector(new Vector(4, 5, 6).add(new Vector(-4, -5, -6))),
                "ERROR: Can not  add v plus -v must throw exception");
    }

    @Test
    void testSubtractEP() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: To check if the subtract() function on vectors is calculate correctly
        assertEquals(
                new Vector(1, 2, 3),
                new Vector(4, 5, 6).subtract(new Vector(3, 3, 3)),
                "ERROR:  Problem with the calculation in the subtract() function");
    }

    @Test
    void testSubtractBVA() {
        // =============== Boundary Values Tests ==================
        // TC11: To test subtract (-v) - (-v)
        assertThrows(IllegalArgumentException.class,
                () -> new Vector(new Vector(-4, -5, -6).subtract(new Vector(-4, -5, -6))),
                "ERROR: Can not Subtract v from v");

    }

    @Test
    public void testScaleEP() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: To check if the scale() function on vector is calculate correctly
        assertEquals(new Vector(-4,-8 , -12),
                new Vector(2, 4, 6).scale(-2),
                "ERROR:  Problem with the calculation in the scale() function");
    }

    @Test
    public void testScaleBVA() {
        // =============== Boundary Values Tests ==================
        // TC11: test scaling to 0
        assertThrows(IllegalArgumentException.class,
                () -> new Vector(4, 5, 6).scale(0.0d),
                "ERROR: Can not scale by 0");
    }

    @Test
    void testVectorZero(){
        //Test that the vector equals to (0,0,0)
        assertThrows(IllegalArgumentException.class, ()-> new Vector(0,0,0),"ERROR: only variable ZERO point can be (0,0,0)" );
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProductEP() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: To check if the crossProduct() function on vectors is calculate correctly
        Vector vr = v1.crossProduct(v3);

        // TC01:  To check cross-product`s  length is suitable
        assertEquals(v1.length() * v3.length(), vr.length(),
                0.00001,
                "ERROR: crossProduct() wrong result length");

        //  TC02: To check if the result of cross-product is orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "ERROR: crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v3)), "ERROR: crossProduct() result is not orthogonal to 2nd operand");
    }
    @Test
    public void testCrossProductBVA() {
        // =============== Boundary Values Tests ==================
        //TC11: test scaling on parallel vectors
        assertThrows(
                IllegalArgumentException.class,
                () -> v1.crossProduct(v2),
                "ERROR: Can not Cross-Product for parallel vectors"
        );
    }


    @Test
    void testLength() {
        assertEquals(10.0d, new Vector(0, 6, 8).length(),
                "ERROR: Problem with the calculation in the length() function");
    }

    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: To check if the LengthSquared() function on vector is calculate correctly
        assertEquals(13.0d, v3.lengthSquared(),
                0.000000001,
                "ERROR: Problem with the calculation in the lengthSquared() function");
    }

    @Test
    void testNormalize() {
        Vector v = new Vector(4, 5, 7);
        v.normalize();
        // ============ Equivalence Partitions Tests ==============
        // TC01: To check if the length() function calculation the object that sent correctly
        assertEquals(1.0, v.length(), 1e-10, "ERROR:Problem with the calculation in the lengthSquared() function ");

        // =============== Boundary Values Tests ==================
        //TC11: To test if Vector create (0,0,0) vector
        assertThrows(IllegalArgumentException.class,
                () -> new Vector(0, 0, 0),
                "ERROR: head can not be (0,0,0)");
    }


    @Test
    void testNormalized() {
        Vector v = new Vector(4, 5, 6);
        Vector n = v.normalize();
        // ============ Equivalence Partitions Tests ==============
        // TC01: To check if the normalized() function changed the object that sent
        assertTrue(v == n, "ERROR: normalized() does not change the vector itself");

        // TC02: To check if the lengthSquared() function calculation the object that sent correctly
        assertEquals(1.0d, v.lengthSquared(), 0.00001,
                "ERROR: wrong normalized vector length");

        // TC03: To check if the normalized() function calculation the object that correctly
        assertEquals(new Vector(0.4558423058385518,0.5698028822981898,0.6837634587578276), v, "ERROR: wrong normalized vector");

    }
}