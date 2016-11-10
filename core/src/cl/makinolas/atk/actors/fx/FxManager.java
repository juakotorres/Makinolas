package cl.makinolas.atk.actors.fx;

import com.badlogic.gdx.scenes.scene2d.Group;

public class FxManager {

    private Group parent;

    private static FxManager instance = new FxManager();

    public enum Fx {
        REDFX("Fx/fxred.png",48,48,0.5f,5),
        BLUEFX("Fx/fxblue.png",48,48,0.5f,5),
        GREENFX("Fx/fxgreen.png",48,48,0.5f,5),
        YELLOWFX("Fx/fxyellow.png",48,48,0.5f,5);

    	
        public String region;
        public int width, height, frames;
        public float duration;

        Fx(String r, int w, int h, float d, int f){
            region = r;
            width = w;
            height = h;
            duration = d;
            frames = f;
        }
    }

    public static FxManager getInstance() {
        return instance;
    }

    private FxManager(){}

    public void setParent(Group g){
        parent = g;
    }

    public void addFx(Fx fx, float x, float y){
        if(parent == null) return;
        FxActor actor = new FxActor(fx.region,fx.width,fx.height,fx.duration,fx.frames);
        actor.setPosition(x-fx.width/2,y-fx.height/2);
        parent.addActor(actor);
    }


}
