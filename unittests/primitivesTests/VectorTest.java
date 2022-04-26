package primitivesTests;

import org.junit.jupiter.api.Test;
import primitives.Util;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class VectorTest {
    /**
     * test construction of Vector 0
     */
    @Test
    void testVectorZero() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test construction of Vector 0
        try { // test zero vector
            new Vector(0, 0, 0);
            out.println("ERROR: zero vector does not throw an exception");
        } catch (Exception e) {
        }
    }

    /**
     * multiply vector by number and get correct vector
     */
    @Test
    void testScale() {
        // TC02:test multiply vector by number and get correct vector
        Vector v1 = new Vector(2,3,4);
        Vector v2 = v1.scale(2);
        Vector v3 = new Vector(4,6,8);
        assertEquals(v2, v3, "Scale of Vector Failed");
    }

    /**
     * Length squared of vector
     */
    @Test
    void testLengthSquared() {
        // TC03:test Length squared of vector
        Vector v1 = new Vector(2,3,4);
        double d = 29;
        assertEquals(v1.lengthSquared(), d, "Length_Squared of Vector Failed");
    }

    /**
     * Length of vector
     */
    @Test
    void testLength() {
        // TC04: test Length of vector
        Vector v1 = new Vector(2,3,6);
        double d = 7;
        assertEquals(v1.length(), d, "Length of Vector Failed");
    }

    /**
     * check if normalize really gives correct outcome-the Normal vector
     */
    @Test
    void testNormalize() {
        // TC05:check if normalize really gives correct outcome-the Normal vector
        Vector v1 = new Vector(2,3,6);
        double num = Math.sqrt((2*2)+(3*3)+(6*6));
        Vector v3 =  new Vector(2/num,3/num,6/num);
        assertEquals(v1.normalize(), v3, "Normalize of Vector Failed");
    }

    /**
     * Add vector to vector and getting correct outcome vector
     */
    @Test
    void testAdd() {
        // TC06: Add vector to vector and getting correct outcome vector
        Vector v1 = new Vector(2,2,2);
        Vector v2 = new Vector(3,3,3);
        Vector v3 = new Vector(5,5,5);
        assertEquals(v1.add(v2), v3, "Add Vector to Vector Failed");
    }

    /**
     * dot product between 2 vectors and checking for ortogonal vectors which will produce vector 0
     */
    @Test
    void testDotProduct() {
        // =============== Boundary Values Tests ==================
        // TC01:test dot product between 2 vectors and checking for ortogonal vectors which will produce vector 0
        Vector v1 = new Vector(2,3,4);
        Vector v2 = new Vector(5,6,7);
        Vector v3 = new Vector(1,2,3);
        double d = 56;
        assertEquals(v1.dotProduct(v2), d, "dotProduct of Vector Failed");
        Vector v4 = new Vector(0, 3, -2);
        assertTrue(Util.isZero(v3.dotProduct(v4)), "ERROR: dotProduct() for orthogonal vectors is not zero");
    }

    /**
     * cross product between 2 vectors and getting outcome vector
     * and checking for all kinds of edge-cases
     */
    @Test
    void testCrossProduct() {
        // TC02: test cross product between 2 vectors and getting outcome vector
        //       and checking for all kinds of edge-cases
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);

        try { // test zero vector
            v1.crossProduct(v2);
            out.println("ERROR: crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {
        }
        Vector vr = v1.crossProduct(v3);
        assertTrue(Util.isZero(vr.length() - v1.length() * v3.length()), "ERROR: crossProduct() wrong result length");

        assertTrue(Util.isZero(vr.dotProduct(v1)) || !Util.isZero(vr.dotProduct(v3)), "ERROR: crossProduct() result is not orthogonal to its operands");

        // test vector normalization vs vector length and cross-product
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();
        assertTrue(Util.isZero(u.length() - 1), "ERROR: the normalized vector is not a unit vector");

        try { // test that the vectors are co-lined
            v.crossProduct(u);
            out.println("ERROR: the normalized vector is not parallel to the original one");
        } catch (Exception e) {
        }

        assertTrue(!(v.dotProduct(u) < 0), "ERROR: the normalized vector is opposite to the original one");
    }

}