package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import primitives.*;
/**
 * Testing Polygons
 */
public class PolygonTest {

    /**
     * Test method for
     *  {@link geometries.Polygon#Polygon(Point3D...)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01:To check if the Polygon`s c-tor can get a wrong vertices order
        assertThrows(IllegalArgumentException.class,
                () -> new Polygon(
                        new Point3D(4, 0, 0),
                        new Point3D(0, 4, 0),
                        new Point3D(0, 0, 4),
                        new Point3D(-4, 4, 4)),
                "ERROR: Constructing a polygon with wrong order of vertices- Failed!"
        );


        // TC02: To check if it will build concave square
        assertThrows(IllegalArgumentException.class,
                () -> new Polygon(
                        new Point3D(0, 0, 4),
                        new Point3D(4, 0, 0),
                        new Point3D(0, 4, 0),
                        new Point3D(1, 1, 1)),
                "ERROR: Constructing a concave polygon- Failed!"
        );


        // TC03: To test if the correct concave square with vertices in correct order
        try {
            new Polygon(new Point3D(0, 0, 4), new Point3D(4, 0, 0),
                    new Point3D(0, 4, 0), new Point3D(-4, 4, 4));
        } catch (IllegalArgumentException e) {
            fail("ERROR: Constructing a correct polygon- Failed!");
        }

        // TC04: To check if the vertices in the same plane for the polygon
     assertThrows(
             IllegalArgumentException.class,
              () -> new Polygon(
                       new Point3D(4, 0, 4),
                       new Point3D(0, 4, 0),
                       new Point3D(3, 0, 0),
                       new Point3D(0, 3, 3)),
              "ERROR: The vertices for the polygon not in the same plane!"
       );





        // =============== Boundary Values Tests ==================

        // TC10: To test when there are two same points
        assertThrows(IllegalArgumentException.class,
                () -> new Polygon(
                        new Point3D(0, 0, 1),
                        new Point3D(1, 0, 0),
                        new Point3D(0, 1, 0),
                        new Point3D(0, 1, 0)),
                "The Constructed polygon has vertex on the side"
        );

        // TC11: To test when the first point and the last point are the same point
       assertThrows(IllegalArgumentException.class,
               () -> new Polygon(
                       new Point3D(0, 0, 3),
                       new Point3D(2, 0, 0),
                       new Point3D(0, 2, 0),
                       new Point3D(0, 0, 3)),
               "The Constructed polygon has vertex on the side"
       );

        // TC12: To test when the square has vertex on the side
        assertThrows(IllegalArgumentException.class,
                () -> new Polygon(
                        new Point3D(0, 0, 4),
                        new Point3D(4, 0, 0),
                        new Point3D(0, 4, 0),
                        new Point3D(0, 0.25, 0.25)),
                "The Constructed polygon has vertex on the side"
        );

   }

    /*
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: To test if the getNormal returning correct value
        Polygon pl = new Polygon(new Point3D(0, 0, 4), new Point3D(4, 0, 0), new Point3D(0, 4, 0),
                new Point3D(-4, 4, 4));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 4)), "The Polygon`s normal is not good");
    }

}


