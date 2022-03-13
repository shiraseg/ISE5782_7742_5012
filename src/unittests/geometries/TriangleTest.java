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
        assertEquals(new Vector(-4/length,6/length,-4/length),triangle.getNormal(new Point(1,2,3)).normalize(),
        "ERROR: bad normal on triangle!");


    };

}