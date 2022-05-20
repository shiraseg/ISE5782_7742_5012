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
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import static java.awt.Color.*;

public class miniProject1 {
    /**
     * this test is part of seventh level.
     * The stage focuses on our attempt to create a scene from ourselves
     * We tried to create a sunset scene
     */
    @Test
    public void test()
    {

        Scene scene1 = new Scene.SceneBuilder("Test scene").build();
        Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150) //
                .setVPDistance(1000);

        //creating a sunset scene
        //adding a sphere= SUN
        scene1.geometries.add(
                new Sphere(new Point(0,-4,-200),40)//SUN
                        .setEmission(new Color(0,0,47))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(300).setKt(new Double3(0)).setKr(new Double3(0))),
                new Plane(new Point(0, -20, 0), new Vector(0, -40, 0))//SEA
                        .setEmission(new Color(0,0,40)),
                new Plane(new Point(0, 0, -300), new Vector(0, 0, 1))//SKY
                        .setEmission( new Color(5,0,54))
                        .setMaterial(new Material().setKt(new Double3(0)) .setKr( new Double3(0))),
                new Triangle(new Point(-52, -32, -28), new Point(-32, -32, -28), new Point(-52, -12, -28))//SHARK
                        .setEmission( new Color(76,76,76))
                        .setMaterial(new Material().setKt(new Double3(1)) .setKr( new Double3(0)))
                );

        //light additions
        scene1.getLights().add(new DirectionalLight(new Color(300,200,0), new Vector(-0.3,-2,0)));
        scene1.getLights().add(new DirectionalLight(new Color(800,200,0), new Vector(0,-2,-1)));
        scene1.getLights().add(new SpotLight(new Color(800, 200, 0), new Point(0, 40, 100), new Vector(0, 1, 4))
                .setkL(0.0004).setkQ(0.0000006));

        //creating the picture
        ImageWriter imageWriter = new ImageWriter("sunsetTest", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage(); //
        camera1.writeToImage(); //
    }
}

