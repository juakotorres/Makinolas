package cl.makinolas.atk.actors.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;

public class MobileGroup extends Group {

    private MobileButton bleft, bright, bup, ba;

    public MobileGroup(boolean mobile){
        if(mobile) {
            bleft = new MobileButton(new TextureRegion(new Texture(Gdx.files.internal("Overlays/uileftbtn.png"))), 5, 25);
            addActor(bleft);
            bright = new MobileButton(new TextureRegion(new Texture(Gdx.files.internal("Overlays/uirightbtn.png"))), 85, 25);
            addActor(bright);
            bup = new MobileButton(new TextureRegion(new Texture(Gdx.files.internal("Overlays/uiupbtn.png"))), 460, 25);
            addActor(bup);
            ba = new MobileButton(new TextureRegion(new Texture(Gdx.files.internal("Overlays/uiabtn.png"))), 550, 25);
            addActor(ba);
        }
    }

    public boolean leftPressed(){
        return bleft!=null && bleft.isTouched();
    }

    public boolean rightPressed(){
        return bright!=null && bright.isTouched();
    }

    public boolean upPressed(){
        return bup!=null && bup.isTouched();
    }

    public boolean APressed(){
        return ba!=null && ba.isTouched();
    }

    public boolean AJustPressed(){
        return ba!=null && ba.isJustTouched();
    }

}
