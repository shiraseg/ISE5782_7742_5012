package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void add() {
        Point p1 = new Point(1, 2, 3);
        assertEquals(p1.add(new Vector(-1, -2, -3)),new Point(0, 0, 0),"ERROR: Point + Vector does not work correctly");
    }

    @Test
    void subtract() {
        Point p1 = new Point(1, 2, 3);
        assertEquals(new Vector(1, 1, 1),new Point(2, 3, 4).subtract(p1),"ERROR: Point - Point does not work correctly");
    }

    @Test
    void distanceSquared() {
    }

    @Test
    void distance()
    {

    }
}