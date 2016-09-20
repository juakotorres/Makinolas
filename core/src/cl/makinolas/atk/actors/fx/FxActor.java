package cl.makinolas.atk.actors.fx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class FxActor extends Actor {

    private float acc, duration;
    private Animation animation;

    public FxActor(String region, int w, int h, float duration, int frames){
        TextureRegion[][] ms = (new TextureRegion(new Texture(region))).split(w,h);
        Array<TextureRegion> regs = new Array<TextureRegion>(frames);
        for (int i = 0; i < frames; i++) {
            regs.add(ms[0][i]);
        }
        animation = new Animation(duration/frames, regs, Animation.PlayMode.LOOP);
        acc = 0;
        this.duration = duration;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        acc += delta;
        if(acc >= duration)
            remove();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(animation.getKeyFrame(acc),getX(),getY());
    }

}
