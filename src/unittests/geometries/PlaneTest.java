package unittests.geometries;

import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    void getNormal() {

    }

    @Test
    public void testConstructor()
    {
        // ============ Equivalence Partitions Tests ==============
        try
        {
            new Plane(new Point(1,1,1),new Point(1,1,1),new Point(1,2,1));
        }
        catch(IllegalArgumentException e)
        {
            fail("failed constructing the plane");
        }
        //TC01: same points
        assertThrows( IllegalArgumentException.class, () -> new Plane(new Point(1,1,1),new Point(1,1,1),new Point(1,2,1)),
                "Constructed a plane with tow equal points");

        //TC01: same ray
        assertThrows( IllegalArgumentException.class, () -> new Plane(new Point(1,1,1),new Point(2,2,2),new Point(1,2,1)),
                "Constructed a plane with tow points on the same ray");
    }

    @Test
    void testGetNormal()
    {
        Point p1=new Point(1,1,1);
        Point p2=new Point(1,1,0);
        Point p3=new Point(1,0,1);
        Plane plane=new Plane (p1,p2,p3);
        assertEquals(plane.getNormal(),new Vector(-1,0,0),"ERROR:wrong value!");

        assertEquals(1,plane.getNormal().length(),"ERROR: the length isn't 1");

    }
}