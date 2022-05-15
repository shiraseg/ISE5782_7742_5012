package Renderer;

import Lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;


import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase
{
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;

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
        return calcColor(closestPoint, ray)==null?scene.getBackground():calcColor(closestPoint,ray);
    }


    private Color calcColor(GeoPoint point, Ray ray, int level, double k)
    {
//        return scene.getAmbienLight().getIntensity() //ka*Ia
//                .add(point.geometry.getEmission(), //+Ie
        Color color= calcLocalEffects(point, ray); //+calculated light contribution from all light sources
        return level==1? color:color.add(calcGlobalEffects(point, ray,level,k));

    }

    private Color calcGlobalEffects(GeoPoint gp,Ray ray, int level, double k)
    {
        Color color=Color.BLACK;
        Vector normal=gp.geometry.getNormal(gp.point);
        Material material=gp.geometry.getMaterial();
        double kkr=k*material.k
    }


    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        int nShininess = gp.geometry.getMaterial().getnShininess();
        Double3 kd = gp.geometry.getMaterial().kD;
        Double3 ks = gp.geometry.getMaterial().kS;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.getLights()) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                 if(unshaded(gp,lightSource,l,n,nv))
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

    private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n, double nv)
    {
        Vector lightDirection = l.scale(-1);//from point to light source
        Vector epsVector = n.scale(nv < 0 ? DELTA : -DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        double maxDistance =light.getDistance(gp.point);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        //if there are no intersections return true (there is no shadow)
        if (intersections == null)
            return true;

        //for each intersection
        for (GeoPoint intersection : intersections) {
            //if there are points in the intersections list that are closer to the point
            //then light source, return false
            if (maxDistance > intersection.point.distance(gp.point)) {
                return false;
            }
        }
        return true;
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









