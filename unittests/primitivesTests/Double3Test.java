package primitivesTests;

import org.junit.jupiter.api.Test;
import primitives.Double3;

import static org.junit.jupiter.api.Assertions.*;

class Double3Test {

    /**
     * Checks adding point to a point
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: When adding a point to a point
        Double3 d1 = new Double3(1,1,1);
        Double3 d2 = new Double3(2,2,2);
        Double3 d3 = new Double3(3,3,3);
        assertEquals(d1.add(d2), d3, "Adding double3 to double3 Failed");
    }
    /**
     * Checks subtracting a point from a point
     */
    @Test
    void testSubtract() {
        // TC02: When subtracting a point from a point
        Double3 d1 = new Double3(1,1,1);
        Double3 d2 = new Double3(2,2,2);
        Double3 d3 = new Double3(3,3,3);
        assertEquals(d3.subtract(d2), d1, "Subtracting double3 to double3 Failed");
    }

    /**
     * checking multiply Double3 by a number
     */
    @Test
    void testScale() {
        // TC03: checking multiply Double3 by a number
        Double3 d1 = new Double3(1,1,1);
        Double3 d2 = new Double3(2,2,2);
        double num = 2;
        assertEquals(d1.scale(num), d2, "Scaling double3 by a double Failed");
    }

    /**
     * checking reduction of number from Double3
     */
    @Test
    void testReduce() {
        // TC04: checking reduction of number from Double3
        Double3 d1 = new Double3(1,1,1);
        Double3 d2 = new Double3(2,2,2);
        double num = 2;
        assertEquals(d2.reduce(num), d1, "Reducing double3 by a double Failed");
    }

    /**
     * checking product between Double3's
     */
    @Test
    void testProduct() {
        // TC05: checking product between Double3's
        Double3 d1 = new Double3(4,4,4);
        Double3 d2 = new Double3(2,2,2);
        Double3 d3 = new Double3(8,8,8);
        assertEquals(d1.product(d2), d3, "Product double3 by double3 Failed");
    }
}