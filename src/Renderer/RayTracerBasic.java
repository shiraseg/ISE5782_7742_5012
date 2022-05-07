package Renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;


import java.util.List;

public class RayTracerBasic extends RayTracerBase
{

    public RayTracerBasic(Scene scene)
    {
        super(scene);
    }

    private Color calcColor(GeoPoint point)
    {
        return scene.getAmbienLight().getIntensity().add(point.geometry.getEmission());
        //return scene.getAmbienLight().getIntensity();
    }


    @Override
    public Color traceRay(Ray ray)
    {
        List<GeoPoint> intersections=scene.geometries.findGeoIntersections(ray);
        if(intersections==null)
            return scene.getBackground();
        GeoPoint closestPoint=ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint);
    }


}
