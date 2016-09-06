package cl.makinolas.atk.actors.ui;

import cl.makinolas.atk.stages.MapStage;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class MapStageActor extends Actor {

    private TextureRegion floor = new TextureRegion(new Texture("Overlays/mapstage.png"));
    private TextureRegion floorlock = new TextureRegion(new Texture("Overlays/mapstagelock.png"));
    private boolean locked;

    public MapStageActor(final int lvl, final boolean lck, final MapStage master, int x, int y){
        locked = lck;
        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(!lck)
                    master.moveToLevel(lvl);
                return true;
            }
        });
        setBounds(0,0,32,32);
        setPosition(20*x,20*y-4);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(locked)
            batch.draw(floorlock,getX(),getY());
        else
            batch.draw(floor,getX(),getY());
    }
}
