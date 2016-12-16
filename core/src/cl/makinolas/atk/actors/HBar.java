package cl.makinolas.atk.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HBar {

    private float vmax, vcurrent, vanim;
    private int widthmax;
    private boolean animated;
    private Sprite reg;

    public HBar(float max, float current, int width, int height, TextureRegion sprite, boolean an){
        vmax = max;
        vcurrent = current;
        animated = an;
        if(animated)
            vanim = current;
        widthmax = width;
        reg = new Sprite(sprite);
        setCurrent(current);
        reg.setRegionHeight(height);
    }

    public HBar(float max, float current, int width, int height, TextureRegion sprite){
        this(max,current,width,height,sprite,false);
    }

    public void setCurrent(float c){
        vcurrent = c;
        if(animated) {
            vanim = 0.7f * vanim + 0.3f * vcurrent;
            reg.setRegionWidth((int) (widthmax * vanim / vmax));
        }
        else
            reg.setRegionWidth((int) (widthmax * vcurrent / vmax));
    }

    public TextureRegion getSprite(){
        return reg;
    }

}
