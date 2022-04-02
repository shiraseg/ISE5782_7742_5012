package Renderer;

import geometries.Geometries;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class RayTracerBasic extends RayTracerBase
{

    public RayTracerBasic(Scene scene)
    {
        super(scene);
    }

    private Color calcColor(Point point) {
        return scene.AmbientLight.getIntensity();
    }

    @Override
    public Color traceRay(Ray ray)
    {
//        Geometries geometries = scene.geometries();
//        List<Point> intersectionPoints = geometries.findIntersections(ray);
//        Point closestPoint = ray.findClosestPoint(intersectionPoints);
//        return calcColor(closestPoint);
        return null;
    }


}
