package Renderer;

import primitives.Color;
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
        var intersection= scene.geometries.findGeoIntersections(ray);
        if(intersection==null) return scene.getBackground();
        GeoPoint closestPoint=ray.findClosestGeoPoint(intersection);
        return calcColor(closestPoint);
    }


}
