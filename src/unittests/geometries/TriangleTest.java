package unittests.geometries;

import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest
{
    @Test
    void getNormal()
    {
        Triangle triangle=new Triangle(new Point(3,2,1),new Point(0,0,1),new Point(1,2,3));
        double length=Math.sqrt(68);        //length of the normal.
        Vector v1 = new Vector(-4/length,6/length,-4/length);
        Vector v2 = triangle.getNormal(new Point(1,2,3)).normalize();
        assertTrue(v1.equals(v2), "ERROR: bad normal on triangle!");


    };

}