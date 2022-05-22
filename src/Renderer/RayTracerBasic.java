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
    private static final Double3 INITIAL_K =new Double3(1);

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    private Ray constructRefractedRay(Point point, Ray inRay, Vector n)
    {
        return new Ray(point, inRay.getDir(), n);
    }

    private Ray constructReflectedRay(Point point,  Ray inRay, Vector n)
    {

        Vector v = inRay.getDir();
        double vn = v.dotProduct(n);

        Vector r = v.subtract(n.scale(2 * vn)).normalize();
        return new Ray(point, r, n);
    }


    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersection = scene.geometries.findGeoIntersections(ray);
        if (intersection == null) {
            return scene.getBackground();
        }
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersection);
        return calcColor(closestPoint, ray)==null?scene.getBackground():calcColor(closestPoint,ray);
    }

    private Color calcColor(GeoPoint closestPoint, Ray ray)
    {
        return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.getAmbienLight().getIntensity());
    }


    private Color calcColor(GeoPoint point, Ray ray, int level, Double3 k)
    {
//        return scene.getAmbienLight().getIntensity() //ka*Ia
//                .add(point.geometry.getEmission(), //+Ie
        Color color= calcLocalEffects(point, ray,k); //+calculated light contribution from all light sources
        return level==1? color:color.add(calcGlobalEffects(point, ray,level,k));

    }

    private Color calcLocalEffects(GeoPoint gp, Ray ray,Double3 k) {
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
            if (nl * nv > 0)
            { // sign(nl) == sing(nv)
                Double3 ktr = transparency(gp,lightSource,l, n);
                 if(ktr.product(k).biggerThan(MIN_CALC_COLOR_K))
                 {
                     Color iL = lightSource.getIntensity(gp.point).scale(ktr);
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


    private Color calcGlobalEffects(GeoPoint gp,Ray ray, int level, Double3 k)
    {
        Color color=Color.BLACK;
        Vector normal=gp.geometry.getNormal(gp.point);
        Material material=gp.geometry.getMaterial();
        Double3 kkr=material.kR.product(k);
        if(kkr.biggerThan(MIN_CALC_COLOR_K))
            color=calcGlobalEffect(constructReflectedRay(gp.point,ray,normal),level,material.kR,kkr);
        Double3 kkt =material.kT.product(k);
        if (kkt.biggerThan(MIN_CALC_COLOR_K))
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp.point, ray, normal), level, material.kT, kkt));
        return color;
    }

    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersectionPoints = scene.geometries.findGeoIntersections(ray);
        if (intersectionPoints == null) {
            return null;
        }
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersectionPoints);
        return closestPoint;
    }

    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx)
    {
        GeoPoint gp = findClosestIntersection (ray);
        return (gp == null ? scene.getBackground() : calcColor(gp, ray, level-1, kkx)).scale(kx);
    }

    private Double3 transparency(GeoPoint gp, LightSource light, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1);//from point to light source
        // Refactored ray head move
        Ray lightRay = new Ray(gp.point, lightDirection, n);
        double maxDistance = light.getDistance(gp.point);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        //if there are no intersections return true (there is no shadow)
        if (intersections == null)
            return new Double3(1.0);

        for (var geo : intersections) {
            double dist = geo.point.distance(gp.point);
            //if the point is farther than the maximum distance- remove from list.
            if (dist >= maxDistance)
                intersections.remove(geo);

        }

        if (intersections.isEmpty())
            return new Double3(1.0);


        Double3 ktr = new Double3(1.0);
        //for each intersection
        for (GeoPoint intersection : intersections) {
            ktr = intersection.geometry.getMaterial().kT.product(ktr);
            if (ktr.lowerThan(MIN_CALC_COLOR_K))
                return Double3.ZERO;
        }
        return ktr;
    }

}







