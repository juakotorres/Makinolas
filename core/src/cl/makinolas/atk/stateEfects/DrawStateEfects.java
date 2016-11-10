package cl.makinolas.atk.stateEfects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class DrawStateEfects {
	
    private float acc, duration;
    private Animation animation;
    private IStateEfects state;
	
	public DrawStateEfects(String region, int w, int h, float duration, int frames, IStateEfects state){
		this.state = state;
        TextureRegion[][] ms = (new TextureRegion(new Texture(region))).split(w,h);
        Array<TextureRegion> regs = new Array<TextureRegion>(frames);
        for (int i = 0; i < frames; i++) {
            regs.add(ms[0][i]);
        }
        animation = new Animation(duration/frames, regs, Animation.PlayMode.LOOP);
        acc = 0;
        this.duration = duration;
	}

	public void act(float delta) {
        acc += delta;
        System.out.println("act");
        if(acc >= duration){
        	System.out.println("destory");
        	state.destroy();
        }
        	
	}

	public void draw(Batch batch, float alpha, float xPos, float yPos) {
        batch.draw(animation.getKeyFrame(acc),xPos-40,yPos-40);		
	}

}
