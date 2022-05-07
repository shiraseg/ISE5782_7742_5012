package Renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 * Camera producing rays through a view plane
 */
public class Camera {

    private  Point p0;          // camera eye
    private  Vector vUp;        // vector pointing upwards : Y axis
    private  Vector vTo;        // vector pointing towards the scene
    private  Vector vRight;     // vector pointing towards the right : X axis

    private double distance;    // cameras distance from ViewPlane
    private double width;       // ViewPlane width
    private double height;      // ViewPlane height

    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;

    public void setP0(Point p0) {
        this.p0 = p0;
    }

    public void setvUp(Vector vUp) {
        this.vUp = vUp;
    }

    public void setvTo(Vector vTo) {
        this.vTo = vTo;
    }

    public void setvRight(Vector vRight) {
        this.vRight = vRight;
    }

    public Camera(Point p0, Vector vTo, Vector vUp)
    {
        if(!isZero(vUp.dotProduct(vTo))){
            throw  new IllegalArgumentException("vTo and vUp should be orthogonal");
        }

        this.p0 = p0;

        //normalizing the positional vectors
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();

        this.vRight = this.vTo.crossProduct(this.vUp);

    }

    // chaining methods

    /**
     * set distance between the camera and it's view plane
     * @param distance the  distance for the view plane
     * @return instance of Camera for chaining
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * setting View Plane size
     * @param width     "physical" width
     * @param height    "physical" height
     * @return instance of Camera for chaining
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Constructing a ray through a pixel
     *
     * @param Nx number of pixels widthwise
     * @param Ny number of pixels heightwise
     * @param j Y value of pixel wanted
     * @param i x value of pixel wanted
     * @return ray form the camera to Pixel[i,j]
     */
    public Ray constructRay(int Nx, int Ny, int j, int i) {
        //Image center
        Point Pc = p0.add(vTo.scale(distance));

        //Ratio (pixel width & height)
        double Ry =height/ Ny;
        double Rx = width/Nx;

        //Pixel[i,j] center
        Point Pij = Pc;

        //delta values for going to Pixel[i,j]  from Pc

        double yI =  -(i - (Ny -1)/2d)* Ry;
        double xJ =  (j - (Nx -1)/2d)* Rx;

        if (! isZero(xJ) ){
            Pij = Pij.add(vRight.scale(xJ));
        }
        if (! isZero(yI)) {
            Pij = Pij.add(vUp.scale(yI));
        }

        return new Ray(p0, Pij.subtract(p0));
    }

    public void writeToImage() {
        if(imageWriter == null)
            throw new MissingResourceException("missing image writer", "Camera", "in writeTorImage");
        imageWriter.writeToImage();
    }

    public Camera renderImage() {
//        try {
//            if (imageWriter == null) {
//                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
//            }
//            if (rayTracer == null) {
//                throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");
//            }
//
//            //rendering the image
//            int nX = imageWriter.getNx();
//            int nY = imageWriter.getNy();
//            for (int i = 0; i < nY; i++) {
//                for (int j = 0; j < nX; j++) {
//                    Ray ray = constructRay(nX, nY, j, i);
//                    Color pixelColor = rayTracer.traceRay(ray);
//                    imageWriter.writePixel(j, i, pixelColor);
//                }
//            }
//        } catch (MissingResourceException e) {
//            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
//        }
//        return this;


        try {
            if (imageWriter == null) {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
            }
            if (rayTracer == null) {
                throw new MissingResourceException("missing resource", RayTracerBasic.class.getName(), "");
            }

            //rendering the image
            int nX = imageWriter.getNx();
            int nY = imageWriter.getNy();
            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                    Ray ray = constructRay(nX, nY, j, i);
                    Color pixelColor = RayTracerBasic.traceRay(ray);
                    imageWriter.writePixel(j, i, pixelColor);
                }
            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
        return this;
    }

    public void printGrid(int interval, Color color) {
        if(imageWriter == null)
            throw new MissingResourceException("missing image writer", "Camera", "in print Grid");
        for (int j = 0; j< imageWriter.getNx(); j++)
            for (int i = 0; i< imageWriter.getNy(); i++)
                if(i%interval==0 || j%interval==0)
                    imageWriter.writePixel(j, i, color);
    }


    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }
}
