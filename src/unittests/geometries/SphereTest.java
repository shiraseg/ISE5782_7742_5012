package unittests.geometries;

import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void getNormal() {
        Sphere s1 = new Sphere(new Point(0,0,0),5);
        Point p = new Point(5,0,0);
        Vector n = p.subtract(new Point(0,0,0)).normalize();
        assertEquals(n, s1.getNormal(new Point(0,0,0)));
    }
}