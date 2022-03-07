package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void add() {
        Point p1 = new Point(1,1,1);
        Vector v1 = new Vector(2,2,2);
        Point p3 = new Point(3,3,3);
        assertEquals(p1.add(v1), p3, "Errorrrrrrrrrrrrrrrr");
    }

    @Test
    void subtract() {

    }

    @Test
    void distanceSquared() {
    }

    @Test
    void distance() {
    }
}