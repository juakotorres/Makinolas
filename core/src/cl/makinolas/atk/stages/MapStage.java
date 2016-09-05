package cl.makinolas.atk.stages;

import cl.makinolas.atk.actors.Background;
import cl.makinolas.atk.actors.Level;
import cl.makinolas.atk.actors.MapInputController;
import cl.makinolas.atk.actors.Traveler;
import cl.makinolas.atk.actors.ui.MapStageActor;
import cl.makinolas.atk.actors.ui.MobileGroup;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public class MapStage extends Stage {

    private Traveler traveler;

    private Levels[] levels;
    private int current = 0;
    private int maxAllowed = 3;

    public MapStage(Viewport v) {
        super(v);

        //Adding the actors to the stage (currently just the background, the traveler and the levels)
        addActor(new Background("Background/mapa.png", getCamera()));

        buildLevels();

        // Add floors
        for (int i = 0; i < levels.length; i++) {
            addActor(new MapStageActor(i, maxAllowed<=i, this, levels[i].mapx, levels[i].mapy));
        }

        traveler = new Traveler();
        addActor(traveler);

        //Input configurations
        addListener(new MapInputController(this, new MobileGroup(Gdx.app.getType() == Application.ApplicationType.Android)));
        Gdx.input.setInputProcessor(this);

        current = 0;

        //The initial position of the traveler
        moveToLevel(current);
    }

    public void moveToLevel(int c) {
        current = c;
        traveler.setPosition(levels[c].mapx*20, levels[c].mapy*20);
    }

    private void buildLevels() {
        levels = Levels.values();
    }

    //public int getCurrentLevel(){return current;}

    public void nextLevel(){
        moveToLevel(current+1);
    }

    public void prevLevel(){
        moveToLevel(current-1);
    }

    public void handleKey(int keycode){
        int keynext = -1;
        int keyprev = -1;
        if(current > 0){
            if(levels[current].mapx > levels[current-1].mapx) keyprev = Input.Keys.LEFT;
            else if(levels[current].mapx < levels[current-1].mapx) keyprev = Input.Keys.RIGHT;
            else if(levels[current].mapy < levels[current-1].mapy) keyprev = Input.Keys.UP;
            else if(levels[current].mapy > levels[current-1].mapy) keyprev = Input.Keys.DOWN;
        }
        if(current < maxAllowed-1){
            if(levels[current].mapx > levels[current+1].mapx) keynext = Input.Keys.LEFT;
            else if(levels[current].mapx < levels[current+1].mapx) keynext = Input.Keys.RIGHT;
            else if(levels[current].mapy < levels[current+1].mapy) keynext = Input.Keys.UP;
            else if(levels[current].mapy > levels[current+1].mapy) keynext = Input.Keys.DOWN;
        }
        if(keycode == keyprev)
            prevLevel();
        else if(keycode == keynext)
            nextLevel();
    }

}
