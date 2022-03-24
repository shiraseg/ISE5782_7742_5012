package unittests.Renderer;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Renderer.Camera;
import primitives.*;
import geometries.*;

import java.util.LinkedList;
import java.util.List;



public class cameraIntegrationTests
{



    /**
     * Integration tests of Camera Ray with Ray-Sphere intersections
     */

    @Test
    public void cameraRaySphereIntegration() {
        Camera cam1 = new Camera(new Point(0,0,0), new Vector(0, 0, -1), new Vector(0, -1, 0));
        Camera cam2 = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Small Sphere 2 points
        assertCountIntersections(cam1, new Sphere(new Point(0, 0, -3),1), 2);

        // TC02: Big Sphere 18 points
        assertCountIntersections(cam2, new Sphere(new Point(0, 0, -2.5),2.5), 18);

        // TC03: Medium Sphere 10 points
        assertCountIntersections(cam2, new Sphere( new Point(0, 0, -2),2), 10);

        // TC04: Inside Sphere 9 points
        assertCountIntersections(cam2, new Sphere( new Point(0, 0, -1),4), 9);

        // TC05: Beyond Sphere 0 points
        assertCountIntersections(cam1, new Sphere( new Point(0, 0, 1),0.5), 0);
    }

}
