package cl.makinolas.atk.actors.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;

public class MobileGroup extends Group {

    private MobileKeyListener listener;

    public enum MobileKeys {LEFT, RIGHT, UP, A, ITEM1, ITEM2, CHG, PAUSE};

    public MobileGroup(boolean mobile){
        if(mobile) {
            MobileButton bleft = new MobileButton(new TextureRegion(new Texture(Gdx.files.internal("Overlays/uileftbtn.png"))), 5, 25, this, MobileKeys.LEFT);
            addActor(bleft);
            MobileButton bright = new MobileButton(new TextureRegion(new Texture(Gdx.files.internal("Overlays/uirightbtn.png"))), 85, 25, this, MobileKeys.RIGHT);
            addActor(bright);
            MobileButton bup = new MobileButton(new TextureRegion(new Texture(Gdx.files.internal("Overlays/uiupbtn.png"))), 560, 25, this, MobileKeys.UP);
            addActor(bup);
            MobileButton ba = new MobileButton(new TextureRegion(new Texture(Gdx.files.internal("Overlays/uiabtn.png"))), 480, 25, this, MobileKeys.A);
            addActor(ba);
            MobileButton bitem1 = new MobileButton(new TextureRegion(new Texture(Gdx.files.internal("Overlays/uiblankbtn.png"))), 560, 25+80*2, this, MobileKeys.ITEM1);
            addActor(bitem1);
            MobileButton bitem2 = new MobileButton(new TextureRegion(new Texture(Gdx.files.internal("Overlays/uiblankbtn.png"))), 560, 25+80*3, this, MobileKeys.ITEM2);
            addActor(bitem2);
            MobileButton bpause = new MobileButton(new TextureRegion(new Texture(Gdx.files.internal("Overlays/uipausebtn.png"))), 560, 25+40*9, this, MobileKeys.PAUSE);
            addActor(bpause);
            MobileButton bchg = new MobileButton(new TextureRegion(new Texture(Gdx.files.internal("Overlays/uichgbtn.png"))), 5, 25+80*4, this, MobileKeys.CHG);
            addActor(bchg);
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
