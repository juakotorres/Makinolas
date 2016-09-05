package cl.makinolas.atk.stages;

import cl.makinolas.atk.actors.Background;
import cl.makinolas.atk.actors.Level;
import cl.makinolas.atk.actors.MapInputController;
import cl.makinolas.atk.actors.Traveler;
import cl.makinolas.atk.actors.ui.MobileGroup;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MapStage extends Stage {

    private Traveler traveler = new Traveler();

    private Level actualLevel;
    private Level level1 = new Level("level1", 505,370);
    private Level level2 = new Level("level2", 505, 300, level1);
    private Level level3 = new Level("level3", 450, 300, level2);

    public MapStage(Viewport v) {
        super(v);

        //Adding the actors to the stage (currently just the background, the traveler and the levels)
        addActor(new Background("Background/mapa.png", getCamera()));
        addActor(traveler);
        addActor(level1);
        addActor(level2);
        addActor(level3);

        //Input configurations
        addListener(new MapInputController(this, new MobileGroup(Gdx.app.getType() == Application.ApplicationType.Android)));
        Gdx.input.setInputProcessor(this);

        //Level configuration
        actualLevel = level1;

        //The initial position of the traveler
        traveler.setPosition(505,370);
    }

    public void setLevel(Level level){actualLevel = level;}

    public Level getActualLevel(){return actualLevel;}

    public void nextLevel(){
        actualLevel = actualLevel.getAfter();
        traveler.setPosition(actualLevel.getX(), actualLevel.getY());
    }

    public void lastLevel(){
        actualLevel = actualLevel.getBefore();
        traveler.setPosition(actualLevel.getX(), actualLevel.getY());
    }
}
