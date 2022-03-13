package unittests.geometries;

import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void getNormal()
    {
       Point center=new Point(-2,0,0);
       Sphere sphere=new Sphere(center,2);
       Point pOnSphere=new Point(-2,0,2);
       Vector normal=center.subtract(pOnSphere);
       assertEquals(normal,sphere.getNormal(pOnSphere),"ERROR: wrong normal on sphere");
    }
}