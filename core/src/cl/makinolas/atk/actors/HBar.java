package cl.makinolas.atk.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HBar {

    private float vmax, vcurrent;
    private int widthmax;
    private Sprite reg;

    public HBar(float max, float current, int width, TextureRegion sprite){
        vmax = max;
        vcurrent = current;
        widthmax = width;
        reg = new Sprite(sprite);

    }

    public void setCurrent(float c){
        vcurrent = c;
        reg.setRegionWidth((int) (widthmax*vcurrent/vmax));
    }

    public TextureRegion getSprite(){
        return reg;
    }

}
