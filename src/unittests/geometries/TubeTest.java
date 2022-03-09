package unittests.geometries;

import geometries.Tube;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    void getNormal() {
        Tube t = new Tube(new Ray(new Point(1,1,1), new Vector(0,0,1)),2);
        assertEquals(new Vector(1,1,0).normalize(),t.getNormal(new Point(2,2,2)));
    }
}