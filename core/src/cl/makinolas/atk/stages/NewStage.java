package cl.makinolas.atk.stages;

import cl.makinolas.atk.actors.LoadActor;
import cl.makinolas.atk.screen.GameScreen;
import cl.makinolas.atk.start.GameText;
import cl.makinolas.atk.start.StartingJourneyStage;
import cl.makinolas.atk.utils.SaveManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.io.File;

/**
 * Created by X455LJ on 16-11-2016.
 */
public class NewStage extends Stage {

    private Game myGame;
    private int actua;
    public NewStage(Viewport v, GameScreen actualScreen, Game myGame) {
        super(v);
        File saves = new File("Save");
        String last_title = saves.list()[saves.list().length-1];
        int actual = Integer.parseInt(last_title.substring(3,4));
        actual++;
        actua = actual;
        this.myGame = myGame;
        GameText.savePath = "Save/ATK"+actual+".sav";
        startJourney();
    }

    public void startJourney() {
        GameScreen gameScreen = new GameScreen(myGame);
        gameScreen.setStage(new StartingJourneyStage(new FitViewport(640,480), gameScreen, myGame));
        myGame.setScreen(gameScreen);
    }



}
