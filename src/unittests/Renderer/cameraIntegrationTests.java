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

    private void countIntersections(Camera cam, Intersectable geo, int expected)
    {
        int count = 0;

        List<Point> allIntersections = null;

        cam.setVPSize(3, 3);
        cam.setVPDistance(1);
        int nX =3;
        int nY =3;

        /**
         * Going over all the intersection points and adding them in the list+counting them.
         */

        for (int i = 0; i < nY; ++i) {
            for (int j = 0; j < nX; ++j) {
                var intersections = geo.findIntersections(cam.constructRay(nX, nY, j, i));
                if (intersections != null)
                {
                    if (allIntersections == null)
                    {
                        allIntersections = new LinkedList<>();
                    }
                    allIntersections.addAll(intersections);
                }

                if(intersections!=null)
                    count+=intersections.size();
            }
        }

        System.out.format("there is %d points:%n", count);
        if (allIntersections != null) {
            for (var item : allIntersections) {
                System.out.println(item);
            }
        }

        System.out.println();

        assertEquals(expected, count, "Wrong amount of intersections");
    }


    /**
     * Integration tests of Camera Ray with Ray-Sphere intersections.
     * all the test were taken from the presentation of the pre-curse.
     */


    /**Sphere integration*/

    @Test
    public void cameraRaySphereIntegration() {
        Camera cam1 = new Camera(new Point(0,0,0), new Vector(0, 0, -1), new Vector(0, -1, 0));
        Camera cam2 = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: The sphere is small and far from the VP => 2 points
        countIntersections(cam1, new Sphere(new Point(0, 0, -3),1), 2);

        // TC02:The sphere is big and close => 18 points
        countIntersections(cam2, new Sphere(new Point(0, 0, -2.5),2.5), 18);

        // TC03: The sphere is medium => 10 points
        countIntersections(cam2, new Sphere( new Point(0, 0, -2),2), 10);

        // TC04: The camera is inside the Sphere => 9 points
        countIntersections(cam2, new Sphere( new Point(0, 0, -1),4), 9);

        // TC05: The sphere is behind the camera => 0 points
        countIntersections(cam1, new Sphere( new Point(0, 0, 1),0.5), 0);
    }

    /**Plane integration*/

    @Test
    public void cameraRayPlaneIntegration()
    {
        Camera cam = new Camera(new Point(0,0,0), new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: The plane is against camera => 9 points
        countIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 0, 1)), 9);

        // TC02: The Plane is with small angle => 9 points
        countIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 1, 2)), 9);

        // TC03: The plane is parallel to lower rays => 6 points
        countIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 1, 1)), 6);

        // TC04: The plane is behind the camera => 0 points
        countIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 1, 1)), 6);
    }

    /**Triangle integrations*/

    @Test
    public void cameraRayTriangleIntegration() {
        Camera cam = new Camera(new Point(0,0,0), new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: The triangle is small => 1 point
        countIntersections(cam, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -1, -2)), 1);

        // TC02:  The triangle is medium triangle => 2 points
        countIntersections(cam, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -20, -2)), 2);
    }

}
