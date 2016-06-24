package cl.makinolas.atk.actors.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class MobileButton extends Actor {

    private TextureRegion region;
    private boolean touched, justTouched;
    private int fixedX, fixedY;

    public MobileButton(TextureRegion reg, int x, int y){
        fixedX = x;
        fixedY = y;
        region = reg;
        setBounds(-4,-4,76,76);
        addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                touched = false;
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                touched = true;
                justTouched = true;
                return true;
            }
        });
    }

    @Override
    public void act(float delta) {
        setPosition(getStage().getCamera().position.x-320+fixedX,getStage().getCamera().position.y-240+fixedY);
        if(justTouched)
            justTouched = false;
    }

    public boolean isTouched(){
        return touched;
    }
    public boolean isJustTouched(){
        return justTouched;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(1,1,1,0.5f);
        batch.draw(region,getX(),getY());
        batch.setColor(1,1,1,1);
    }
}
