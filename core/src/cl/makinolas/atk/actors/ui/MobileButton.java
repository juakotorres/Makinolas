package cl.makinolas.atk.actors.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class MobileButton extends Actor {

    private TextureRegion region;
    private int fixedX, fixedY;

    public MobileButton(TextureRegion reg, int x, int y, final MobileGroup mobileGroup, final MobileGroup.MobileKeys key){
        fixedX = x;
        fixedY = y;
        region = reg;
        setBounds(0,0,80,80);
        addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                mobileGroup.onMobileKeyUp(key);
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mobileGroup.onMobileKeyDown(key);
                return true;
            }
        });
        //setDebug(true);
    }

    @Override
    public void act(float delta) {
        setPosition(getStage().getCamera().position.x-320+fixedX,getStage().getCamera().position.y-240+fixedY);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(region,getX()+16,getY()+16);
    }
}
