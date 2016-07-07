package cl.makinolas.atk.actors.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;

public class MobileGroup extends Group {

    private MobileKeyListener listener;

    public enum MobileKeys {LEFT, RIGHT, UP, A};

    public MobileGroup(boolean mobile){
        if(mobile) {
            MobileButton bleft = new MobileButton(new TextureRegion(new Texture(Gdx.files.internal("Overlays/uileftbtn.png"))), 5, 25, this, MobileKeys.LEFT);
            addActor(bleft);
            MobileButton bright = new MobileButton(new TextureRegion(new Texture(Gdx.files.internal("Overlays/uirightbtn.png"))), 85, 25, this, MobileKeys.RIGHT);
            addActor(bright);
            MobileButton bup = new MobileButton(new TextureRegion(new Texture(Gdx.files.internal("Overlays/uiupbtn.png"))), 460, 25, this, MobileKeys.UP);
            addActor(bup);
            MobileButton ba = new MobileButton(new TextureRegion(new Texture(Gdx.files.internal("Overlays/uiabtn.png"))), 550, 25, this, MobileKeys.A);
            addActor(ba);
        }
    }

    public void setMobileKeyListener(MobileKeyListener l){
        listener = l;
    }

    public void onMobileKeyDown(MobileKeys k){
        if(listener!=null)
            listener.onMobileKeyDown(k);
    }

    public void onMobileKeyUp(MobileKeys k){
        if(listener!=null)
            listener.onMobileKeyUp(k);
    }


}
