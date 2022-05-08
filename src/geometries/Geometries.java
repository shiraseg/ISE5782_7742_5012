package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable
{

    protected List<Intersectable> intersectablesList;

    public Geometries() {
        intersectablesList = new LinkedList<>();
    }

    public Geometries(Intersectable... intersectables)
    {
        intersectablesList = new LinkedList<>();
        Collections.addAll(intersectablesList, intersectables);

    }

    public void add(Intersectable...intersectables){
        Collections.addAll(intersectablesList,intersectables);
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray)
    {
        List<GeoPoint> intersections = null;
        for (Intersectable geometry : intersectablesList)
        {
            var geoIntersections = geometry.findGeoIntersections(ray);
            if (geoIntersections != null)
            {
                if (intersections == null)
                {
                    intersections = new LinkedList<>();
                }
                intersections.addAll(geoIntersections);
            }
        }
        return intersections;
    }
}

