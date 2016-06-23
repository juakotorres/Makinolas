package cl.makinolas.atk.actors.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class MobileButton extends Actor {

    private TextureRegion region;
    private boolean touched;
    private int fixedX, fixedY;

    public MobileButton(TextureRegion reg, int x, int y){
        fixedX = x;
        fixedY = y;
        region = reg;
        addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                touched=true;

            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                touched=false;
                return true;
            }
        });
    }

    public boolean isTouched(){
        return touched;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(region,getStage().getCamera().position.x-320+fixedX,getStage().getCamera().position.y-240+fixedY);
    }
}
