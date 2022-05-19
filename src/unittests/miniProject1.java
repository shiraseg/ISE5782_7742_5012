package unittests;

import Lighting.AmbientLight;
import Lighting.DirectionalLight;
import Lighting.PointLight;
import Lighting.SpotLight;
import Renderer.Camera;
import Renderer.ImageWriter;
import Renderer.RayTracerBasic;
import geometries.Geometry;
import geometries.Plane;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import static java.awt.Color.*;

public class miniProject1 {

    @Test
    public void test()
    {

        Scene scene1 = new Scene.SceneBuilder("Test scene").build();
        Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150) //
                .setVPDistance(1000);


        //creating the sunset
        //SUN
        scene1.geometries.add(
        new Sphere(new Point(0,0,-250),40).setEmission(new Color(BLACK)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(300).setKt(new Double3(0.5)).setKr(new Double3(0))));

        //SKY
        Plane plane = new Plane(new Point(0, 0, -300), new Vector(0, 0, 1));
        scene1.geometries.add(plane.setEmission(new Color(163,62,0)).setMaterial(new Material() .setKd(0.5).setKs(0.5).setnShininess(2000).setKt(new Double3(0)).setKr(new Double3(0))));
        //SEA
        Plane plane2=new Plane(new Point(0, -20, 0), new Vector(0, -40, 0));
        scene1.geometries.add(plane2.setEmission( new Color(BLUE)).setMaterial(new Material() .setKr( new Double3(1))));


//        scene1.getLights().add( //
//                new SpotLight(new Color(2555, 510, 0), new Point(20, 100, 75), new Vector(-1, -1, -4)) //
//                        .setkL(4E-4).setkQ(2E-5));

        scene1.getLights().add(new DirectionalLight(new Color(800,200,0), new Vector(-1,-1,-4)));


        ImageWriter imageWriter = new ImageWriter("trytopro", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage(); //
        camera1.writeToImage(); //
    }
}
