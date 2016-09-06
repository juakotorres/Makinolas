package cl.makinolas.atk.screen;

import cl.makinolas.atk.stages.MapStage;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MapScreen extends SimpleScreen {

    public MapScreen(Game myGame,Stage st) {
        super(myGame,st);
    }

    public MapScreen(Game game){
        super(game, new MapStage(new FitViewport(640, 480),game));
    }

}
