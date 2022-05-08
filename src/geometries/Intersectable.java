package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.List;
import java.util.Objects;

public abstract class Intersectable
{
  //  public abstract List<Point> findIntersections(Ray ray);
    //An internal Assistance Department-PDS
    public static class GeoPoint
    {

        public Geometry geometry;
        public Point point;

        //constructor.
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint:" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '.';
        }
    }

    public List<Point> findIntersections(Ray ray)
    {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();

    }
        abstract protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    public List<GeoPoint> findGeoIntersections(Ray ray)
    {
        return this.findGeoIntersectionsHelper(ray);
    }

}
