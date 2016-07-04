package cl.makinolas.atk.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen implements Screen{
  
  private Stage stage;
  private Game myGame;
  
  public GameScreen(Game myGame) {
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

  public void setStage(Stage nextStage) {
    stage = nextStage;    
  }
  
}
