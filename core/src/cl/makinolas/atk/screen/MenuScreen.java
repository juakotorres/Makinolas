package cl.makinolas.atk.screen;

import cl.makinolas.atk.stages.MenuStage;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MenuScreen implements Screen {

  private Game myGame;
  private final Stage stage;
  
  public MenuScreen(Game game){
    myGame = game;
    
    stage = new MenuStage(new FitViewport(640,480));
    Gdx.input.setInputProcessor(stage);

    TextButton startButton = new TextButton("Start Game",  new Skin(Gdx.files.internal("Data/uiskin.json")));
    startButton.setPosition(280, 240);
    startButton.addListener(new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
            myGame.setScreen(new GameScreen());
        }
    });
    TextButton loadButton = new TextButton("Load Game",  new Skin(Gdx.files.internal("Data/uiskin.json")));
    TextButton optionButton = new TextButton("Options",  new Skin(Gdx.files.internal("Data/uiskin.json")));
    optionButton.setWidth(loadButton.getWidth());
    startButton.setWidth(loadButton.getWidth());

    loadButton.setPosition(280, 200);
    optionButton.setPosition(280, 160);
    stage.addActor(startButton);
    stage.addActor(loadButton);
    stage.addActor(optionButton);
  }
  
  @Override
  public void show() {
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.act(delta);
    stage.draw();
  }

  @Override
  public void resize(int width, int height) {
    
  }

  @Override
  public void pause() {
    
  }

  @Override
  public void resume() {
    
  }

  @Override
  public void hide() {
    
  }

  @Override
  public void dispose() {
    
  }
  
}
