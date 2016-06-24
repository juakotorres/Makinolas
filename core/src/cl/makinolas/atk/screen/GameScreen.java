package cl.makinolas.atk.screen;

import cl.makinolas.atk.stages.GameStage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen implements Screen{
  
  private GameStage stage;
  private Game myGame;
  
  public GameScreen(Game myGame) {
    stage = new GameStage(new FitViewport(640,480), this);
    this.myGame = myGame;
  }

  @Override
  public void show() {
  }

  @Override
  public void render(float delta) {
    //Clear the screen
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    //Update the stage
    stage.draw();
    stage.act(delta);
  }

  @Override
  public void resize(int width, int height) {
    stage.getViewport().update(width,height);
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
    stage.dispose();
  }

  public void mainMenu() {
    myGame.setScreen(new MenuScreen(myGame));
  }
  
}
