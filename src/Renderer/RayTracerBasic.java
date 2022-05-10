package Renderer;

import Lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;


import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase
{

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    private static final double DELTA = 0.1;

    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersection = scene.geometries.findGeoIntersections(ray);
        if (intersection == null) {
            return scene.getBackground();
        }
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersection);
        return calcColor(closestPoint, ray);
    }


    private Color calcColor(GeoPoint point, Ray ray) {
        return scene.getAmbienLight().getIntensity()
                .add(calcLocalEffects(point, ray));

    }


    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir().normalize();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.getLights()) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)

                 if(unshaded(gp,l,n,v,lightSource))
                 {
                     Color iL = lightSource.getIntensity(gp.point);
                     color = color.add(
                             iL.scale(calcDiffusive(material, nl)),
                             iL.scale(calcSpecular(material, n, l, nl, v)));
                 }


            }
        }
        return color;
    }


    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(Math.abs(nl));
    }

    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2)).normalize();
        double minusVR = 0 - alignZero(r.dotProduct(v));
        if (minusVR <= 0)
            return Double3.ZERO;
        return material.kS.scale(Math.pow(Math.max(0, r.dotProduct(v.scale(-1d))), material.getnShininess()));
    }

    private boolean unshaded(GeoPoint gp, Vector origLightDir, Vector normal,Vector rayDir, LightSource light)
    {
        Vector lightDirection = origLightDir.scale(-1); // from point to light source
        Vector deltaVec=normal.scale(alignZero(normal.dotProduct(lightDirection))<0? DELTA:-DELTA);
        Point point=gp.point.add(deltaVec);
        Ray lightRay = new Ray(gp.point , lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if(intersections == null)
            return true;
        else
            return afterRay(intersections,light,point);

    }

    boolean afterRay(List<GeoPoint> intersections, LightSource light,Point rayHead)
    {
        for (int i=0;i<intersections.size();i++)
        {
            //loop that checks if the intersection geometry is in the range.
            if(rayHead.distance(intersections.get(i).point)<light.getDistance(intersections.get(i).point))
                return false;
        }
        return  true;
    }
}









