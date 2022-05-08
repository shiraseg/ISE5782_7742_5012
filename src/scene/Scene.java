package scene;

import Lighting.AmbientLight;
import Lighting.LightSource;
import geometries.Geometries;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

public class Scene
{
    private final String name;
    private final Color background;
    private final AmbientLight ambientLight;
    public Geometries geometries;
    private List<LightSource> lights=new LinkedList<>();

    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    private Scene(SceneBuilder builder) {
        name = builder.name;
        background = builder.background;
        ambientLight = builder.ambientLight;
        geometries = builder.geometries;
        lights =  builder.lights;
    }

        public List<LightSource> getLights() {
        return lights;
    }

    public String getName() {
        return name;
    }

    public Color getBackground() {
        return background;
    }

    public AmbientLight getAmbienLight() {
        return ambientLight;
    }


    //builder pattern.
    public static class SceneBuilder
    {
        private final String name;
        public List<LightSource> lights = new LinkedList<>();
        private Color background = Color.BLACK;
        private AmbientLight ambientLight = new AmbientLight();
        private Geometries geometries = new Geometries();

        public SceneBuilder setLights(List<LightSource> lights)
        {
            this.lights = lights;
            return this;
        }

        public SceneBuilder(String name) {
            this.name = name;
        }

        ////chaining methods
        public SceneBuilder setBackground(Color background)
        {
            this.background = background;
            return this;
        }

        public SceneBuilder setAmbientLight(AmbientLight ambientLight)
        {
            this.ambientLight = ambientLight;
            return this;
        }

        public SceneBuilder setGeometries(Geometries geometries)
        {
            this.geometries = geometries;
            return this;
        }

        public Scene build()
        {
            Scene scene = new Scene(this);
            ///
            return scene;
        }
    }
}