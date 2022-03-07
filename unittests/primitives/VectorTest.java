package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

    @Test
    void scale() {
        Vector v1 = new Vector(2,3,4);
        Vector v2 = v1.scale(2);
        Vector v3 = new Vector(4,6,8);
        assertEquals(v2, v3, "Scale of Vector Failed");
    }

    @Test
    void dotProduct() {
        Vector v1 = new Vector(2,3,4);
        Vector v2 = new Vector(5,6,7);
        double d = 56;
        assertEquals(v1.dotProduct(v2), d, "dotProduct of Vector Failed");
    }

    @Test
    void crossProduct() {
        Vector v1 = new Vector(1,2,3);
        Vector v2 = new Vector(4,5,6);
        Vector v3 = new Vector(-3,6,-3);
        assertEquals(v1.crossProduct(v2), v3, "crossProduct of Vector Failed");
    }

    @Test
    void lengthSquared() {
        Vector v1 = new Vector(2,3,4);
        double d = 29;
        assertEquals(v1.lengthSquared(), d, "Length_Squared of Vector Failed");
    }

    @Test
    void length() {
        Vector v1 = new Vector(2,3,6);
        double d = 7;
        assertEquals(v1.length(), d, "Length of Vector Failed");
    }

    @Test
    void normalize() {
        Vector v1 = new Vector(2,3,6);
        double num = Math.sqrt((2*2)+(3*3)+(6*6));
        Vector v3 =  new Vector(2/num,3/num,6/num);
        assertEquals(v1.normalize(), v3, "Normalize of Vector Failed");
    }

    @Test
    void add() {
        Vector v1 = new Vector(2,2,2);
        Vector v2 = new Vector(3,3,3);
        Vector v3 = new Vector(5,5,5);
        assertEquals(v1.add(v2), v3, "Add Vector to Vector Failed");
    }
}